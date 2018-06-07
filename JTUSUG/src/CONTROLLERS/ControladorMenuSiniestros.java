/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLERS;
import GUI.GUIMenuSiniestros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author laibr
 */
public class ControladorMenuSiniestros {
    
    GUIMenuSiniestros interfaz;
    Connection c;
    public int Indice;
    
    public ControladorMenuSiniestros(GUIMenuSiniestros ui){
        interfaz=ui;
        Conexion.setConfiguracion("postgres", "root");
        c = Conexion.getConexion();   
    }
    
}
