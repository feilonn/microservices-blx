package com.blx.vendas.services;

import com.blx.vendas.clients.UsuarioClient;
import com.blx.vendas.dtos.RelatorioComprador;
import com.blx.vendas.dtos.produto.ProdutoProjection;
import com.blx.vendas.dtos.relatorios.RelatorioProdutoCollection;
import com.blx.vendas.dtos.usuario.UsuarioResponse;
import com.blx.vendas.mapper.ProdutoMapper;
import com.blx.vendas.models.Produto;
import com.blx.vendas.repositories.VendasRespository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatoriosService {

    private final VendasService vendasService;
    private final VendasRespository repository;
    private final ProdutoMapper produtoMapper;
    private final ModelMapper modelMapper;
    private final UsuarioClient usuarioClient;

    public void gerarRelatorioComprasByUsuario(Long idComprador, HttpServletResponse response) {
        try {
            InputStream resourceAsStream = this
                    .getClass()
                    .getResourceAsStream("/relatorios/relatorio_comprador.jasper");
            HashMap<String, Object> parametros = new HashMap<>();

            var compras = vendasService.buscarTodasComprasByComprador(idComprador);
            ZoneOffset zoneOffSet= ZoneOffset.of("-03:00");
            var listaDto = compras
                    .stream()
                    .map(compra -> new RelatorioComprador(compra.getId(),
                            compra.getUsuario().getNome(),
                            compra.getTotalCompra(),
                            Date
                            .from(compra.getDataVenda().toInstant(zoneOffSet)),
                            produtosToString(compra.getProdutos())))
                    .collect(Collectors.toList());
            var dados = new JRBeanCollectionDataSource(listaDto);
            var jasperReport = (JasperReport) JRLoader.loadObject(resourceAsStream);

            response.setContentType("application/x-pdf");
            response.setHeader("content-disposition", "inline; filename=relatorio_comprador.pdf");
            OutputStream outputStream = response.getOutputStream();

            var jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dados);

            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao gerar relatório", ex);
        }
    }

    public void gerarRelatorioProdutosVendidosByUsuario(Long idUsuario, HttpServletResponse response) {
        try {
            InputStream resourceAsStream = this.getClass()
                    .getResourceAsStream("/relatorios/relatorio_produtos_vendidos.jasper");
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

            List<RelatorioProdutoCollection> produtoResponse = buscarProdutosVendidosPorUsuario(idUsuario);

            var dados = new JRBeanCollectionDataSource(produtoResponse);
            var jasperReport = (JasperReport) JRLoader.loadObject(resourceAsStream);

            response.setContentType("application/x-pdf");
            response.setHeader("content-disposition", "inline; filename=relatorio_produtos_vendidos.pdf");
            OutputStream outputStream = response.getOutputStream();

            var jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dados);

            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao gerar relatório ", ex);
        }
    }

    public List<RelatorioProdutoCollection> buscarProdutosVendidosPorUsuario(Long idUsuario) {
        Boolean existsUsuario = usuarioClient.existsUsuarioById(idUsuario);
        if(existsUsuario) {
            UsuarioResponse usuarioResponse = usuarioClient.buscarPorId(idUsuario);


            List<ProdutoProjection> results = new ArrayList<>(repository.buscarProdutosVendidosPorUsuario(idUsuario));

            return results.stream()
                    .map(produtoProjection -> new RelatorioProdutoCollection(
                            produtoProjection.getDescricao(),
                            produtoProjection.getTitulo(),
                            produtoProjection.getValor(),
                            produtoProjection.getStatus().getDescricao(),
                            usuarioResponse.getNome()))
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    private String produtosToString(List<Produto> produtos) {
        return produtos
                .stream()
                .map(e -> e.getTitulo() + " - " + "R$" + e.getValor())
                .collect(Collectors.joining("\n"));
    }
}
