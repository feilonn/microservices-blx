package com.blx.vendas.services;

import com.blx.vendas.clients.UsuarioClient;
import com.blx.vendas.models.Produto;
import com.blx.vendas.models.Usuario;
import com.blx.vendas.models.Vendas;
import com.blx.vendas.utils.RelatorioUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
@RequiredArgsConstructor
public class ArquivosService {

    private final UsuarioClient usuarioClient;
    private final ProdutoService produtoService;

    @Value(value = "${app.caminhos.arquivoTxt}")
    private String caminhoArquivoTxt;

    public void salvarDadosTxtNoBanco() {
        ClassPathResource path = new ClassPathResource(caminhoArquivoTxt);
        List<String> linhasArquivos = RelatorioUtils.lerArquivoTxt(path);
        String cabecalho = linhasArquivos.get(0);
        Map<String, Integer> camposMapeados = mapearIndiceCampos(cabecalho.split(";"));
        List<String> conteudoArquivo = linhasArquivos.stream().skip(1).collect(Collectors.toList());

        var teste = criarVendasComCampos(conteudoArquivo, camposMapeados);
    }

    private List<Vendas> criarVendasComCampos(List<String> valores, Map<String, Integer> indiceCampos) {
        List<Vendas> listaParaPreencher = new ArrayList<>();

        LongStream.range(0, valores.size())
                .forEach(linhaTxt -> {
                    Vendas venda = new Vendas();
                    Arrays.stream(Vendas.class.getDeclaredFields())
                            .filter(field -> {
                                return indiceCampos.containsKey(field.getName());
                            })
                            .forEach(field -> {
                                Integer indiceCampo = indiceCampos.get(field.getName());
                                if(indiceCampo < valores.size()) {
                                    String valor = valores.get((int) linhaTxt);
                                    String[] listaCamposPorLinha = valor.split(";");
                                    if (!StringUtils.isEmpty(valor) && indiceCampo < listaCamposPorLinha.length) {
                                        try {
                                            field.setAccessible(true);
                                            if (field.getType() == LocalDateTime.class) {
                                                var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                                field.set(venda, LocalDate.parse(listaCamposPorLinha[indiceCampo],
                                                        formatter).atStartOfDay());
                                            } else if (field.getType() == String.class) {
                                                field.set(venda, listaCamposPorLinha[indiceCampo]);
                                            } else if (field.getType() == Usuario.class) {
                                                var idUsuario = Long.parseLong(listaCamposPorLinha[indiceCampo]);
                                                var usuarioResponse = usuarioClient.buscarPorId(idUsuario);
                                                var usuario = new Usuario(idUsuario, usuarioResponse.getEmail(),
                                                        usuarioResponse.getNome(), usuarioResponse.getRole());
                                                field.set(venda, usuario);
                                            } else if (field.getType() == List.class && field.getGenericType() instanceof ParameterizedType) {
                                                String[] idsProdutos = listaCamposPorLinha[indiceCampo].split("-");
                                                List<Long> listaDeIdsConvertidos = Arrays.stream(idsProdutos)
                                                        .map(Long::parseLong)
                                                        .collect(Collectors.toList());
                                                List<Produto> produtosById = produtoService.buscarProdutosById(listaDeIdsConvertidos);
                                                field.set(venda, produtosById);
                                            }
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                            });
                    listaParaPreencher.add(venda);
                });
        return listaParaPreencher;
    }

    private Map<String, Integer> mapearIndiceCampos(String[] campos) {
        Map<String, Integer> indiceCampos = new HashMap<>();
        for (int i = 0; i < campos.length; i++) {
            indiceCampos.put(campos[i], i);
        }
        return indiceCampos;
    }

}
