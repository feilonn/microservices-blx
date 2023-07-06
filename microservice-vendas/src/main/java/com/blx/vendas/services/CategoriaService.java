package com.blx.vendas.services;

import com.blx.vendas.dtos.CategoriaRequest;
import com.blx.vendas.dtos.CategoriaResponse;
import com.blx.vendas.mapper.CategoriaMapper;
import com.blx.vendas.models.Categoria;
import com.blx.vendas.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;
    private final CategoriaMapper categoriaMapper;

    public Page<CategoriaResponse> listar(Pageable pageable) {
        List<CategoriaResponse> listaCategoria = repository
                .findAll(pageable)
                .stream()
                .map(categoriaMapper::toCategoriaResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(listaCategoria);
    }

    public CategoriaResponse buscarCategoriaPorId(Long categoriaId) {
        Categoria categoria = buscarPorId(categoriaId);

        return categoriaMapper.toCategoriaResponse(categoria);
    }

    public CategoriaResponse adicionar(CategoriaRequest categoriaRequest) {
        Categoria categoriaModel = categoriaMapper.toCategoria(categoriaRequest);
        categoriaModel.setDataCriacao(LocalDateTime.now());
        Categoria categoriaSalva = repository.save(categoriaModel);

        return categoriaMapper.toCategoriaResponse(categoriaSalva);
    }

    public CategoriaResponse alterar(Long id, CategoriaRequest categoriaRequest) {
        Categoria categoria = buscarPorId(id);
        BeanUtils.copyProperties(categoriaRequest, categoria, "id");

        Categoria categoriaBanco = repository.save(categoria);

        return categoriaMapper.toCategoriaResponse(categoriaBanco);
    }

    public void deletar(Long id) {
        Categoria categoria = buscarPorId(id);

        repository.delete(categoria);
    }

    private Categoria buscarPorId(Long categoriaId) {
        return repository.findById(categoriaId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }


}
