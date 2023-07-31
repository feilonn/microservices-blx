package com.blx.vendas.services;

import com.blx.vendas.clients.UsuarioClient;
import com.blx.vendas.dtos.ProdutoResponse;
import com.blx.vendas.dtos.RelatorioComprador;
import com.blx.vendas.dtos.produto.ProdutoProjection;
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

    private String produtosToString(List<Produto> produtos) {
            return produtos
                .stream()
                .map(e -> e.getTitulo() + " - " + "R$" + e.getValor())
                .collect(Collectors.joining("\n"));
    }

    public List<ProdutoResponse> buscarProdutosVendidosPorUsuario(Long idUsuario) {
        Boolean existsUsuario = usuarioClient.existsUsuarioById(idUsuario);
        if (existsUsuario) {
            List<ProdutoProjection> results = new ArrayList<>(repository.buscarProdutosVendidosPorUsuario(idUsuario));

            return results.stream()
                    .map(produtoProjection -> modelMapper.map(produtoProjection, ProdutoResponse.class))
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}
