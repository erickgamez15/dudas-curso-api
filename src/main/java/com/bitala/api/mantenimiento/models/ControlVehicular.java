package com.bitala.api.mantenimiento.models;

import jakarta.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

/**
 * API MANTENIMIENTOS - BITALA
 * @AUTHOR ERICK GAMEZ
 * MODEL - CONTROLVEHICULAR
 * 
 * version 1.0
 */

@Entity
//name sigue la nomenclatura de SQL (nombre de la tabla)
@Table(name = "control_vehicular")
@EntityListeners(AuditingEntityListener.class)
public class ControlVehicular {

    /* 
    CREATE TABLE `control_vehicular` (
		`id_control_vehicular` int(11) NOT NULL,
		`fecha_p_semestre`date DEFAULT NULL,
		`doc_p_semestre` varchar(128),
		`checked_pago_ps` varchar(15) DEFAULT 'false',
		`fecha_s_semestre`date DEFAULT NULL,
		`doc_s_semestre` varchar(128),
		`checked_pago_ss` varchar(15) DEFAULT 'false'
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    */

    //Indica que es la PK de la tabla
    @Id
    //Para que sea autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Para que sea autoincrementable
    //name sigue la nomenclatura de SQL
    @Column(name = "id_control_vehicular")
    private Long idControlVehicular;

    @Temporal(TemporalType.DATE) //Agrega solo la fecha, sin la hora
    @DateTimeFormat(pattern = "yyyy-MM-dd") //Formato de fecha
    @Column(name = "fecha_p_semestre")
    private Date fechaPSemestre;

    @Column(name = "doc_p_semestre")
    private String docPSemestre;

    @Column(name = "checked_pago_ps")
    private String checkedPagoPs;

    @Temporal(TemporalType.DATE) //Agrega solo la fecha, sin la hora
    @DateTimeFormat(pattern = "yyyy-MM-dd") //Formato de fecha
    @Column(name = "fecha_s_semestre")
    private Date fechaSSemestre;

    @Column(name = "doc_s_semestre")
    private String docSSemestre;

    @Column(name = "checked_pago_ss")
    private String checkedPagoSs;

    public ControlVehicular() {

    }

    //Constructor para inicializar variables
    public ControlVehicular(Long idControlVehicular, Date fechaPSemestre, String docPSemestre, String checkedPagoPs, Date fechaSSemestre, String docSSemestre, String checkedPagoSs) {
        this.idControlVehicular = idControlVehicular;
        this.fechaPSemestre = fechaPSemestre;
        this.docPSemestre = docPSemestre;
        this.checkedPagoPs = checkedPagoPs;
        this.fechaSSemestre = fechaSSemestre;
        this.docSSemestre = docSSemestre;
        this.checkedPagoSs = checkedPagoSs;
    }

    //Metodos Get y Set
    public Long getIdControlVehicular() {
        return idControlVehicular;
    }

    public void setIdControlVehicular(Long idControlVehicular) {
        this.idControlVehicular = idControlVehicular;
    }

    public Date getFechaPSemestre() {
        return fechaPSemestre;
    }

    public void setFechaPSemestre(Date fechaPSemestre) {
        this.fechaPSemestre = fechaPSemestre;
    }

    public String getDocPSemestre() {
        return docPSemestre;
    }

    public void setDocPSemestre(String docPSemestre) {
        this.docPSemestre = docPSemestre;
    }

    public String getCheckedPagoPs() {
        return checkedPagoPs;
    }

    public void setCheckedPagoPs(String checkedPagoPs) {
        this.checkedPagoPs = checkedPagoPs;
    }

    public Date getFechaSSemestre() {
        return fechaSSemestre;
    }

    public void setFechaSSemestre(Date fechaSSemestre) {
        this.fechaSSemestre = fechaSSemestre;
    }

    public String getDocSSemestre() {
        return docSSemestre;
    }

    public void setDocSSemestre(String docSSemestre) {
        this.docSSemestre = docSSemestre;
    }

    public String getCheckedPagoSs() {
        return checkedPagoSs;
    }

    public void setCheckedPagoSs(String checkedPagoSs) {
        this.checkedPagoSs = checkedPagoSs;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "ControlVehicular [idControlVehicular=" + idControlVehicular + ", fechaPSemestre=" + fechaPSemestre+ ", docPSemestre=" + docPSemestre + ", checkedPagoPs=" + checkedPagoPs + ", fechaSSemestre=" + fechaSSemestre + ", docSSemestre=" + docSSemestre + ", checkedPagoSs=" + checkedPagoSs + "]";
    }
}
