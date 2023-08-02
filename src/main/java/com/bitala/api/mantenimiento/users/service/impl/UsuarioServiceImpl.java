package com.bitala.api.mantenimiento.users.service.impl;

import com.bitala.api.mantenimiento.users.constants.FacturaConstantes;
import com.bitala.api.mantenimiento.users.dao.UsuarioDAO;
import com.bitala.api.mantenimiento.users.pojo.Usuario;
import com.bitala.api.mantenimiento.users.security.jwt.CustomerDetailsService;
import com.bitala.api.mantenimiento.users.security.jwt.JwtUtil;
import com.bitala.api.mantenimiento.users.service.UsuarioService;
import com.bitala.api.mantenimiento.users.util.FacturaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDAO userDAO;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    /*@Autowired
    private JwtFilter jwtFilter;*/

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try{
            if(validateSignUpMap(requestMap)){
                Usuario usuario = userDAO.findByEmail(requestMap.get("email"));
                if(Objects.isNull(usuario)){
                    userDAO.save(getUserFromMap(requestMap));
                    return FacturaUtils.getResponseEntity("Usuario registrado con éxito", HttpStatus.CREATED);
                }
                else{
                    return FacturaUtils.getResponseEntity("El usuario con ese email ya existe", HttpStatus.BAD_REQUEST);
                }
            }
            else{
                return FacturaUtils.getResponseEntity(FacturaConstantes.INVALID_DATA,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return FacturaUtils.getResponseEntity(FacturaConstantes.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"),requestMap.get("password"))
            );
            //Para que un usuario pueda tener un token, tiene que estar activo, es decir, en TRUE
            if(authentication.isAuthenticated()){
                if(customerDetailsService.getUserDetails().getEstatus().equalsIgnoreCase("true")){
                    return new ResponseEntity<String>("{\"token\":\"" + jwtUtil.generateToken(customerDetailsService.getUserDetails().getEmail(), customerDetailsService.getUserDetails().getRol()) + "\"}", HttpStatus.OK);
                } else{
                    return new ResponseEntity<String>("{\"mensaje\":\""+" Necesitas aprobación del administrador "+ "\"}",HttpStatus.BAD_REQUEST);
                }
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return new ResponseEntity<String>("{\"mensaje\":\""+" Credenciales incorrectas "+"\"}",HttpStatus.BAD_REQUEST);
    }

    private boolean validateSignUpMap(Map<String, String> requestMap){
        return requestMap.containsKey("nombre") && requestMap.containsKey("email") && requestMap.containsKey("password");
    }

    private Usuario getUserFromMap(Map<String, String> requestMap){
        Usuario usuario = new Usuario();
        usuario.setNombre(requestMap.get("nombre"));
        usuario.setEmail(requestMap.get("email"));
        usuario.setPassword(requestMap.get("password"));
        usuario.setEstatus("false");
        usuario.setRol("usuario");
        return usuario;
    }
}
