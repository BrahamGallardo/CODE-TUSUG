package CONTROLLERS;

import CustomException.InvalidFormatException;
import Servicios.Fachada;
import java.sql.Date;
import GUI.TrabajadorGUI;
import Validacion.Validador;
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
            String domicilio = interfaz.txtA_direccion.getText().toLowerCase();
            /*--------Intermedio para obtener fecha de nac y validar esa parte-------*/

            int anioFechaNac, month, day;
            try {
                anioFechaNac = Integer.parseInt(rfc.substring(3, 5));
                month = Integer.parseInt(rfc.substring(5, 7));
                day = Integer.parseInt(rfc.substring(7, 9));
            } catch (NumberFormatException ex) {
                anioFechaNac = Integer.parseInt(rfc.substring(4, 6));
                month = Integer.parseInt(rfc.substring(6, 8));
                day = Integer.parseInt(rfc.substring(8, 10));
            }
            if ((new java.util.Date().getYear() - anioFechaNac) > 100) {
                anioFechaNac += 100;
            }
            java.sql.Date nac = new java.sql.Date(anioFechaNac, month-1, day);
            java.sql.Date contrato = new java.sql.Date(new java.util.Date().getTime());
            interfaz.txt_fechaNac.setText(nac.toString());
            //interfaz.fecha_nac.setDate(new Date(anioFechaNac,
            //        Integer.parseInt(rfc.substring(5, 7)),
            //       Integer.parseInt(rfc.substring(7, 9))
            // ));
            /*.......................<End>................................................*/

            String puesto = (String) interfaz.combo_puesto.getSelectedItem().toString().toLowerCase();
            
            //java.sql.Date f_nac = new java.sql.Date(interfaz.fecha_nacimiento.getDate().getTime());
            //java.sql.Date f_cont = new java.sql.Date(interfaz.fecha_nacimiento.getDate().getTime());
            String estado = (String) interfaz.combo_estadoLaboral.getSelectedItem().toString().toLowerCase();
            String url = " ";
            agregarTrabajador(rfc, nombre, ap_paterno, ap_materno, domicilio, puesto, nac, contrato, estado, url);
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

    public void agregarTrabajador(String rfc, String nombre, String ap_paterno, String ap_materno, String domicilio, String puesto, Date f_nac, Date f_cont, String estado, String urlImage) {
        //------<Validating>----------
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
            System.out.println("agregado");
        } catch (SQLException ex) {
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminaTrabajador(String rfc) {
        String estado = "baja";

        try {
            PreparedStatement pstm = c.prepareStatement("UPDATE sistemaTusug.trabajador SET "
                    + " estado= ? "
                    + "WHERE lower(rfc) = ? ");
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
        String sql = "select rfc from sistemaTusug.trabajador where lower(estado) like 'activo' ORDER BY rfc";
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
        domicilio = interfaz.txtA_direccion.getText();
        puesto = ((String) interfaz.combo_puesto.getSelectedItem());
        rfc = interfaz.tfrfc.getText().toLowerCase();

        try {
            PreparedStatement pstm = c.prepareStatement("UPDATE sistemaTusug.trabajador SET "
                    + "nombre= ? ,"
                    + "ap_paterno= ? ,"
                    + "ap_materno= ? ,"
                    + "domicilio= ? ,"
                    + "puesto= ? "
                    + "WHERE lower(rfc) =? ");
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

            PreparedStatement pstm = c.prepareStatement("select * from sistemaTusug.trabajador where lower(rfc)=?");
            pstm.setString(1, rfc);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                interfaz.tfrfc.setText(rs.getString("rfc").toUpperCase());
                interfaz.tfnom.setText(rs.getString("nombre").toUpperCase());
                interfaz.tfapp.setText(rs.getString("ap_paterno").toUpperCase());
                interfaz.tfapm.setText(rs.getString("ap_materno").toUpperCase());
                interfaz.txtA_direccion.setText(rs.getString("domicilio").toUpperCase());
                interfaz.combo_puesto.          setSelectedItem(rs.getString("puesto"));
                interfaz.txt_fechaNac.  setText(rs.getString("fecha_nac"));
                //--Falta Fecha de contratacion, no hay un campo para visualizarlo :|
                interfaz.combo_estadoLaboral.  setSelectedItem(rs.getString("estado"));
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
            PreparedStatement pstm = conn.prepareStatement("UPDATE sistemaTusug.trabajador SET url_img = ? WHERE lower(rfc) = ? ;");
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

