package com.bitala.api.mantenimiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitala.api.mantenimiento.models.VerificacionVehicular;

/**
 * API MANTENIMIENTOS - BITALA
 * @AUTHOR ERICK GAMEZ
 * REPOSITORY - VERIFICACIONVEHICULAR
 * 
 * version 1.0
 */

@Repository
public interface IVerificacionVehicularRepository extends JpaRepository <VerificacionVehicular, Long> {

}
