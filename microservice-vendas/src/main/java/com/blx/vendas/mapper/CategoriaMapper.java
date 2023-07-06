package com.blx.vendas.mapper;

import com.blx.vendas.dtos.CategoriaRequest;
import com.blx.vendas.dtos.CategoriaResponse;
import com.blx.vendas.models.Categoria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    @Autowired
    private ModelMapper mapper;

    public Categoria toCategoria(CategoriaRequest categoriaRequest) {
        return mapper.map(categoriaRequest, Categoria.class);
    }

    public CategoriaResponse toCategoriaResponse(Categoria categoria) {
        return mapper.map(categoria, CategoriaResponse.class);
    }
}