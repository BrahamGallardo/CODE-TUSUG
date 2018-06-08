package CONTROLLERS;

import CustomException.InvalidFormatException;
import Servicios.Fachada;
import java.sql.Date;
import GUI.TrabajadorGUI;
import Validacion.Validador;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Trabajador {
    HashMap<String, TrabajadorEntity> dataBD;

    TrabajadorGUI interfaz;
    Connection c;
    ArrayList<String> m;

    public Trabajador(TrabajadorGUI ui) {
        dataBD = new HashMap<String, TrabajadorEntity>();
        interfaz = ui;
        c = Conexion.getConexion();
        if (c == null) {
            interfaz.disable();
        }

    }

    public void agregaTrabajador() {
        try {
            String rfc = Validador.getRfcIfIsValid(interfaz.tfrfc.getText()).toLowerCase();
            String nombre = interfaz.tfnom.getText().toLowerCase();
            String ap_paterno = interfaz.tfapp.getText().toLowerCase();
            String ap_materno = interfaz.tfapm.getText().toLowerCase();
            String domicilio = interfaz.area1.getText().toLowerCase();
            /*--------Intermedio para obtener fecha de nac y validar esa parte-------*/

            int anioFechaNac;
            try {
                anioFechaNac = Integer.parseInt(rfc.substring(3, 5));
            } catch (NumberFormatException ex) {
                anioFechaNac = Integer.parseInt(rfc.substring(4, 6));
            }
            if ((new java.util.Date().getYear() - anioFechaNac) > 100) {
                anioFechaNac += 2000;
            } else {
                anioFechaNac += 1900;
            }            
            interfaz.fecha_nac.setDate(new Date(1996, 05,05));
            //interfaz.fecha_nac.setDate(new Date(anioFechaNac,
            //        Integer.parseInt(rfc.substring(5, 7)),
            //       Integer.parseInt(rfc.substring(7, 9))
            // ));
            /*.......................<End>................................................*/

            String puesto = (String) interfaz.cbPuesto.getSelectedItem().toString().toLowerCase();
            java.sql.Date f_nac = new java.sql.Date(interfaz.fecha_nac.getDate().getTime());
            java.sql.Date f_cont = new java.sql.Date(interfaz.fecha_nac.getDate().getTime());
            String estado = (String) interfaz.cb6.getSelectedItem().toString().toLowerCase();
            String url = " ";
            addT(rfc, nombre, ap_paterno, ap_materno, domicilio, puesto, f_nac, f_cont, estado, url);
        } catch (InvalidFormatException ex) {
            if (ex.getMessage().equals("RFC")) {
                JOptionPane.showMessageDialog(null, "RFC Invalido");
            } else {
                Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addT(String rfc, String nombre, String ap_paterno, String ap_materno, String domicilio, String puesto, Date f_nac, Date f_cont, String estado, String urlImage) {
        try {

            PreparedStatement pstm = c.prepareStatement("insert into "
                    + "sistemaTusug.trabajador(rfc,nombre,ap_paterno, ap_materno,domicilio,puesto,fecha_nac,fecha_contratacion,estado,url_img) "
                    + " values(?,?,?,?,?,?,?,?,?,?)");
            pstm.setString(1, rfc);
            pstm.setString(2, nombre);
            pstm.setString(3, ap_paterno);
            pstm.setString(4, ap_materno);
            pstm.setString(5, domicilio);
            pstm.setString(6, puesto);
            pstm.setDate(7, f_nac);
            pstm.setDate(8, f_cont);
            pstm.setString(9, estado);
            pstm.setString(10, urlImage);
            pstm.execute();
            System.out.print("agregado");
        } catch (SQLException ex) {
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminaTrabajador(String rfc) {
        String estado = "baja";

        try {
            PreparedStatement pstm = c.prepareStatement("UPDATE sistemaTusug.trabajador SET "
                    + " estado= ? "
                    + "WHERE rfc = ? ");
            pstm.setString(1, estado);
            pstm.setString(2, rfc.toLowerCase());
            pstm.execute();
            // pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<String> listatrabajador() {
        ArrayList<String> listEmployers = new ArrayList<String>();
        String sql = "select rfc from sistemaTusug.trabajador where estado like 'activo' ORDER BY rfc";
        PreparedStatement pst;
        ResultSet res;
        try {
            //sql = "select rfc from sistemaTusug.trabajador ORDER BY rfc";
            pst = c.prepareStatement(sql);
            res = pst.executeQuery();
            while (res.next()) {
                listEmployers.add(res.getString("rfc"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listEmployers;

    }

    public void modificaTrabajador(String nombre, String ap_paterno, String ap_materno, String domicilio, String puesto, String rfc) {
        nombre = interfaz.tfnom.getText();
        ap_paterno = interfaz.tfapp.getText();
        ap_materno = interfaz.tfapm.getText();
        domicilio = interfaz.area1.getText();
        puesto = ((String) interfaz.cbPuesto.getSelectedItem());
        rfc = interfaz.tfrfc.getText().toLowerCase();

        try {
            PreparedStatement pstm = c.prepareStatement("UPDATE sistemaTusug.trabajador SET "
                    + "nombre= ? ,"
                    + "ap_paterno= ? ,"
                    + "ap_materno= ? ,"
                    + "domicilio= ? ,"
                    + "puesto= ? "
                    + "WHERE rfc =? ");
            pstm.setString(1, nombre);
            pstm.setString(2, ap_paterno);
            pstm.setString(3, ap_materno);
            pstm.setString(4, domicilio);
            pstm.setString(5, puesto);
            pstm.setString(6, rfc);
            pstm.execute();
            // pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

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

    public String[] listaParametro(String rfc) {
        String[] registros = new String[9];
        try {

            PreparedStatement pstm = c.prepareStatement("select * from sistemaTusug.trabajador where rfc=?");
            pstm.setString(1, rfc);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString(1);
                interfaz.tfrfc.setText(registros[0].toUpperCase());
                registros[1] = rs.getString(2);
                interfaz.tfnom.setText(registros[1].toUpperCase());
                registros[2] = rs.getString(3);
                interfaz.tfapp.setText(registros[2].toUpperCase());
                registros[3] = rs.getString(4);
                interfaz.tfapm.setText(registros[3].toUpperCase());
                registros[4] = rs.getString(5);
                interfaz.area1.setText(registros[4].toUpperCase());
                //interfaz.cb1.setSelectedItem(rs.getString("estado").toLowerCase());
                registros[5] = rs.getString(6);
                registros[6] = rs.getString(7);
                registros[7] = rs.getString(8);
                registros[8] = rs.getString(9);
                putImageProfile(rs.getString("url_img"));
            }
            //   pstm.close();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, e);
        }
        return registros;

    }

    public void cambiarImagen() {
        try {
            // Obtener la ruta de la imagen
            String absPathImg = Fachada.getSelectedFileImage();
            // Cargar las variables
            String rfc = interfaz.tfrfc.getText().toLowerCase();
            // Actualizar dato en la Base de datos
            // Preparar Consulta
            Connection conn = Conexion.getConexion();
            PreparedStatement pstm = conn.prepareStatement("UPDATE sistemaTusug.trabajador SET url_img = ? WHERE rfc = ? ;");
            pstm.setString(1, absPathImg);
            pstm.setString(2, rfc);
            pstm.execute();
            //pstm.close();
            // Mostrar la img en el Label
            Icon icon = new ImageIcon(absPathImg);
            interfaz.lb_imagen.setIcon(icon);
        } catch (SQLException e) {
            System.out.println("Error 404: Conexion refusada o algun pedo así");
        } catch (NullPointerException ex) {
            Logger.getLogger(Autobus.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }

    public void putImageProfile(String path) {
        // Replace los simbolos        '\'       por '/'
        String Path = path.replace('\u005C\u005C', '\u002F');
        ImageIcon fot = new ImageIcon(Path);
        Icon icon = new ImageIcon(fot.getImage().getScaledInstance(interfaz.lb_imagen.getWidth(), interfaz.lb_imagen.getHeight(), Image.SCALE_DEFAULT));
        interfaz.lb_imagen.setIcon(icon);
    }

    /*-------------------Funciones Mejoradas-----------------------*/
    public void loadDataFromDB(){
        String sql = "select * from sistemaTusug.trabajador ORDER BY rfc";
        PreparedStatement pst;
        ResultSet res;
        try {
            //sql = "select rfc from sistemaTusug.trabajador ORDER BY rfc";
            pst = c.prepareStatement(sql);
            res = pst.executeQuery();
            TrabajadorEntity rowEmpleado;
            while (res.next()) {
                rowEmpleado = new TrabajadorEntity(res.getString("rfc"), res.getString("nombre"),
                        res.getString("ap_paterno"), res.getString("ap_materno"), res.getString("domicilio"), 
                        res.getString("puesto"), res.getDate("fecha_nac"), res.getDate("fecha_contratacion"), 
                        res.getString("estado"), res.getString("url_img"));
                dataBD.put(rowEmpleado.getRfc(), rowEmpleado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
