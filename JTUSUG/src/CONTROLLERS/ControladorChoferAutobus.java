
package CONTROLLERS;

import GUI.GUIAutobusChofer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;


public class ControladorChoferAutobus {
        GUIAutobusChofer interfaz;
        Connection c;
        ArrayList<String> m;
        ArrayList<String>n;

        
        public ControladorChoferAutobus(GUIAutobusChofer ui){
            interfaz=ui;
            Conexion.setConfiguracion("postgres", "root");
            c = Conexion.getConexion();
            
        }
        
   public String[] listaParametro(String rfc) throws SQLException {
        String[] registros = new String[9];
        try {

            PreparedStatement pstm = c.prepareStatement("select * from sistemaTusug.trabajador where rfc=?");
            pstm.setString(1, rfc);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
            //interfaz.matric.        setText(rs.getString(1));
            interfaz.name.          setText(rs.getString(2));
            interfaz.ap_paterno.    setText(rs.getString(3));
            interfaz.ap_materno.    setText(rs.getString(4));
            interfaz.state.         setText(rs.getString(9));
            }
            
        
         //   pstm.close();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, e);
        }
       
 return registros;
    
}
   
       public ArrayList<String> listaTrabajador() {
        try {

            String[] registros = new String[1];
            m= new ArrayList();
            String cons = "select rfc from sistemaTusug.trabajador where puesto='chofer'";

            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while (rs.next()) {
                m.add( rs.getString(1));
                System.out.print(rs.getString(1));
               
             //   st.close();

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return m;
    }
       
        public void cargarLista(JList l) {
        DefaultListModel modelo = new DefaultListModel();
        ArrayList<String> lista = listaTrabajador();
        for (int i = 0; i < lista.size(); i++) {
            modelo.addElement(lista.get(i).toUpperCase());
        }
        l.setModel(modelo);
    }
       public void cargarListaAutobus(JList l) {
        DefaultListModel modelo = new DefaultListModel();
        ArrayList<String> lista = listaAutobuses();
        for (int i = 0; i < lista.size(); i++) {
            modelo.addElement(lista.get(i).toUpperCase());
        }
        l.setModel(modelo);
    }
       
        public ArrayList<String> listaAutobuses() {
        try {

            String[] registros = new String[1];
            n= new ArrayList();
            String cons = "select matricula from sistemaTusug.autobus ";

            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while (rs.next()) {
                n.add( rs.getString(1));
                System.out.print(rs.getString(1));
               
             //   st.close();

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return n;
    }
        
       public String[] listaParametroAutobus(String matricula) throws SQLException {
        String[] registros = new String[9];
        try {

            PreparedStatement pstm = c.prepareStatement("select numero_economico,matricula,marca,kilometraje from sistemaTusug.autobus where matricula=?");
            pstm.setString(1, matricula);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
            //interfaz.matric.        setText(rs.getString(1));
            interfaz.combo.          setText(rs.getString(1));
            interfaz.matric.    setText(rs.getString(2));
            interfaz.marc.    setText(rs.getString(3));
            interfaz.kilometraje.         setText(rs.getString(4));
            }
            
        
         //   pstm.close();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, e);
        }
       
 return registros;
    
}
       
       public void addT() {
        try {
                String dato=(String)interfaz.lista.getSelectedValue();
            PreparedStatement pstm = c.prepareStatement("insert into "
                    + "sistemaTusug.chofer_autobus(rfc, matricula) "
                    + " values(?,?)");
            pstm.setString(1,dato.toLowerCase() );
            pstm.setString(2, interfaz.matric.getText());
            pstm.execute();
            System.out.print("agregado");
        } catch (SQLException ex) {
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}