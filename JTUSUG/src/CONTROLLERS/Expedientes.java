/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLERS;
import GUI.GUIExpedientes;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Guillermo
 */
public class Expedientes {
        Connection conn;
        GUIExpedientes interfaz;
    public Expedientes(GUIExpedientes ui){
        interfaz=ui;
        Conexion.setConfiguracion("postgres", "root");
        conn = Conexion.getConexion();
    }

 
    
    public void llenarTable(){
        try{
            
            String datos[] = new String[4];
            String sql ="select rfc, nombre, ap_paterno, ap_materno from sistemaTusug.trabajador ";
            PreparedStatement pstm = conn.prepareStatement(sql);
        
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                interfaz.model.addRow(datos);
            }
        }catch (Exception e) {
            //System.out.println(e.getMessage());
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
         public void creaRepor() throws JRException{
               try{
          
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("rf", interfaz.valor);
            JasperReport reporte=JasperCompileManager.compileReport("ReporteExpedientes.jrxml");
            JasperPrint p= JasperFillManager.fillReport(reporte, parametros, conn);
            JasperViewer ventanavisor= new JasperViewer(p, false);
            ventanavisor.setTitle("Expediente de trabajador");
            ventanavisor.setVisible(true);
               }catch(HeadlessException | JRException e){
                     JOptionPane.showMessageDialog(null,"Error en el reporte"+ e);
                 }              
               }

 
    
    
}
