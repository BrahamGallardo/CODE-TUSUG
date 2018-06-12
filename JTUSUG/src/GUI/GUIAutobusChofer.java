package GUI;
import CONTROLLERS.ControladorChoferAutobus;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import static java.util.Collections.list;
import static Validacion.Validador.*;
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

public class GUIAutobusChofer {
    String ruta = "src/imagenes/";
    public JList lista, list;
    public JScrollBar   scroll; 
    public JButton btActualizar,confirm,regresar;
    public JLabel lTrabajadores,dGeneral,nombre,apP,apM,estado,cAutobus,matricula,marca,km;
    public JTextField name,ap_paterno,ap_materno,state,matric,marc,kilometraje, combo;
   
    public String user;
    JMenuBar barra;
    JMenu archivo;
    JMenuItem reestablecer;
    JMenuItem Cerrar_Sesion;
    JFrame x;    
    JPanel p;
    ActionListener listener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            x.dispose();
        }
    };
    ControladorChoferAutobus controlador;
    public GUIAutobusChofer()
    {
        ControladorChoferAutobus ui= new ControladorChoferAutobus(this);
        controlador=ui;
        user = "Usuario";
        x = Builder.construirFrame("Trabajador", new Rectangle(0,0,700,600), false); 
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p = Builder.crearPanel(x, new Rectangle(0,0,700,600),ruta + "fondo_vta_chofer_autobus.png", false);

        //---------<botones>-------------------------
        btActualizar =  Builder.crearButtonIcon( p,     "Actualizar",ruta+"boton_actualizar_lista.png", new Rectangle(89,444,145,36),listener ,true, false);      
        confirm =       Builder.crearButtonIcon(p,      "confirmar", ruta+"btn_confirmar.png",          new Rectangle(409,451,114,43), new  CustomActionListener(), true, false);
        regresar =      Builder.crearButtonIcon(p,      "regresar",  ruta+"regresar.png",               new Rectangle(326,513, 41,41), listener, true, false);

        //---------<Labels>-------------------------
        lTrabajadores = Builder.crearLabel(p,"Lista de Trabajadores",   new Rectangle(65,137,136,20),new Color(66,66,66), null, new Font("Segoe UI", Font.PLAIN, 14)); 
        dGeneral =      Builder.crearLabel(p,"Descripcion General",     new Rectangle(317,129,121,20),new Color(66,66,66), null, new Font("Segoe UI", Font.PLAIN, 14)); 
        nombre =        Builder.crearLabel(p,"Nombre:",                 new Rectangle(266,167,55,20),new Color(66,66,66), null, new Font("Segoe UI", Font.PLAIN, 14)); 
        apP =           Builder.crearLabel(p,"Ap. Paterno:",            new Rectangle(242,200,76,20),new Color(66,66,66), null, new Font("Segoe UI", Font.PLAIN, 14)); 
        apM =           Builder.crearLabel(p,"Ap. Materno:",            new Rectangle(239,240,80,20),new Color(66,66,66), null, new Font("Segoe UI", Font.PLAIN, 14)); 
        estado =        Builder.crearLabel(p,"Estado:",                 new Rectangle(271,270,45,20),new Color(66,66,66), null, new Font("Segoe UI", Font.PLAIN, 14)); 
        cAutobus =      Builder.crearLabel(p,"Codigo Autobus:",         new Rectangle(219,333,102,20),new Color(66,66,66), null, new Font("Segoe UI", Font.PLAIN, 14)); 
        matricula =     Builder.crearLabel(p,"Matricula:",              new Rectangle(258,366,62,20),new Color(66,66,66), null, new Font("Segoe UI", Font.PLAIN, 14)); 
        marca =         Builder.crearLabel(p,"Marca:",                  new Rectangle(276,398,43,20),new Color(66,66,66), null, new Font("Segoe UI", Font.PLAIN, 14)); 
        km =            Builder.crearLabel(p,"Kilometraje:",            new Rectangle(245,433,73,20),new Color(66,66,66), null, new Font("Segoe UI", Font.PLAIN, 14)); 
        
        //lista
         lista= new JList();
         p.add(lista);
         lista.setBounds(new Rectangle(65,167,140,280));
         lista.setVisible(true);
         lista.addMouseListener(new CustomMouseListener());
         controlador.cargarLista(lista);
         
         list= new JList();
         p.add(list);
         list.setBounds(new Rectangle(468, 167, 140,280));
         list.setVisible(true);
         list.addMouseListener(new CustomMouseListener2());
         controlador.cargarListaAutobus(list);
         
         //lista.addMouseListener(new TrabajadorGUI.CustomMouseListener());
         
         //JTextField
         name =           Builder.crearTextField(p,new Rectangle(331,163,129,25), "", null, null, new Font("Segoe UI", Font.PLAIN, 11), false,true, true,listener);
         ap_paterno =     Builder.crearTextField(p,new Rectangle(331,198,129,25), "", null, null, new Font("Segoe UI", Font.PLAIN, 11), false,true, true,listener);
         ap_materno=      Builder.crearTextField(p,new Rectangle(331,233,129,25), "", null, null, new Font("Segoe UI", Font.PLAIN, 11), false,true, true,listener);
         state =          Builder.crearTextField(p,new Rectangle(331,268,129,25), "", null, null, new Font("Segoe UI", Font.PLAIN, 11), false,true, true,listener);
         matric =         Builder.crearTextField(p,new Rectangle(331,363,129,25), "", null, null, new Font("Segoe UI", Font.PLAIN, 11), false,true, true,listener);
         marc =           Builder.crearTextField(p,new Rectangle(331,398,129,25), "", null, null, new Font("Segoe UI", Font.PLAIN, 11), false,true, true,listener);
         kilometraje =    Builder.crearTextField(p,new Rectangle(331,433,129,25), "", null, null, new Font("Segoe UI", Font.PLAIN, 11), false,true, true,listener);
  
         //JComboBox
         combo= Builder.crearTextField(p, new Rectangle(331,331,129,22),"",null , null, new Font("Segoe UI", Font.PLAIN, 11),false,true, true, listener);
         valida();
    }
    public void valida()
    {
        name.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validaNombre(evt,name,20);
            }
        });
        kilometraje.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validaNum(evt,kilometraje,10);
            }
        });
        ap_paterno.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validaNombre(evt,ap_paterno,20);
            }
        });
        marc.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validaNombre(evt,marc,20);
            }
        });
        ap_materno.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validaNombre(evt,ap_materno,20);
            }
        });     
        
        state.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validaNombre(evt,state,20);
            }
        });
        matric.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validaAlfanumerico(evt,matric,10);
            }
        });
    }
    public static void main(String[] args){
        GUIAutobusChofer s= new GUIAutobusChofer();
    }
 
    
            class CustomMouseListener extends MouseAdapter{
            public void mouseClicked(MouseEvent me){
            if(lista.getSelectedValue()!=null){
                String matricula1 = (String)lista.getSelectedValue();
                try {
                    controlador.listaParametro(matricula1.toLowerCase());
                } catch (SQLException ex) {
                    Logger.getLogger(GUIAutobusChofer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
                System.err.println("Nada que elegir");
        };
    }
            
            class CustomMouseListener2 extends MouseAdapter{
            public void mouseClicked(MouseEvent me){            
            if(list.getSelectedValue()!=null){
                String matricula1 = (String)list.getSelectedValue();
                try {
                    controlador.listaParametroAutobus(matricula1.toLowerCase());
                } catch (SQLException ex) {
                    Logger.getLogger(GUIAutobusChofer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
                System.err.println("Nada que elegir");
        };
    }
         
      class CustomActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(validaIngreso(name,ap_paterno,ap_materno,state,matric,marc,kilometraje)){
            controlador.addT();
           JOptionPane.showMessageDialog(null,"Chofer asignado");

            } 
            else
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Error..!!", JOptionPane.ERROR_MESSAGE);
        }
        }
            
}
