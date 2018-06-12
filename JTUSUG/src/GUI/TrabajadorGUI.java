package GUI;
import CONTROLLERS.Conexion;
import CONTROLLERS.Trabajador;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import Validacion.Validador;
import static Validacion.Validador.*;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;
import Validacion.*;
public class TrabajadorGUI {
    //----------------------------------<Validacion>-----------------------
    TrabajadorActionListener eventos;    
    Validador valida;
    //----------------------------------<Reglas de la empresa>--------------
    String              Cargo[]={"secretaria", "chofer", "mantenimiento", "recursos humanos", "almacen"};
    String              Categoria[]= {"1","2","3","4","5"};        
    String              st[]={"activo", "pasivo", "baja"};
    
    //---------------------------------<Componentes>------------------------
    JFrame x;    
    JPanel p;    
    public JLabel       trabajadores, lb_imagen;    
    public JTextField   tfrfc, tfapp, tfapm, tfnom, tfgen,  tfeda, tffna, tfsa,             
                        tfdir, tffin, tftce, tfema,tftca,buscador;    
    public JTextArea    txtA_direccion;    
    public JComboBox    combo_estadoLaboral,combo_puesto;

    //public JDateChooser        fecha_nacimiento;
    public JTextField          txt_fechaNac;
    public LocalDate    fecha1,fecha2;    
    public int          dia1,mes1,anio1;    
    public JList        lista;    
    public JButton      btInicio,btLT,btBuscar,btActualizar,sesion,back,nuevo,            
                        agregar,baja,btlista,btactinac, Cfoto,btn_guardar,btn_cancelar,btn_guardar_mod;    
    public JScrollBar   scroll;    
    public Date         fecha,fechaA;
    
    //-------------------------------<Controlador>------------------------------
    Trabajador          interfaz;

    
    public TrabajadorGUI(){
        initComponents();
        cargarLista(lista);
    }
    
    
    ActionListener closeSesion = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            x.dispose();
        }
        
    };
    
    private void initComponents(){
        interfaz= new Trabajador(this);
        eventos = new TrabajadorActionListener();

        x = Builder.construirFrame("Trabajador", new Rectangle(0,0,700,600), false); 
            x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p = Builder.crearPanel(x, new Rectangle(0,0,700,518),"src/imagenes/fondo_ventana_2.png", false);
        
        Color color = new Color(233,233,233);
        //etiquetas
        lb_imagen = Builder.crearLabelImagen( p,"src/imagenes/icono usuario.jpg",new Rectangle(539, 130, 128, 128));
        trabajadores =  Builder.crearLabel( p, "Trabajadores",          new Rectangle(65, 130, 80, 20), null, null, new Font("Segoe UI",Font.PLAIN,11));
        JLabel jlrfc=   Builder.crearLabel( p, "RFC: ",                 new Rectangle(351,143,28, 13),  null, null, new Font("Segoe UI",Font.PLAIN,11));
        JLabel jlapp=   Builder.crearLabel( p, "Apellido Paterno: ",    new Rectangle(285,216,95, 13),  null, null, new Font("Segoe UI",Font.PLAIN,11));
        JLabel jlapm=   Builder.crearLabel( p, "Apellido Materno: ",    new Rectangle(282,245,100, 13), null, null, new Font("Segoe UI",Font.PLAIN,11));
        JLabel jlnom=   Builder.crearLabel( p, "Nombre(s): ",           new Rectangle(319,179,60, 13),  null, null, new Font("Segoe UI",Font.PLAIN,11));
        JLabel jlfna=   Builder.crearLabel( p, "Fecha nac. :",          new Rectangle(270, 311,70, 26), null, null, new Font("Segoe UI",Font.PLAIN,11));
        JLabel jlsts=   Builder.crearLabel( p, "Status :",              new Rectangle(270, 290,131, 17),null, null, new Font("Segoe UI",Font.PLAIN,11));
        JLabel jldir=   Builder.crearLabel( p, "Domicilio :",           new Rectangle(322, 378,54, 13), null, null, new Font("Segoe UI",Font.PLAIN,11));
        JLabel puesto=  Builder.crearLabel( p, "Puesto :",              new Rectangle(322, 440,50, 13), null, null, new Font("Segoe UI",Font.PLAIN,11));
        
        
        //-----------------------------------------------<Botones para el flujo del programa>-------------------------------------------
        btInicio =      Builder.crearButtonIcon( p, "regresar",               "src/imagenes/boton_inicio.png",                        new Rectangle(14, 63, 88, 43),   closeSesion,   true, false);
        //btBuscar =      Builder.crearButtonIcon( p, "Buscar",               "src/imagenes/buscar.png",                              new Rectangle(26,185,32,32) , null,   true, false);
        btActualizar =  Builder.crearButtonIcon( p, "Actualizar",           "src/imagenes/boton_actualizar_lista.png",              new Rectangle(26, 455, 145, 36), actualizarT,true, false);
        //sesion =        Builder.crearButtonIcon( p, "cerrarSesion",        "src/imagenes/boton_cerrar_sesion.png",                 new Rectangle(539, 65, 130, 27), null,   true, false);
        back =          Builder.crearButtonIcon( p, "regresar",             "src/imagenes/regresar.png",                            new Rectangle(626, 452, 32, 32), regresarV,true, false);
      
        
        //-----------------------------------------------<Botones CRUD del empleado>----------------------------------------------------
        nuevo=          Builder.crearButtonIcon( p, "Nuevo Empleado",       "src/imagenes/agregar-usuario.png",                     new Rectangle(218, 140, 32, 32), nuevoT, true, false,true,color);
        agregar=        Builder.crearButtonIcon( p, "Modificar Empleado",   "src/imagenes/anadir-punto-de-anclaje.png",             new Rectangle(218, 202, 32, 32), modificarT ,false, false,true,color);
        btn_cancelar=   Builder.crearButtonIcon( p, "cancelar",             "src/imagenes/btn_cancelar.png",                        new Rectangle(300,464,97,38),    cancel,true,false, true, color);
        btn_guardar=    Builder.crearButtonIcon( p, "guardar",              "src/imagenes/btn_guardar.png",                         new Rectangle(413,464,106,38),guardarN,true,false, true, color);
        btn_guardar_mod=Builder.crearButtonIcon( p, "guardar_modificacion", "src/imagenes/btn_guardar.png",                         new Rectangle(413,464,106,38),guardarMod,true,false, true, color);
        agregar.setEnabled(false);
        btn_guardar.    setVisible(false);
        btn_cancelar.   setVisible(false);
        btn_guardar_mod.setVisible(false);
        
        baja=           Builder.crearButtonIcon( p, "Eliminar Empleado",    "src/imagenes/boton-x.png",                             new Rectangle(218, 268, 32, 32), bajaT,  false, false,true,color); 
        baja.setEnabled(false);
        btlista=        Builder.crearButtonIcon( p, "Lista Trabajadores",   "src/imagenes/boton_lista_trabajadores__selected_.png", new Rectangle(175, 67, 140, 27), null,   true, false); 
       // btactinac=      Builder.crearButtonIcon( p, "Activos e Inactivos",  "src/imagenes/boton_activos_inactivos.png",             new Rectangle(342, 67, 130, 27), null,   true, false); 
        Cfoto=          Builder.crearBoton(      p, "Cambiar Foto",         new Rectangle(547, 273, 109, 20),eventos, true, true);
        Cfoto.setBackground(Color.white);
        Cfoto.setEnabled(false);
        //-------------<Comodines>--------------
        String letras = null;
        Color colorfondo = null;
        Color colortexto = null;
        Font fuente = null;
        boolean editable = true; boolean noEditable = false;
        boolean enable = true; boolean disable = false;
        boolean visible = true; boolean invisible = false;
        
        //Jtext
         //buscador = Builder.crearTextField(p, new Rectangle(68, 189, 106, 25),  null, null, null, null, false, true, true);
         tfrfc=     Builder.crearTextField(p, new Rectangle(381, 138, 127, 23), letras, colorfondo, colortexto, fuente, noEditable, enable, visible, new KeyListenerValidation(20));
         tfapp=     Builder.crearTextField(p, new Rectangle(381, 207, 127, 23), letras, colorfondo, colortexto, fuente, noEditable, enable, visible, new KeyListenerValidation(20) );
         tfapm=     Builder.crearTextField(p, new Rectangle(381, 240, 127, 23), letras, colorfondo, colortexto, fuente, noEditable, enable, visible, new KeyListenerValidation(10) );
         tfnom=     Builder.crearTextField(p, new Rectangle(381, 174, 127, 23), letras, colorfondo, colortexto, fuente, noEditable, enable, visible, new KeyListenerValidation(10) );
         
         txt_fechaNac = Builder.crearTextField(p, new Rectangle(351, 316, 100, 17), letras, colorfondo, colortexto, fuente, noEditable, enable, visible, new KeyListenerValidation(10) );
         combo_estadoLaboral=           Builder.crearComboBox(p, new Rectangle(351,290,100,17),  st,   null, null, null);
         combo_estadoLaboral.           setEnabled(false);
                    
         combo_puesto=      Builder.crearComboBox(p, new Rectangle(380,438,111,17), Cargo,null, null, null);
         combo_puesto.setEnabled(false);
         combo_puesto.      setBackground(Color.white);
         combo_puesto.      setForeground(Color.BLACK);
         
         
          //area de texto
         txtA_direccion = new JTextArea();
         p.add(txtA_direccion);
         txtA_direccion.setBounds(381, 378, 277, 56);
         txtA_direccion.setVisible(true);
         txtA_direccion.setEditable(false);
         txtA_direccion.setEnabled(true);
         //lista
         //lista= new JList();
         //p.add(lista);
         //lista.setBounds(new Rectangle(14,180,176,260));
         //lista.setVisible(true);
         //lista.addMouseListener(new TrabajadorGUI.CustomMouseListener());
         //--------------------------</Adding a ScrollPane to JPanel>------------
         lista= new JList();
         javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(lista);         
         scroll.setBounds(new Rectangle(14,180,176,260));
         scroll.setVisible(true);
         lista.addMouseListener(new TrabajadorGUI.CustomMouseListener());
     
         p.add(scroll);
         //--------------------------</Adding a ScrollPane to JPanel>---------------
         JLabel fondo    =   Builder.crearLabelImagen(p, "src/imagenes/fondo_ventana_2.png", new Rectangle(0,0,700,518));
    }
    
    public void cargarLista(JList l){
        DefaultListModel modelo = new DefaultListModel();
                    ArrayList<String> list = interfaz.listatrabajador();
                    for(int i = 0;i<list.size();i++){
                        modelo.addElement(list.get(i));
                    }
                    l.setModel(modelo);
    }

    ActionListener nuevoT=new ActionListener(){
        public void actionPerformed(ActionEvent ae){
            cleanFormulario();
            habilitaNewFormulario();
        }
    };
    
    
    ActionListener bajaT=new ActionListener() {
        public void actionPerformed(ActionEvent ae){
            //--<Advertencia>---
            int ok = javax.swing.JOptionPane.showConfirmDialog(null, "Está seguro de eliminar este registro");
            if (ok == JOptionPane.NO_OPTION || ok == JOptionPane.CANCEL_OPTION || ok == JOptionPane.CLOSED_OPTION ||ok == JOptionPane.OK_CANCEL_OPTION)
                return ;            
            //--</Advertencia>---
            if(lista.getSelectedValue()==null){
                baja.setEnabled(false);
            }else{
                interfaz.eliminaTrabajador(tfrfc.getText());
                cargarLista(lista);
                cleanFormulario();
                
            }
        }
    };

    
    ActionListener modificarT=new ActionListener() {
        public void actionPerformed(ActionEvent ae)
        {
            if("".equals(tfrfc.getText())==false)
                habilitaUpdateFormulario();
                
        }
    };
    
    
    ActionListener actualizarT=new ActionListener() {
        public void actionPerformed(ActionEvent ag)
        {            
            cargarLista(lista);
            cleanFormulario();
            deshabilitaFormulario();
        }
    };
    
    
    ActionListener cancel=new ActionListener() {
        public void actionPerformed(ActionEvent ae){   
           deshabilitaFormulario();
        }
    };
    
    
    ActionListener regresarV=new ActionListener() {
        public void actionPerformed(ActionEvent ae)
        {
            x.dispose();
        }
    };
    
    
    ActionListener guardarN=new ActionListener() {
        public void actionPerformed(ActionEvent ae)
        {
           if(validaIngreso(tfrfc,tfapp,tfapm,tfnom)){
                boolean si = interfaz.agregaTrabajador(); 
                if (si) {
                    cargarLista(lista);
                    cleanFormulario();
                    deshabilitaFormulario();
                    JOptionPane.showMessageDialog(null,"Guardado Exitosamente");
                }else JOptionPane.showMessageDialog(null, "Vuelva a intentarlo","Advertencia", JOptionPane.ERROR_MESSAGE);
                
            }
            else
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Error..!!", JOptionPane.ERROR_MESSAGE);

        }
    };
    
    
    ActionListener guardarMod=new ActionListener() {
        public void actionPerformed(ActionEvent ae)
        {
            if(validaIngreso(tfrfc,tfapp,tfapm,tfnom)){
                interfaz.modificaTrabajador(tfnom.getText(), tfapp.getText(), tfapm.getText(), txtA_direccion.getText(),( (String)combo_puesto.getSelectedItem()),(String)combo_estadoLaboral.getSelectedItem(), tfrfc.getText());
                cargarLista(lista);
                cleanFormulario();
                deshabilitaFormulario();
                
                JOptionPane.showMessageDialog(null,"Guardado Exitosamente");
            }else
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Error..!!", JOptionPane.ERROR_MESSAGE);
        }
    };
    
    
    class TrabajadorActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String op = e.getActionCommand();
            switch(op){
                case "Cambiar Foto":
                    interfaz.cambiarImagen();
                    break;
            }
        }
        
    }
    
    
    class CustomMouseListener extends MouseAdapter{
        public void mouseClicked(MouseEvent me){
            String matricula = (String)lista.getSelectedValue();
            interfaz.listaParametro(matricula);
            agregar.setEnabled(true);
            Cfoto.setEnabled(false);
            baja.setEnabled(true);
            //interfaz.cargarImagen();
        };
        
   
    
        public void actualizarLista(JList lista){
        lista.removeAll();
        
        for(String s: interfaz.listaTrabajador()){
            lista.add(s,null);
        }
    }    
    }
    
    
    
    
    
    public static void main(String []args){
        Conexion.setRol("root");
        
        TrabajadorGUI t = new TrabajadorGUI();
    }
    
    
    public void cleanFormulario(){
        tfrfc.setText(null);
        tfnom.setText(null);
        tfapm.setText(null);
        tfapp.setText(null);        
        combo_estadoLaboral.setSelectedItem("activo");
        txt_fechaNac.setText(null);
        txtA_direccion.setText(null);
        interfaz.putImageProfile("src/imagenes/perfil.jpg");
        Cfoto.setEnabled(true);
    }
    
    
    public void habilitaNewFormulario(){
    //----------------<Inhabilitar Lista>--------------------------
        lista.setEnabled(false);
        btActualizar.setEnabled(false);
    //--------------<Inhabilitar botones de nuevo, modificar y eliminar>------------------
        nuevo.setEnabled(false);
        agregar.setEnabled(false);
        baja.setEnabled(false);
    //--------------<Habilitar botones de Guardar y cancelar>----------------
        btn_guardar.setVisible(true);btn_guardar.setEnabled(true);
        btn_cancelar.setVisible(true);btn_cancelar.setEnabled(true);
    //--------------<Fin>------------------------------------------------
        tfrfc.setEditable(true);
        tfnom.setEditable(true);
        tfapm.setEditable(true);
        tfapp.setEditable(true);
        combo_estadoLaboral.setEnabled(false);  //---Nuevo registro siempre es activo        
        txt_fechaNac.setEditable(false);
        txtA_direccion.setEditable(true);
        combo_puesto.setEnabled(true);
        Cfoto.setEnabled(true);
    }
    
    
    public void habilitaUpdateFormulario(){
    //----------------<Inhabilitar Lista>--------------------------
        lista.setEnabled(false);
        btActualizar.setEnabled(false);
    //--------------<Inhabilitar botones de nuevo, modificar y eliminar>------------------
        nuevo.setEnabled(false);
        agregar.setEnabled(false);
        baja.setEnabled(false);
    //--------------<Habilitar botones de Guardar y cancelar>----------------
        btn_guardar_mod.setVisible(true);btn_guardar_mod.setEnabled(true);        
    //--------------<Fin>------------------------------------------------
        tfrfc.setEditable(false);
        tfnom.setEditable(true);
        tfapm.setEditable(true);
        tfapp.setEditable(true);
        combo_estadoLaboral.setEnabled(true);  //---Nuevo registro siempre es activo        
        txt_fechaNac.setEditable(false);
        txtA_direccion.setEditable(true);
        combo_puesto.setEnabled(true);
        Cfoto.setEnabled(true);
    }
    
    
    public void deshabilitaFormulario(){
        tfrfc.setEditable(false);
        tfnom.setEditable(false);
        tfapm.setEditable(false);
        tfapp.setEditable(false);
        combo_estadoLaboral.setEnabled(false);
        txt_fechaNac.setEditable(false);
        txtA_direccion.setEditable(false);
        combo_puesto.setEnabled(false);
        Cfoto.setEnabled(false);
        
    //----------------<Habilitar Lista>--------------------------
        lista.setEnabled(true);
        btActualizar.setEnabled(true);
    //--------------<Habilitar botones de nuevo, modificar y eliminar>------------------
        nuevo.setEnabled(true);
        agregar.setEnabled(true);
        baja.setEnabled(true);
    //--------------<Habilitar botones de Guardar y cancelar>----------------
        btn_guardar.setVisible(false);btn_guardar.setEnabled(false);
        btn_cancelar.setVisible(false);btn_cancelar.setEnabled(false);
        btn_guardar_mod.setVisible(false);btn_guardar_mod.setEnabled(false);
    //--------------<Fin>------------------------------------------------
    }
}