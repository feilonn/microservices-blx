package com.blx.vendas.repositories;

import com.blx.vendas.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Boolean existsCategoriaById(Long id);
}
