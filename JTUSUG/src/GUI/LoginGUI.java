package GUI;
import Servicios.Encriptar;
import CONTROLLERS.Conexion;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Alekhius
 */
public class LoginGUI {

    private HashMap<String, Sesion> sesiones;

    private String rol;
    private String nombre_usuario;
    private String rfc;
    private String contrasenia;

    JFrame frame;
    JPanel loginUI;
    JTextField txt_rfc;
    JTextField txt_password;
    JButton b;
    Connection conn;

    public LoginGUI() {
        Conexion.setRol("root");
        conn = Conexion.getConexion();
        sesiones = new HashMap<String, Sesion>();
        loadSesiones();
        initComponents();
        for (Sesion s: sesiones.values())
            System.out.println(s.toString());
    }
    public static void main(String [] args){
        new LoginGUI();
    }
    public void loadSesiones(){
        try {
            String query = "SELECT * from sistemaTusug.sesiones;";                         
            PreparedStatement s = conn.prepareStatement(query);
            ResultSet rs = s.executeQuery();
            
            Sesion aux;
            while (rs.next()) {                
                rfc =               rs.getString("rfc");
                nombre_usuario =    rs.getString("nombre");
                rol =               rs.getString("puesto");                
                contrasenia =       rs.getString("contrasenia");
                aux = new Sesion(rfc, nombre_usuario, rol, contrasenia);
                sesiones.put(rfc, aux);
                
            }            
            rs.close();
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void  initComponents() {
        Font font = new Font("Segoe UI", Font.BOLD, 18);
        Conexion.setConfiguracion("postgres", "root");
        conn = Conexion.getConexion();
        CustomActionListener escucha = new CustomActionListener();
        frame = Builder.construirFrame("Sistema Integral Tusug Login", new Rectangle(0, 0, 700, 600), false);
        loginUI = Builder.crearPanel(frame, new Rectangle(0, 0, 700, 600), "src/Imagenes/login.png", true);
        txt_rfc = Builder.crearTextField(loginUI, new Rectangle(205, 233, 293, 38), "", null, null, null, true, true, true);
        txt_password = Builder.crearPasswordField(loginUI, new Rectangle(205, 298, 293, 38), "", null, null, null, true, true);
        b = Builder.crearBoton(loginUI, "Ingresar", new Rectangle(257, 383, 185, 39), escucha, false, false);

        txt_rfc.addKeyListener(new CustomKeyListener());
        txt_rfc.setFont(font);
        txt_rfc.setBackground(new Color(0xe4, 0xe4, 0xe4));

        txt_password.setFont(font);
        txt_password.addKeyListener(new CustomKeyListener());
        txt_password.setBackground(new Color(0xe4, 0xe4, 0xe4));

        b.addKeyListener(new CustomKeyListener());
    }

    public void iniciarSesion() throws Exception {
        String inputPass = txt_password.getText();
        String inputRFC = txt_rfc.getText();
        Sesion userValid = sesiones.get(inputRFC);
        if (userValid!=null && Encriptar.md5(inputPass).equals(userValid.password)){
            RootGUI main = new RootGUI(userValid.puesto, userValid.nombre, "src/imagenes/icono usuario.jpg");
            frame.dispose();
        }else javax.swing.JOptionPane.showMessageDialog(null, "Error: Usuario o Contraseña invalidos\nIntente otra vez");
    }
    
    
    class CustomActionListener implements ActionListener {

        String comando = "";

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                comando = e.getActionCommand();
                switch (comando) {
                    case "Ingresar":
                        iniciarSesion();
                        break;
                }
            } catch (Exception ex) {

            }
        }
    }

    class CustomKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == 10) {
                try {
                    iniciarSesion();
                } catch (Exception ex) {
                }
            }
        }
    }

    class Sesion {

        String rfc;
        String nombre;
        String puesto;
        String password;

        public Sesion(String rfc, String nombre, String puesto, String password) {
            this.rfc = rfc;
            this.nombre = nombre;
            this.puesto = puesto;
            this.password = password;
        }

        @Override
        public String toString() {
            return "Sesion{" + "rfc=" + rfc + ", nombre=" + nombre + ", puesto=" + puesto + ", password=" + password + '}';
        }

       

        public Sesion() {
        }
    }
}




