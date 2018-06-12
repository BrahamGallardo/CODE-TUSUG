/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Alekhius
 */
public class AutobusE {
    private String matricula;
    private String id;
    private String marca;
    private String no_economico;
    private int km;
    private int asientos;
    private String DirImage;

    public AutobusE() {
    }
    
    public AutobusE(String matricula, String id, String marca, String no_economico, int km, int asientos, String DirImage) {
        this.matricula = matricula;
        this.id = id;
        this.marca = marca;
        this.no_economico = no_economico;
        this.km = km;
        this.asientos = asientos;
        this.DirImage = DirImage;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNo_economico() {
        return no_economico;
    }

    public void setNo_economico(String no_economico) {
        this.no_economico = no_economico;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int getAsientos() {
        return asientos;
    }

    public void setAsientos(int asientos) {
        this.asientos = asientos;
    }

    public String getDirImage() {
        return DirImage;
    }

    public void setDirImage(String DirImage) {
        this.DirImage = DirImage;
    }

    @Override
    public String toString() {
        return matricula.toUpperCase();
    }
    
    
}
