package com.blx.usuariosauth.services;

import com.blx.usuariosauth.exceptions.RecursoNaoEncontradoException;
import com.blx.usuariosauth.mapper.UsuarioMapper;
import com.blx.usuariosauth.repositories.UsuarioRepository;
import com.blx.usuariosauth.dtos.UsuarioRequest;
import com.blx.usuariosauth.dtos.UsuarioResponse;
import com.blx.usuariosauth.models.Usuario;
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
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper usuarioMapper;

    public Page<UsuarioResponse> listar(Pageable pageable) {
        List<UsuarioResponse> listaUsuario = repository
                .findAll(pageable)
                .stream()
                .map(usuarioMapper::toUsuarioResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(listaUsuario);
    }

    public UsuarioResponse buscarUsuarioPorId(Long usuarioId) {
        Usuario usuario = buscarPorId(usuarioId);

        return usuarioMapper.toUsuarioResponse(usuario);
    }

    public UsuarioResponse adicionar(UsuarioRequest usuarioRequest) {
        Usuario usuarioModel = usuarioMapper.toUsuario(usuarioRequest);
        Usuario usuarioSalvo = repository.save(usuarioModel);

        return usuarioMapper.toUsuarioResponse(usuarioSalvo);
    }

    public UsuarioResponse alterar(Long id, UsuarioRequest usuarioRequest) {
        Usuario usuario = buscarPorId(id);
        BeanUtils.copyProperties(usuarioRequest, usuario, "id");

        Usuario usuarioBanco = repository.save(usuario);

        return usuarioMapper.toUsuarioResponse(usuarioBanco);
    }

    public void deletar(Long id) {
        Usuario usuario = buscarPorId(id);

        repository.delete(usuario);
    }

    private Usuario buscarPorId(Long usuarioId) {
        return repository.findById(usuarioId).orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
    }

    public Boolean existsUsuarioById(Long usuarioId) {
        return repository.existsUsuarioById(usuarioId);
    }
}
