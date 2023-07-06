package com.blx.usuariosauth.repositories;

import com.blx.usuariosauth.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Boolean existsUsuarioById(Long id);

}
