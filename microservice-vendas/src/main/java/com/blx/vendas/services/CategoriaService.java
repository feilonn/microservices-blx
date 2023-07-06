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

    public CategoriaResponse buscarCategoriaPorId(Long usuarioId) {
        Categoria usuario = buscarPorId(usuarioId);

        return categoriaMapper.toCategoriaResponse(usuario);
    }

    public CategoriaResponse adicionar(CategoriaRequest usuarioRequest) {
        Categoria usuarioModel = categoriaMapper.toCategoria(usuarioRequest);
        Categoria usuarioSalvo = repository.save(usuarioModel);

        return categoriaMapper.toCategoriaResponse(usuarioSalvo);
    }

    public CategoriaResponse alterar(Long id, CategoriaRequest usuarioRequest) {
        Categoria usuario = buscarPorId(id);
        BeanUtils.copyProperties(usuarioRequest, usuario, "id");

        Categoria usuarioBanco = repository.save(usuario);

        return categoriaMapper.toCategoriaResponse(usuarioBanco);
    }

    public void deletar(Long id) {
        Categoria usuario = buscarPorId(id);

        repository.delete(usuario);
    }

    private Categoria buscarPorId(Long usuarioId) {
        return repository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }


}
