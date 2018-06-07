
package CONTROLLERS;

import GUI.GUIAutobusChofer;
import java.sql.Connection;
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
            interfaz.matric.setText(rs.getString(1));
            interfaz.name.setText(rs.getString(2));
            interfaz.ap_paterno.setText(rs.getString(3));
            interfaz.ap_materno.setText(rs.getString(4));
            interfaz.state.setText(rs.getString(9));
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
            String cons = "select rfc from sistemaTusug.trabajador";

            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while (rs.next()) {
                registros[0] = rs.getString(1);
                System.out.print(registros[0]);
                m.add(registros[0]);
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
}