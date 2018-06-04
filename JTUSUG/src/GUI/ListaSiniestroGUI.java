package GUI;
import static GUI.ListaInventario.datos;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author BARTO
 */
public class ListaSiniestroGUI {
    public JButton lSiniestros,Rsiniestro,regresar,guardar,abrir,imprimir;
    static String encabezado[]  = {"Referencia","Tipo de Siniestro","Fecha de Alta","Responsable"};
     static Object [][]datos = {{"","","",""},
                                {"","","",""},
                                {"","","",""},
                                {"","","",""},
                                {"","","",""},
                                {"","","",""},
                                {"","","",""},
                                {"","","",""},
                                {"","","",""},
                                {"","","",""}};
     public JTable tabla;
    ActionListener listener;
    DefaultTableModel dtm;
    String ruta = "src/imagenes/";
    JFrame f;
    JPanel p;
    public String user;
    JMenuBar barra;
    JMenu archivo;
    JMenuItem reestablecer;
    JMenuItem Cerrar_Sesion;
    public ListaSiniestroGUI()
    {
        user = "Usuario";
        f = Builder.construirFrame("Lista de Siniestros", new Rectangle(0,0, 700, 649), false); 
        p = Builder.crearPanel(f, new Rectangle(2, 0, 703, 649),ruta+"fondo_lista_siniestros.png", false);
        
        //Menu
         barra=new JMenuBar();
         barra.setBackground(Color.GRAY);
         archivo=new JMenu(user);
         reestablecer = new JMenuItem("Reestablecer Contraseña");
         Cerrar_Sesion= new JMenuItem("Cerrar Sesión");
         archivo.add(reestablecer);
         archivo.add(Cerrar_Sesion);
         barra.add(archivo);
         p.add(barra);
         barra.setBounds(new Rectangle(513,75,55,34));
         barra.setVisible(true);
         
          //botones
         lSiniestros = Builder.crearButtonIcon(p, "listado", ruta+"boton_listado_siniestros_selected.png",new Rectangle(135,69,142,43), listener, true, false);
         Rsiniestro = Builder.crearButtonIcon(p, "reportar", ruta+"boton_reportar_siniestro.png",new Rectangle(313,69,142,43), listener, true, false);
         abrir = Builder.crearButtonIcon(p,"abrir", ruta+"folder.png",new Rectangle(656,199,24,24), listener, true,false);
         imprimir = Builder.crearButtonIcon(p,"imprimir", ruta+"print.png",new Rectangle(656,260,24,24), listener, true,false);
         guardar = Builder.crearButtonIcon(p,"guardar", ruta+"save.png",new Rectangle(656,318,24,24), listener, true,false);
         regresar = Builder.crearButtonIcon(p, "regresar", ruta+"regresar.png",new Rectangle(326,518,39,39), listener, true, false);
         
          //Tabla
        dtm= new DefaultTableModel();
        tabla = new JTable(datos,encabezado);
        //tabla.setBackground(Color.GRAY);
        tabla.setPreferredSize(new Dimension(576,329));
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(54,145,576,329);
        p.add(scrollPane);
         
    }
    public static void main(String []args)
    {
        ListaSiniestroGUI l = new ListaSiniestroGUI();
    }
}
