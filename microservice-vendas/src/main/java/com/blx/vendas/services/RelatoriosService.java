package com.blx.vendas.services;

import com.blx.vendas.clients.UsuarioClient;
import com.blx.vendas.dtos.ProdutoResponse;
import com.blx.vendas.dtos.RelatorioComprador;
import com.blx.vendas.dtos.produto.ProdutoProjection;
import com.blx.vendas.mapper.ProdutoMapper;
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
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
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

    public void gerarRelatorioComprasByUsuario(HttpServletResponse response) {
        try {
            InputStream resourceAsStream = new FileInputStream("C:\\Users\\Maycon\\Desktop\\Projeto spring wipro\\microservices-blx\\microservice-vendas\\src\\main\\resources\\relatorios\\relatorio_comprador_3.jasper");
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

            var compras = vendasService.buscarTodasComprasByComprador(1L);
            var listaDto = compras
                    .stream()
                    .map(venda -> new RelatorioComprador(venda.getId(), new Date(2023, 1, 05)))
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

    public List<ProdutoResponse> buscarProdutosVendidosPorUsuario(Long idUsuario) {
        Boolean existsUsuario = usuarioClient.existsUsuarioById(idUsuario);
        if(existsUsuario) {
            List<ProdutoProjection> results = new ArrayList<>(repository.buscarProdutosVendidosPorUsuario(idUsuario));

            return results.stream()
                    .map(produtoProjection -> modelMapper.map(produtoProjection, ProdutoResponse.class))
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}
