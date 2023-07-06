package com.blx.vendas.repositories;

import com.blx.vendas.models.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendasRespository extends JpaRepository<Vendas, Long> {
}
