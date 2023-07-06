package com.blx.usuariosauth.repositories;

import com.blx.usuariosauth.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
