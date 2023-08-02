package com.bitala.api.mantenimiento.users.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UsuarioService {

    ResponseEntity<String> signUp(Map<String,String> requestMap);

    ResponseEntity<String> login(Map<String,String> requestMap);
/*
    ResponseEntity<List<UsuarioWrapper>> getAllUsers();

    ResponseEntity<String> update(Map<String,String> requestMap);

    ResponseEntity<String> checkToken();

    ResponseEntity<String> changePassword(Map<String,String> requestMap);

    ResponseEntity<String> forgotPassword(Map<String,String> requestMap);*/
}
