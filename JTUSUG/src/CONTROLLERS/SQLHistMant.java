package CONTROLLERS;
import GUI.HistorialMantenGUI;
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
 * @author gallardo
 */
public class SQLHistMant {
    Connection conn;
    HistorialMantenGUI interfaz;
    public SQLHistMant(HistorialMantenGUI ui){
            interfaz=ui;
            Conexion.setConfiguracion("postgres","root");
            conn = Conexion.getConexion();
    }
    public String[][] obtenerRegistro(){
        String sql = "select count(codigo_m) as total from sistemaTusug.mantenimiento ";
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
            sql = "select codigo_m from sistemaTusug.mantenimiento ORDER BY codigo_m";
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            int index = 0;
            while(res.next()){
                String dato=res.getString("codigo_m");
                tabla[index][0] = dato;
                index++;
            }
            sql = "select responsable from sistemaTusug.mantenimiento ORDER BY codigo_m";
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            int index2 = 0;
            while(res.next()){
                String dato2=res.getString("responsable");
                tabla[index2][1] = dato2;
                index2++;
            }
            sql = "select fecha_ingreso from sistemaTusug.mantenimiento ORDER BY codigo_m";
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            int index3 = 0;
            while(res.next()){
                String dato3=res.getString("fecha_ingreso");
                tabla[index3][2] = dato3;
                index3++;
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
            parametros.put("holis", interfaz.valor);
            JasperReport reporte=JasperCompileManager.compileReport("ReporteMante.jrxml");
            JasperPrint p= JasperFillManager.fillReport(reporte, parametros, conn);
            JasperViewer ventanavisor= new JasperViewer(p, false);
            ventanavisor.setTitle("REPORTE DE MANTENIMIENTO");
            ventanavisor.setVisible(true);
               }catch(HeadlessException | JRException e){
                     JOptionPane.showMessageDialog(null,"Error en el reporte"+ e);
                 }              
               }
    public String[][] obtenerRegistroArgs(String args){
        String sql = "select count(codigo_m) as total from sistemaTusug.mantenimiento ";
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
            sql = "select codigo_m from sistemaTusug.mantenimiento ORDER BY codigo_m";
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            int index = 0;
            while(res.next()){
                String dato=res.getString("codigo_m");
                tabla[index][0] = dato;
                index++;
            }
            sql = "select responsable from sistemaTusug.mantenimiento ORDER BY codigo_m";
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            int index2 = 0;
            while(res.next()){
                String dato2=res.getString("responsable");
                tabla[index2][1] = dato2;
                index2++;
            }
            sql = "select fecha_ingreso from sistemaTusug.mantenimiento ORDER BY codigo_m";
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            int index3 = 0;
            while(res.next()){
                String dato3=res.getString("fecha_ingreso");
                tabla[index3][2] = dato3;
                index3++;
            }
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tabla;
    } 
}