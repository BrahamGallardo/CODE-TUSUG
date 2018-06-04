
package CONTROLLERS;

import GUI.GUIReporteManten;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


public class ControladorMantenimiento {
    
    GUIReporteManten interfaz;
    Connection c;
    public int Indice;
    
    public ControladorMantenimiento(GUIReporteManten ui){
        interfaz=ui;
        Conexion.setConfiguracion("postgres", "root");
        c = Conexion.getConexion();   
    }
    
    
     public void creaRepor() throws JRException{
               try{
          
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("holis", Indice);
            JasperReport reporte=JasperCompileManager.compileReport("ReporteMante.jrxml");
            JasperPrint p= JasperFillManager.fillReport(reporte, parametros, c);
            JasperViewer ventanavisor= new JasperViewer(p, false);
            ventanavisor.setTitle("REPORTE DE MANTENIMIENTO");
            ventanavisor.setVisible(true);
               }catch(HeadlessException | JRException e){
                     JOptionPane.showMessageDialog(null,"Error en el reporte"+ e);
                 }
               
               }
    
    public void agregaMan(){
        try {
          int x=Integer.parseInt(interfaz.txt_costo.getText());
           System.out.print("agregado");
           ResultSet res;
            PreparedStatement pstm = c.prepareStatement("insert into "
                    + "sistemaTusug.mantenimiento(matricula,fecha_ingreso,fecha_salida,solicitante,responsable,solicitud,area_trabajo,prioridad,tipo_de_mantenimiento,direccion,telefono,email,costo_reparacion) "
                    + " values(?,CURRENT_DATE,?,?,?,?,?,?,?,?,?,?,?) returning codigo_m");
            System.out.print("agregado");
            
            pstm.setString(1, interfaz.txt_matricula.getText());
            pstm.setDate  (2, new java.sql.Date(interfaz.calendario.getDate().getTime() ));
            pstm.setString(3, interfaz.txt_solicitante.getText());
            pstm.setString(4, interfaz.txt_responsable.getText());
            pstm.setString(5, interfaz.text_solicitud.getText());
            pstm.setString(6, interfaz.txt_areaTrabajo.getText());
            pstm.setString(7, interfaz.txt_prioridad.getText());
            pstm.setString(8, interfaz.txt_tipoManten.getText());
            pstm.setString(9, interfaz.txt_direccion.getText());
            pstm.setString(10,interfaz.txt_telefono.getText());
            pstm.setString(11,interfaz.txt_email.getText());
            pstm.setInt(12,x);
            pstm.execute();
             res=pstm.executeQuery();
            if(res.next()){
            
            Indice=res.getInt(1);
            System.out.print(Indice);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        
    }
}
