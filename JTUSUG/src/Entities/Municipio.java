/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alekhius
 */
public class Municipio {
    
    
    private int codigoPostal;
    private String asentamiento;
    private String tipo_asentamiento;
    private String municipio;

    public Municipio(int codigoPostal, String asentamiento, String tipo_asentamiento, String municipio) {
        this.codigoPostal = codigoPostal;
        this.asentamiento = asentamiento;
        this.tipo_asentamiento = tipo_asentamiento;
        this.municipio = municipio;
    }

    public Municipio() {
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getAsentamiento() {
        return asentamiento;
    }

    public void setAsentamiento(String asentamiento) {
        this.asentamiento = asentamiento;
    }

    public String getTipo_asentamiento() {
        return tipo_asentamiento;
    }

    public void setTipo_asentamiento(String tipo_asentamiento) {
        this.tipo_asentamiento = tipo_asentamiento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return municipio + " - " + asentamiento;
    }
    
    
}
