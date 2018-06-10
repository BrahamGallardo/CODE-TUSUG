package CONTROLLERS;
import GUI.ListaSiniestroGUI;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @author BARTO
 */
public class SQLRepSinies {
     Connection conn;
     ListaSiniestroGUI interfaz;
     public SQLRepSinies(ListaSiniestroGUI ui)
     {
         interfaz=ui;
         Conexion.setConfiguracion("postgres", "root");
            conn = Conexion.getConexion();
     }
     
     public void llenarTable(){
        try{
            
            Object datos[] = new Object[3];
            String sql ="select codigo_accidente, siniestro, tipo from sistemaTusug.siniestro";
            PreparedStatement pstm = conn.prepareStatement(sql);
        
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                datos[0]=rs.getInt(1);
                datos[1]=rs.getDate(2);
                datos[2]=rs.getString(3);
              
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
            parametros.put("holis", interfaz.valor);
            JasperReport reporte=JasperCompileManager.compileReport("ReporteSiniestro.jrxml");
            JasperPrint p= JasperFillManager.fillReport(reporte, parametros, conn);
            JasperViewer ventanavisor= new JasperViewer(p, false);
            ventanavisor.setTitle("Expediente de Siniestros");
            ventanavisor.setVisible(true);
               }catch(HeadlessException | JRException e){
                     JOptionPane.showMessageDialog(null,"Error en el reporte"+ e);
                 }              
               }

 
    
}

