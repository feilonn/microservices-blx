package com.blx.vendas.repositories;

import com.blx.vendas.models.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VendasRespository extends JpaRepository<Vendas, Long> {

    @Query(value = "select v.* from vendas v inner join usuario u on u.id = v.comprador_id and u.id = :idUsuario", nativeQuery = true)
    Vendas buscarComprasPorUsuario(Long idUsuario);
}
