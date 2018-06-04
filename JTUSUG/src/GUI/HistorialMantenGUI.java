
package GUI;

import CONTROLLERS.Conexion;
import CONTROLLERS.SQLHistMant;
import Validacion.Validador;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;


public class HistorialMantenGUI {
    String              ruta = "src/imagenes/";
    ActionListener      listener;
    Validador           valida;
    public JTable        tabla;
    public JTextField   txt_no_manten,txt_fecha;
    public JFileChooser url_img;
    public JButton      btn_buscar,btn_abrir,btn_imprimir,btn_guardar,btn_regresar,btn_cerrarSesion;
    public JLabel       lb_no_manten,lb_fecha,lb_historial_manten;
    public JDateChooser dateChooser;
    public SQLHistMant controlador;
    public int valor;
     HistorialMantenGUI g;
    
    JPanel p;
    JFrame a;
    Object[][] dtPer;
    SQLHistMant interfaz;
    public HistorialMantenGUI()
    {
        SQLHistMant ui= new SQLHistMant(this);
        interfaz=ui;
        g = this;
        a=Builder.construirFrame("Historial de Mantenimiento", new Rectangle(200,50,700,610),false);
        inicializarComp();
        tabla.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) 
            {
                tableMouseClicked(evt);
            }
        });                
    }
    
    public void inicializarComp()
    {  p = Builder.crearPanel(a, new Rectangle(0, 0, 700, 600),"", false );
        //JCalendar
        dateChooser = new JDateChooser ();
        a.add(dateChooser);
        dateChooser.setBounds(500,90,170,22);
        btn_abrir        = Builder.crearButtonIcon(a,"abrir",ruta + "folder.png",                  new Rectangle(643,182,24,24) ,new ReportCustomListener(),false,false);
        btn_imprimir     = Builder.crearButtonIcon(a,"imprimir",ruta + "print.png",                new Rectangle(643,243,24,24) ,listener,false,false);
        btn_buscar       = Builder.crearButtonIcon(a,"buscar",ruta + "btn_buscar.png",             new Rectangle(54,89,74,21)   ,listener,false,false);
        btn_regresar     = Builder.crearButtonIcon(a,"regresar",ruta + "regresar.png",             new Rectangle(326,518,39,39) ,listener,false,false);
        btn_guardar      = Builder.crearButtonIcon(a,"guardar",ruta + "save.png",                  new Rectangle(643,301,24,24) ,listener,false,false);
        btn_cerrarSesion = Builder.crearButtonIcon(a,"cerrarSesion",ruta + "cerrar_sesion.png",    new Rectangle(460,506,201,63),listener,false,false);
        
        
        lb_no_manten         =   Builder.crearLabel(a, "No. Mantenimiento:",                new Rectangle(158,85,125,28) ,null,null);
        lb_fecha             =   Builder.crearLabel(a, "Fecha:",                            new Rectangle(461,85,45,28)  ,null,null);
        lb_historial_manten  =   Builder.crearLabel(a, "Historial de mantenimiento",        new Rectangle(260,170,170,18),null,null);
        
        
        txt_no_manten =    Builder.crearTextField(a, new Rectangle(293, 88, 131, 22), "", null, null,new Font("Segoe UI", Font.BOLD, 10),true,true,true);
        
        
        
        
        JLabel fondo    =   Builder.crearLabelImagen(a, ruta + "fondo_mant.png", new Rectangle(0,0,700,600));
        
        String lista[] = {"num. de manten","Responsable","Fecha de emision"};
        controlador=new SQLHistMant(g);
        Object[][] datos =controlador.obtenerRegistro();
        
        tabla = new JTable(datos,lista);
        tabla.setPreferredSize(new Dimension(515,277));
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(69,210,515,277);
        p.add(scrollPane);    
    }
    
    private void updateTabla(){             
        String[] columNames = {"Num. Mantenimiento", "Responsable", "Fecha de emisión"};  
        // se utiliza la funcion
        //dtPer = p.getDatos();
        // se colocan los datos en la tabla
        Object[][] registro =controlador.obtenerRegistro();
        DefaultTableModel datos = new DefaultTableModel(registro,columNames);                        
        tabla.setModel(datos); 
    }
    
    public static void main(String []args)
    {
        //Conexion.setConfiguracion("postgres", "root");
        HistorialMantenGUI a= new HistorialMantenGUI();
    }
    public void registro(){
        txt_no_manten.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
               
            }
        });
    }
    
    class ReportCustomListener implements ActionListener{
        String op;
        @Override
        public void actionPerformed(ActionEvent e) {
            op = e.getActionCommand();
            switch (op){
                
                case "abrir":
                    
            {
                try {
                  interfaz.creaRepor();
                } catch (JRException ex) {
                    Logger.getLogger(GUIReporteManten.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                case "Cerrar Sesion":
                    break;
                case "Regresar":
                    break;
            }
        }
        
    }
    
    public int tableMouseClicked(MouseEvent evt)
    {
    	int filasele= tabla.getSelectedRow();
    	 valor= Integer.valueOf(tabla.getValueAt(filasele, 0).toString());
        System.out.println(valor);
        return valor;
    }
}