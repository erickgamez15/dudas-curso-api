package com.bitala.api.mantenimiento.users.security.jwt;

import java.util.ArrayList;

import com.bitala.api.mantenimiento.users.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bitala.api.mantenimiento.users.pojo.Usuario;

@Service
public class CustomerDetailsService implements UserDetailsService {

    //Anotación para inyección de dependencias
    @Autowired
    private UsuarioDAO usuarioDAO;

    private Usuario usuario;

    public CustomerDetailsService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        usuario = usuarioDAO.findByEmail(username);

        if (usuario != null) {
            return new User(usuario.getEmail(), usuario.getPassword(), new ArrayList<>());
        } else{
            throw new UsernameNotFoundException("User not found");
        }
    }

    public Usuario getUserDetails(){
        return usuario;
    }
    
}
