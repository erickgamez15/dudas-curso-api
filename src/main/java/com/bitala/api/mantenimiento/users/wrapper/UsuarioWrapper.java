package com.bitala.api.mantenimiento.users.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioWrapper {
    private Integer id;
    private String nombre;
    private String email;
    private String numeroDeContacto;
    private String status;
}
