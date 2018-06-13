package GUI;
import CONTROLLERS.Conexion;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.*;

/**
 *
 * @author laibr
 */
public class MenuAsignarChoferAutobusGUI {
    String ruta = "src/imagenes/";
    JLabel lb_choferAutobus,img_usuario;
    JButton bt_regresar,bt_asignar,bt_visualizar;
    JPanel fondo_vta;
    JMenuBar barra;
    JMenu archivo;
    String user = "usuario";
    

    
    JFrame ventana;
    MenuAsignarChoferAutobusGUI.CustomActionListener listener;
    Connection conn;
    
    
    public MenuAsignarChoferAutobusGUI(){
 
        
        //Creacion de ventana del menu.
        ventana   = Builder.construirFrame("Menu chofer-autobus", new Rectangle(0,0,700,600),                              false);
        fondo_vta = Builder.crearPanel(ventana,                   new Rectangle(0,0,700,600),ruta + "fondo_frame_sec.png", false);
        
        //Listener
         listener =  new CustomActionListener();
         
         //Botones
        bt_regresar   =  Builder.crearButtonIcon(fondo_vta,     "regresar",ruta+"regresar.png",                                           new Rectangle(326,518,39,39),  listener ,true, true); //bt_regresar.setBorder(null);
        bt_asignar    =  Builder.crearButtonIcon(fondo_vta,     "reportar",ruta+"boton_seccion_asignar_chofer_autobus.png",               new Rectangle(104,162,205,331),listener ,true, true); //bt_rep_siniestro.setBorder(null);
        bt_visualizar =  Builder.crearButtonIcon(fondo_vta,     "visualizar",ruta+"boton_seccion_visualizar_relacion_chofer_autobus.png", new Rectangle(380,162,205,331),listener ,true, true); //bt_vis_siniestro.setBorder(null);
        
        //Imagen de usuario
        img_usuario   = Builder.crearLabelImagen(fondo_vta, ruta+"user.png", new Rectangle(547,109,32,32));
        
        //Etiqueta "Asignar chofer-autobus"
        lb_choferAutobus = Builder.crearLabel(fondo_vta,"Asignar Chofer-Autobus",           new Rectangle(263,97,175,18),new Color(0,204,204), null, new Font("Segoe UI", Font.BOLD, 14)); 
        
        
    }
    
    
    class CustomActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String x = e.getActionCommand();
            
            switch(x){
                case("regresar"):      ventana.dispose();                                          break;
                case("reportar"):   GUIAutobusChofer repor         = new GUIAutobusChofer();       break;
                case("visualizar"): GUIListaChoferAutobus lista    = new GUIListaChoferAutobus();  break;   
                case "Cerrar Sesion":
                   LoginGUI login = new LoginGUI();                   
                   ventana.dispose();
                   break;
                
            
        }
            
        }
        }
    
    
}
