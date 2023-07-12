package com.blx.vendas.repositories;

import com.blx.vendas.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(nativeQuery = true, value = "SELECT p.* FROM produtos p INNER JOIN usuarios u ON p.usuario_id = u.id" +
            " AND u.id = :usuarioId AND p.status = 'ATIVO'")
    List<Produto> findAllProdutosByVendedorId(Long usuarioId);

}
