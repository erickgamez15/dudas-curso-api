package com.bitala.api.mantenimiento.users.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitala.api.mantenimiento.users.pojo.Usuario;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
