package GUI;

import CONTROLLERS.SQLHistMant;
import CONTROLLERS.SQLRepSinies;
import static GUI.ListaInventario.datos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author BARTO
 */
public class ListaSiniestroGUI {

    public JButton lSiniestros, Rsiniestro, regresar, guardar, abrir, imprimir;
    static Object[][] datos;
    public JTable tabla;
    public int valor;
    ListaSiniestroGUI g;
    ActionListener listener;
    SQLRepSinies controlador;
    String ruta = "src/imagenes/";
    JFrame f;
    JPanel p;
    public String user;
    JMenuBar barra;
    JMenu archivo;
    JMenuItem reestablecer;
    JMenuItem Cerrar_Sesion;
    public DefaultTableModel model;


    public ListaSiniestroGUI() {
        controlador= new SQLRepSinies(this);
        user = "Usuario";
        String[] nom= {"Codigo", "Fecha del siniestro", "Tipo"};
        model=new DefaultTableModel(null, nom);

        f = Builder.construirFrame("Lista de Siniestros", new Rectangle(0, 0, 700, 649), false);
        p = Builder.crearPanel(f, new Rectangle(2, 0, 703, 649), ruta + "fondo_lista_siniestros.png", false);

        //Menu

        //botones
        listener = new ReportCustomListener();
        lSiniestros = Builder.crearButtonIcon(p, "listado", ruta + "boton_listado_siniestros_selected.png", new Rectangle(135, 69, 142, 43), listener, true, false);
        Rsiniestro = Builder.crearButtonIcon(p, "reportar", ruta + "boton_reportar_siniestro.png", new Rectangle(313, 69, 142, 43), listener, true, false);
        abrir = Builder.crearButtonIcon(p, "abrir", ruta + "folder.png", new Rectangle(656, 199, 24, 24), new ReportCustomListener(), true, false);
        //imprimir = Builder.crearButtonIcon(p, "imprimir", ruta + "print.png", new Rectangle(656, 260, 24, 24), listener, true, false);
        //guardar = Builder.crearButtonIcon(p, "guardar", ruta + "save.png", new Rectangle(656, 318, 24, 24), listener, true, false);
        regresar = Builder.crearButtonIcon(p, "regresar", ruta + "regresar.png", new Rectangle(326, 518, 39, 39), listener, true, false);

        //Tabla
        controlador.llenarTable();
        tabla = new JTable();      
        tabla.setPreferredSize(new Dimension(576, 329));       
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(54, 145, 576, 329);
        p.add(scrollPane);
        tabla.setModel(model);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
        public void mouseClicked(MouseEvent e) {
        tableMouseClicked(e);
        }
        });
       
        
    
  
    }


    public int tableMouseClicked(MouseEvent evt) {
        int filasele = tabla.getSelectedRow();
        valor = Integer.valueOf(tabla.getValueAt(filasele, 0).toString());
        System.out.println(valor);
        
        return valor;
    }

    class ReportCustomListener implements ActionListener {

        String op;

        @Override
        public void actionPerformed(ActionEvent e) {
            op = e.getActionCommand();
            switch (op) {

                case "abrir":  {
                try {
                    controlador.creaRepor();
                } catch (JRException ex) {
                    Logger.getLogger(ListaSiniestroGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                break;
                case "cerrarSesion":
                case "regresar":
                    f.dispose();
                    break;
            }
        }

    }
}
