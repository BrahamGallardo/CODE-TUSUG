package GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class GUIMenuSiniestros {
    String ruta = "src/imagenes/";
    JLabel lb_siniestros,img_usuario;
    JButton bt_regresar,bt_rep_siniestro,bt_vis_siniestro;
    JPanel fondo_vta;
    JMenuBar barra;
    JMenu archivo;
    String user = "usuario";
     JMenuItem reestablecer,Cerrar_Sesion;
    JFrame ventana;
    
    public GUIMenuSiniestros(){
        
        //Ventana
        ventana = Builder.construirFrame("Menu siniestros", new Rectangle(0,0,700,600), false);
        
        //Fondo de la ventana
        fondo_vta = Builder.crearPanel(ventana, new Rectangle(0,0,700,600),ruta + "fondo_frame_sec.png", false);
        //MenuBar
        barra = new JMenuBar();
        barra.setEnabled(true);
        archivo = new JMenu(user);
        archivo.setForeground(Color.GRAY);
        reestablecer = new JMenuItem("Reestablecer Contraseña");
        Cerrar_Sesion = new JMenuItem("Cerrar Sesión");
        archivo.add(reestablecer);
        archivo.add(Cerrar_Sesion);
        barra.add(archivo);
        fondo_vta.add(barra);
        barra.setBounds(new Rectangle(589, 116, 67, 18));
        barra.setVisible(true);
        //Botones
        bt_regresar      =  Builder.crearButtonIcon(fondo_vta,     "Salir",ruta+"regresar.png",                         new Rectangle(326,518,39,39),  /*Listener*/null ,true, true); bt_regresar.setBorder(null);
        bt_rep_siniestro =  Builder.crearButtonIcon(fondo_vta,     "reportar",ruta+"imagen_reportar_siniestro.png",     new Rectangle(104,162,205,331),/*Listener*/null ,true, true); bt_rep_siniestro.setBorder(null);
        bt_vis_siniestro =  Builder.crearButtonIcon(fondo_vta,     "visualizar",ruta+"imagen_visualizar_siniestro.png", new Rectangle(380,162,205,331),/*Listener*/null ,true, true); bt_vis_siniestro.setBorder(null);
        
        //Imagen de usuario
        img_usuario   = Builder.crearLabelImagen(fondo_vta, ruta+"user.png", new Rectangle(547,109,32,32));
        
        //Label "Siniestros"
        lb_siniestros = Builder.crearLabel(fondo_vta,"Siniestros",           new Rectangle(310,97,71,18),new Color(0,204,204), null, new Font("Segoe UI", Font.BOLD, 14)); 
        
        
    }
    
    public static void main(String[] args){
        GUIMenuSiniestros x = new GUIMenuSiniestros();
    
    }
}
