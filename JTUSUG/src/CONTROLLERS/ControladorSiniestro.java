package CONTROLLERS;

import GUI.ReportarSiniestroGUI;
import java.awt.HeadlessException;
import java.sql.Connection;
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

/**
 *
 * @author Guillermo
 */
public class ControladorSiniestro {
    
    ReportarSiniestroGUI interfaz;
     Connection conn;
     public int Indice;
           
    public ControladorSiniestro( ReportarSiniestroGUI ui){
        interfaz=ui;
         Conexion.setConfiguracion("postgres","root");
         conn = Conexion.getConexion();
        
    }
    
        public void creaRepor() throws JRException{
               try{
          
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("holis", Indice);
            JasperReport reporte=JasperCompileManager.compileReport("ReporteSiniestro.jrxml");
            JasperPrint p= JasperFillManager.fillReport(reporte, parametros, conn);
            JasperViewer ventanavisor= new JasperViewer(p, false);
            ventanavisor.setTitle("REPORTE DE Siniestro");
            ventanavisor.setVisible(true);
               }catch(HeadlessException | JRException e){
                     JOptionPane.showMessageDialog(null,"Error en el reporte"+ e);
                 }
               
               }
    
     public void agregaMan(){
        try {
           System.out.print("agregado");
           ResultSet res;
            PreparedStatement pstm = conn.prepareStatement("insert into "
                    + "sistemaTusug.siniestro(siniestro,ingreso,indemnizado,tipo,reclamado,pagado,estado) "
                    + " values(?,?,?,?,?,?,?) returning codigo_accidente");
            System.out.print("agregado");
            
            pstm.setDate(1, new java.sql.Date(interfaz.calendarioSiniestro.getDate().getTime()));
            pstm.setDate(2, new java.sql.Date(interfaz.calendarioIngreso.getDate().getTime()));
            pstm.setDate(3, new java.sql.Date(interfaz.calendarioIndemnizado.getDate().getTime()));
            pstm.setString(4, (String)interfaz.tipoS.getSelectedItem());
            pstm.setInt(5, Integer.parseInt(interfaz.reclama.getText()));
            pstm.setInt(6, Integer.parseInt(interfaz.paga.getText()));
            pstm.setString(7, (String)(interfaz.status.getSelectedItem()));
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
