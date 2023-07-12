package com.blx.vendas.repositories;

import com.blx.vendas.dtos.produto.ProdutoProjection;
import com.blx.vendas.models.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendasRespository extends JpaRepository<Vendas, Long> {

    @Query(value = "select v.* from vendas v inner join usuarios u on " +
            "u.id = v.usuario_id and u.id = :idUsuario", nativeQuery = true)
    List<Vendas> buscarComprasPorUsuario(Long idUsuario);

    @Query(value = "select p.id, p.descricao, p.titulo, p.valor, p.status, " +
            "p.usuario_id as usuario, p.categoria_id as categoria from" +
            " produtos p inner join vendas_produtos vp on p.id = vp.produto_id where p.usuario_id = :idUsuario",
            nativeQuery = true)
    List<ProdutoProjection> buscarProdutosVendidosPorUsuario(Long idUsuario);
}
