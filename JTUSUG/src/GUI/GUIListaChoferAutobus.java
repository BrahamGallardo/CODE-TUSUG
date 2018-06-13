
package GUI;


import CONTROLLERS.ControladorChoferAutobus;
import CONTROLLERS.Expedientes;
import CONTROLLERS.ListaAutobusChofer;
import static GUI.GUIExpedientes.encabezado;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;


public class GUIListaChoferAutobus {
      
    public JButton lexpediente, Rexpdiente, regresar, guardar, abrir, imprimir;
    static String encabezado[] = {"RFC", "MATRICULA"};
    static Object[][] datos;
    public JTable tabla;
    public String valor;
    ActionListener listener;
    String ruta = "src/imagenes/";
    JFrame f;
    JPanel p;
    public String user;
    JMenuBar barra;
    JMenu archivo;
    JMenuItem reestablecer;
    JMenuItem Cerrar_Sesion;
    ListaAutobusChofer controlador;
    public DefaultTableModel model;
    
    public GUIListaChoferAutobus(){
        valor="";
        model=new DefaultTableModel(null, encabezado);
        controlador= new ListaAutobusChofer(this);
        user = "Usuario";
        f = Builder.construirFrame("Lista de Relaciones", new Rectangle(0, 0, 700, 649), false);
        p = Builder.crearPanel(f, new Rectangle(2, 0, 703, 649), ruta + "fondo_lista_siniestros.png", false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listener = new ReportCustomListener();
        
       // abrir = Builder.crearButtonIcon(p, "abrir", ruta + "folder.png", new Rectangle(656, 199, 24, 24), new ReportCustomListener(), true, false);
        imprimir = Builder.crearButtonIcon(p, "eliminar", ruta + "boton-x.png", new Rectangle(656, 260, 24, 24), listener, true, false);
        regresar = Builder.crearButtonIcon(p, "regresar", ruta + "regresar.png", new Rectangle(326, 518, 39, 39), listener, true, false);
        controlador.llenarTable();
        tabla = new JTable()
                        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
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
    
    public static void main(String[] args){
        GUIListaChoferAutobus d= new GUIListaChoferAutobus();
    }



    public String tableMouseClicked(MouseEvent evt) {
        int filasele = tabla.getSelectedRow();
        valor =tabla.getValueAt(filasele,0).toString();
        System.out.println(valor);
        return valor;
        
    }
    
      public void eliminar(){
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        int a = tabla.getRowCount()-1;
        for (int i = a; i >= 0; i--) {           
        tb.removeRow(tb.getRowCount()-1);
        } 
        //cargaTicket();
    }



    class ReportCustomListener implements ActionListener{
        String op;
        @Override
        public void actionPerformed(ActionEvent e) {
            op = e.getActionCommand();
            switch (op){
                
                case "abrir":
                    
                    break;
                case "cerrarSesion":
                case "eliminar":
            {
                try {
                    controlador.borrarAutobusBy();
                    eliminar();
                    controlador.llenarTable();
                } catch (SQLException ex) {
                    Logger.getLogger(GUIListaChoferAutobus.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            break;
                case "regresar":
                    
                    f.dispose();
                    break;
            }
        }
        
    }

    }

