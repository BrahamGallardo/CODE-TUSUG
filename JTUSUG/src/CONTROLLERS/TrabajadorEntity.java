/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLERS;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Alekhius
 */
public class TrabajadorEntity {
    private String rfc;
    private String nombre;
    private String ap_p;    
    private String ap_m;
    private String domicilio;
    private String puesto;    
    private java.sql.Date fecha_nac;
    private java.sql.Date fecha_contrato;
    private String estado;
    private String imgUri;

    public TrabajadorEntity(String rfc, String nombre, String ap_p, String ap_m, String domicilio, String puesto, Date fecha_nac, Date fecha_contrato, String estado, String imgUri) {
        this.rfc = rfc;
        this.nombre = nombre;
        this.ap_p = ap_p;
        this.ap_m = ap_m;
        this.domicilio = domicilio;
        this.puesto = puesto;
        this.fecha_nac = fecha_nac;
        this.fecha_contrato = fecha_contrato;
        this.estado = estado;
        this.imgUri = imgUri;
    }

    public TrabajadorEntity() {
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAp_p(String ap_p) {
        this.ap_p = ap_p;
    }

    public void setAp_m(String ap_m) {
        this.ap_m = ap_m;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public void setFecha_nac(java.sql.Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public void setFecha_contrato(java.sql.Date fecha_contrato) {
        this.fecha_contrato = fecha_contrato;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public String getRfc() {
        return rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAp_p() {
        return ap_p;
    }

    public String getAp_m() {
        return ap_m;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getPuesto() {
        return puesto;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public Date getFecha_contrato() {
        return fecha_contrato;
    }

    public String getEstado() {
        return estado;
    }

    public String getImgUri() {
        return imgUri;
    }
    
    
    
    @Override
    public String toString() {
        return rfc.toUpperCase();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TrabajadorEntity other = (TrabajadorEntity) obj;
        if (!Objects.equals(this.rfc, other.rfc)) {
            return false;
        }
        return true;
    }
    
    
    
}
