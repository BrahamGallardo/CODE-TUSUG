/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLERS;

import GUI.GUIListaChoferAutobus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guillermo
 */
public class ListaAutobusChofer {
        Connection conn;
        GUIListaChoferAutobus interfaz;
        
        public ListaAutobusChofer(GUIListaChoferAutobus ui){
            interfaz=ui;
            Conexion.setConfiguracion("postgres", "root");
            conn = Conexion.getConexion();
        }
           public void llenarTable(){
        try{
            
            String datos[] = new String[2];
            String sql ="select rfc, matricula from sistemaTusug.chofer_autobus ";
            PreparedStatement pstm = conn.prepareStatement(sql);
        
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                interfaz.model.addRow(datos);
            }
        }catch (Exception e) {
            //System.out.println(e.getMessage());
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
           
               public void borrarAutobusBy() throws SQLException {
        PreparedStatement stmt = null;
        //int tuplas = 0;
        try {
            stmt = conn.prepareStatement("delete from sistematusug.chofer_autobus where lower(rfc) = ?");
            stmt.setString(1, interfaz.valor.toLowerCase());
            int tuplas = stmt.executeUpdate();
            //rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
