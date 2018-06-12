package GUI;
import CONTROLLERS.Autobus;
import CONTROLLERS.Conexion;
import CONTROLLERS.SQLAutobus;
import Validacion.Validador;
import static Validacion.Validador.*;
//import com.sun.media.jfxmedia.logging.Logger;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
public class AutobusGUI extends JFrame 
{ 
    //String              defaultImagePath;
    String              ruta = "src/imagenes/";
    ActionListener      listener;
    Autobus             controlador;
    Validador           valida;
    public JList        list;
    public JTextField   txt_buscar,txt_marca,txt_No_Eco,txt_Km,txt_matricula,txt_id,txt_asientos;
    public JFileChooser url_img;
    public JButton      btn_Act_List,btn_nuevo_autobus,btn_modificar,btn_eliminar,btn_agregar_img,btn_regresar,btn_guardar,btn_cancelar,btn_guardar_mod;
    public JButton      sesion, inicio, buscar;
    public JLabel       lb_codigo_auto,lb_imagen_autobus;
    JFrame x;
    JPanel p;
    public AutobusGUI()
    {
        x = this;
        listener = new CustomActionListener();
        x = Builder.construirFrame("Autobus", new Rectangle(0,0, 700, 600), false); 
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p = Builder.crearPanel(x, new Rectangle(0, 0, 700, 600),ruta + "fondo_ventana_2.png", false);
        controlador = new Autobus(this);
        inicializarcomp();
    }
    
    public void inicializarcomp()
    {
        Color color = new Color(233,233,233);
        inicio=     Builder.crearButtonIcon(p,"inicio",ruta + "boton_inicio.png",       new Rectangle(14,63,101,43), listener,false,false);
        sesion=     Builder.crearButtonIcon(p,"cerrarSesion",ruta + "boton_cerrar_sesion.png",new Rectangle(539,65,142,43),listener,false,false);
        buscar =    Builder.crearButtonIcon(p,"buscar",ruta + "buscar.png",             new Rectangle(26,185,32,32) ,listener,false,false);
        txt_buscar=         Builder.crearTextField(p, new Rectangle(68, 189, 106, 25), "", null, null,new Font("Segoe UI", Font.BOLD, 10),true,true,true);
        list=new JList();
        controlador.cargarLista(list);
        list.addMouseListener(new CustomMouseListener());
        JScrollPane pane = new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBounds(14,236,172,198);
        p.add(pane);  
        btn_Act_List =      Builder.crearButtonIcon(p,"actualizar_lista",   ruta + "boton_actualizar_lista.png",new Rectangle(26,446,145,36),listener,true,false);
        btn_nuevo_autobus = Builder.crearButtonIcon(p,"agregar",            ruta + "anadir.png",    new Rectangle(218,136,32,32),listener,true,false, true, color);
        btn_eliminar=       Builder.crearButtonIcon(p,"eliminar",           ruta + "boton-x.png",   new Rectangle(218,268,32,32),listener,false,false, true, color);
        btn_cancelar=       Builder.crearButtonIcon(p,"cancelar",           ruta + "btn_cancelar.png",   new Rectangle(300,444,97,38),listener,true,false, true, color); 
        btn_regresar=       Builder.crearButtonIcon(p,"regresar",           ruta + "regresar.png",  new Rectangle(626,450,32,32),listener,true,false, true, color);
        btn_guardar=        Builder.crearButtonIcon(p,"guardar",           ruta + "btn_guardar.png",  new Rectangle(413,444,106,38),listener,true,false, true, color);
        btn_guardar_mod=    Builder.crearButtonIcon(p,"guardar_modificacion",           ruta + "btn_guardar.png",  new Rectangle(413,444,106,38),listener,true,false, true, color);
        btn_guardar.    setVisible(false);
        btn_cancelar.   setVisible(false);
        btn_guardar_mod.setVisible(false);
        btn_eliminar.setEnabled(false);
        btn_modificar =     Builder.crearButtonIcon(p,"modificar",          ruta + "lapiz.png",     new Rectangle(218,202,32,32),listener,false,false, true);
        btn_modificar.setEnabled(false);
        btn_agregar_img=    Builder.crearButtonIcon(p,"cargarImagen",       ruta + "foto.png",      new Rectangle(626,215,32,32),listener,true,false, true, color);
        btn_agregar_img.setVisible(false);
        lb_imagen_autobus=          Builder.crearLabelImagen(p,"src/imagenes/autobu.png",  new Rectangle(354,162,245 , 139));
        JLabel lb_codigo_auto=      Builder.crearLabel(p, "(Matricula)",               new Rectangle(423,132, 107 , 13),   null,null);
        JLabel lb_Marca =           Builder.crearLabel(p, "Marca:",                    new Rectangle(335,350,101,13),      null,null);
        JLabel lb_Num_Eco =         Builder.crearLabel(p, "Numero Economico:",         new Rectangle(270,383,120,13),      null,null);
        JLabel lb_Mat_Auto =        Builder.crearLabel(p, "Matricula:",                new Rectangle(315,416, 101, 13),    null,null);
        JLabel lb_Km =              Builder.crearLabel(p, "Kilometraje",               new Rectangle(499,350,101,13),      null,null);
        JLabel autobuses =          Builder.crearLabel( p, "Autobuses",                new Rectangle(65, 130, 80, 20), null, null, new Font("Segoe UI",Font.PLAIN,11));
        JLabel lb_Asien =           Builder.crearLabel(p, "Asientos:",                 new Rectangle(514,383,101,13),      null,null);

        txt_marca       =   Builder.crearTextField(p, new Rectangle(389,345,108,23), "", null, null,null,false,true,true);
        txt_No_Eco      =   Builder.crearTextField(p, new Rectangle(389,378,108,23), "", null, null,null,false,true,true);
        txt_Km          =   Builder.crearTextField(p, new Rectangle(584,345,74,23),  "", null, null,null,false,true,true);
        txt_matricula   =   Builder.crearTextField(p, new Rectangle(389,411,108,23), "", null, null,null,false,true,true);
        txt_asientos    =   Builder.crearTextField(p, new Rectangle(584,378,74,23),  "", null, null,null,false,true,true);        
        
        JLabel fondo    =   Builder.crearLabelImagen(p, ruta + "fondo_ventana_2.png", new Rectangle(0,0,700,518));
        valida();
       // p.add(pane);
    }
    
    static void p(Object o){
        System.err.println(o.toString());
    }
    static void textField(JTextField... text){
        for(JTextField tf:text){
            tf.setEditable(true);
            tf.setText(null);
        }
    }
    static void textField2(JTextField... text){
        for(JTextField tf:text){
            tf.setEditable(false);
            tf.setText(null);
        }
    }
    private boolean mat(){
        String Busqueda=txt_matricula.getText();
        boolean bandera=false;
        ListModel model = list.getModel();
        for(int i=0;i<model.getSize();i++){
            if(model.getElementAt(i).equals(Busqueda)){
                JOptionPane.showMessageDialog(null, "La Matricula ya ha sido ingresada antes", "Error..!!", JOptionPane.ERROR_MESSAGE);
		txt_matricula.requestFocus();
		bandera=true;
		break;
            }
        }
        return bandera;
    }
    class CustomActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String op = e.getActionCommand();
            switch(op)
            {
                case "agregar":
                    textField(txt_marca,txt_No_Eco,txt_Km,txt_matricula,txt_asientos);
                    btn_guardar.setVisible(true);
                    list.setEnabled(false);
                    btn_nuevo_autobus.setEnabled(false);
                    btn_modificar.setEnabled(false);
                    btn_eliminar.setEnabled(false);
                    btn_cancelar.setVisible(true);
                    btn_agregar_img.setVisible(true);
                    break;
                case "guardar":
                    if(!mat())
                        if(validaIngreso(txt_marca,txt_No_Eco,txt_Km,txt_matricula,txt_asientos)){
                            controlador.evtAgregarAutobus();
                            list.setEnabled(true);
                            textField2(txt_marca,txt_No_Eco,txt_Km,txt_matricula,txt_asientos);
                            btn_nuevo_autobus.setEnabled(true);
                            btn_guardar.setVisible(false);
                            btn_modificar.setEnabled(false);
                            btn_eliminar.setEnabled(false);
                            btn_cancelar.setVisible(false);
                            btn_agregar_img.setVisible(false);
                            controlador.actualizarLista();
                        }
                        else
                            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Error..!!", JOptionPane.ERROR_MESSAGE);
                    break;
                case "cancelar":
                        list.setEnabled(true);
                        textField2(txt_marca,txt_No_Eco,txt_Km,txt_matricula,txt_asientos);
                        btn_nuevo_autobus.setEnabled(true);
                        btn_guardar.setVisible(false);
                        btn_modificar.setEnabled(false);
                        btn_eliminar.setEnabled(false);
                        btn_cancelar.setVisible(false);
                        btn_agregar_img.setVisible(false);
                        controlador.actualizarLista();
                case "modificar":
                    if("".equals(txt_matricula.getText())){}
                    else{
                        txt_marca.setEditable(true);
                        txt_No_Eco.setEditable(true);
                        txt_Km.setEditable(true);
                       // txt_matricula.setEditable(true);
                        txt_asientos.setEditable(true);
                        list.setEnabled(false);
                        btn_nuevo_autobus.setEnabled(false);
                        btn_eliminar.setEnabled(false);
                        btn_guardar_mod.setVisible(true);
                        btn_modificar.setEnabled(false);
                        btn_agregar_img.setVisible(true);
                        
                    }
                    break;
                case "guardar_modificacion":
                    controlador.modificarAutobus();
                    btn_guardar_mod.setVisible(false);
                    list.setEnabled(true);
                    btn_nuevo_autobus.setEnabled(true);
                    btn_eliminar.setEnabled(false);
                    textField2(txt_marca,txt_No_Eco,txt_Km,txt_matricula,txt_asientos);
                    btn_modificar.setEnabled(false);
                    btn_agregar_img.setVisible(false);
                    break;
                case "cargarImagen":
                    controlador.evtCargarImagen();
                    break;
                case "actualizar_lista":
                    controlador.actualizarLista();
                    textField2(txt_marca,txt_No_Eco,txt_Km,txt_matricula,txt_asientos);
                    btn_eliminar.setEnabled(false);
                    btn_modificar.setEnabled(false);
                    btn_agregar_img.setVisible(false);
                    break;
                case "Buscar":
                    //controlador.buscar();
                    break;
                case "eliminar":
                       int ok = javax.swing.JOptionPane.showConfirmDialog(null, "Está seguro de eliminar este registro");
                       if (ok == JOptionPane.NO_OPTION || ok == JOptionPane.CANCEL_OPTION || ok == JOptionPane.CLOSED_OPTION ||ok == JOptionPane.OK_CANCEL_OPTION)
                return ; 
                    if(list.getSelectedValue()==null)
                        btn_eliminar.setEnabled(false);
                    else{
                        controlador.borrarAutobus();
                        controlador.actualizarLista();
                    }
                    break;
                case "inicio":
                    x.dispose();
                    //controlador.inicio();
                     break;
                case "regresar":
                   x.dispose();
                    break;
                case "cerrarSesion":
                    LoginGUI l = new LoginGUI();
                    x.dispose();
                    break;
            }
        }
    }
    
   
    
    
    
    public void valida()
    {
        txt_marca.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validaNombre(evt,txt_marca,20);
            }
        });
        
        txt_No_Eco.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validaAlfanumerico(evt,txt_No_Eco,20);
            }
        });
        
        txt_matricula.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validaAlfanumerico(evt,txt_matricula,10);
            }
        });     
        
        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validaAlfanumerico(evt,txt_buscar,10);
            }
        });
        txt_Km.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validaNum(evt,txt_Km,10);
            }
        });
        txt_asientos.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validaNum(evt,txt_asientos,10);
            }
        });
    }
    
    class CustomMouseListener extends MouseAdapter{
        public void mouseClicked(MouseEvent me){
            if(list.getSelectedValue()!=null){
                String matricula = (String)list.getSelectedValue();
                SQLAutobus s = new SQLAutobus();
                String registro[] = s.obtenerRegistro(matricula.toLowerCase());
                txt_matricula.setText(registro[0].toUpperCase());
                txt_marca.    setText(registro[2].toUpperCase());
                txt_No_Eco.   setText(registro[3].toUpperCase());
                txt_Km.       setText(registro[4].toUpperCase());
                txt_asientos. setText(registro[5].toUpperCase());
                btn_eliminar.setEnabled(true);
                btn_modificar.setEnabled(true);
                //Logger.logMsg(0, registro[6]);
                controlador.putImageProfile(registro[6]);
            }
            else
                System.err.println("Nada que elegir");
        };
    }
    public static void main (String []agrs)
    {
        Conexion.setRol("root");
        AutobusGUI autobus = new AutobusGUI();
    }
     
}