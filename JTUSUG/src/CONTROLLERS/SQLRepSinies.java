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
     
     public String[][] obtenerRegistro(){
        String sql = "select count(codigo_accidente) as total from sistemaTusug.siniestro ";
            PreparedStatement pst;
            ResultSet res;
            int n = 0;
        try {
            pst = conn.prepareStatement(sql);
        
            res = pst.executeQuery();
            res.next();
            n = res.getInt("total");
            res.close();
            } catch (SQLException ex) {
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String tabla[][] = new String[n][4];
        
        try{
            sql = "select codigo_accidente from sistemaTusug.siniestro ORDER BY codigo_accidente";
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            int index = 0;
            while(res.next()){
                String dato=res.getString("codigo_accidente");
                tabla[index][0] = dato;
                index++;
                System.out.println(dato);
            }
            sql = "select siniestro from sistemaTusug.siniestro ORDER BY codigo_accidente";
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            int index2 = 0;
            while(res.next()){
                String dato2=res.getString("siniestro");
                tabla[index2][1] = dato2;
                index2++;
            }
            sql = "select tipo from sistemaTusug.siniestro ORDER BY codigo_accidente";
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            int index3 = 0;
            while(res.next()){
                String dato3=res.getString("tipo");
                tabla[index3][2] = dato3;
                index3++;
            }
            sql = "select estado from sistemaTusug.siniestro ORDER BY codigo_accidente";
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            int index4 = 0;
            while(res.next()){
                String dato4=res.getString("estado");
                tabla[index4][3] = dato4;
                index4++;
            }
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tabla;
    }
        public void creaRepor() throws JRException{
               try{
          
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("holis", interfaz.valor );
            JasperReport reporte=JasperCompileManager.compileReport("ReporteSiniestro.jrxml");
            JasperPrint p= JasperFillManager.fillReport(reporte, parametros, conn);
            JasperViewer ventanavisor= new JasperViewer(p, false);
            ventanavisor.setTitle("REPORTE DE Siniestro");
            ventanavisor.setVisible(true);
               }catch(HeadlessException | JRException e){
                     JOptionPane.showMessageDialog(null,"Error en el reporte"+ e);
                 }
               
               }
     
    public String[][] obtenerRegistroArgs(String args){
        String sql = "select count(codigo_accidente) as total from sistemaTusug.siniestro ";
            PreparedStatement pst;
            ResultSet res;
            int n = 0;
        try {
            pst = conn.prepareStatement(sql);
        
            res = pst.executeQuery();
            res.next();
            n = res.getInt("total");
            res.close();
            } catch (SQLException ex) {
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String tabla[][] = new String[n][3];
        
        try{
            sql = "select codigo_accidente from sistemaTusug.siniestro ORDER BY codigo_accidente";
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            int index = 0;
            while(res.next()){
                String dato=res.getString("codigo_accidente");
                tabla[index][0] = dato;
                index++;
                System.out.print(dato);
            }
            sql = "select siniestro from sistemaTusug.siniestro ORDER BY codigo_accidente";
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            int index2 = 0;
            while(res.next()){
                String dato2=res.getString("siniestro");
                tabla[index2][1] = dato2;
                index2++;
            }
            sql = "select tipo from sistemaTusug.siniestro ORDER BY codigo_accidente";
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            int index3 = 0;
            while(res.next()){
                String dato3=res.getString("tipo");
                tabla[index3][2] = dato3;
                index3++;
            }
            sql = "select estado from sistemaTusug.siniestro ORDER BY codigo_accidente";
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            int index4 = 0;
            while(res.next()){
                String dato4=res.getString("estado");
                tabla[index4][3] = dato4;
                index4++;
            }
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tabla;
    } 
}

