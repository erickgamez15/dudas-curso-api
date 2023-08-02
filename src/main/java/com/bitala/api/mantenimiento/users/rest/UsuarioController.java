package com.bitala.api.mantenimiento.users.rest;

import com.bitala.api.mantenimiento.users.constants.FacturaConstantes;
import com.bitala.api.mantenimiento.users.service.UsuarioService;
import com.bitala.api.mantenimiento.users.util.FacturaUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Map;

//Para indicar que la clase es un controlador
@RestController
//Ruta para acceder a los usuarios
@RequestMapping("/api/usuario")
public class UsuarioController {

    //Anotación para inyección de dependencias
    @Autowired
    private UsuarioService usuarioService;


    //Constructor para inyección de dependencias
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signupUsuario(@RequestBody(required = true) Map<String,String> requestMap){
        try{
            return usuarioService.signUp(requestMap);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return FacturaUtils.getResponseEntity(FacturaConstantes.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUsuario(@RequestBody(required = true) Map<String,String> requestMap){
        try{
            return usuarioService.login(requestMap);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return FacturaUtils.getResponseEntity(FacturaConstantes.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
