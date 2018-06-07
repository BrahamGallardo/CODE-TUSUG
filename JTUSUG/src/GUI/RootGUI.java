package GUI;

import CONTROLLERS.Conexion;
import GaleriaRutas.interfaz;
import Servicios.FabricaComponentes;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Alekhius
 */
public class RootGUI {
    //Datos de la sesion
    String rol_activo;
    String nombre_usuario;
    String urlimage;
    private JMenuBar bar_menu;
    private JMenu ar;
    private JMenuItem   menu_cerrarsesion;
    private JMenuItem   menu_cambiarpass;
    
    CustomActionListener listen;
    String carpeta_img = "src/imagenes/";
    Font font;
    public static JFrame root;
    public static JPanel panel;
    public JButton btn_close, btn_secre, btn_manten, btn_rrhh, btn_rutas;
    
    //Para reciclar
    JLabel lb_text;
    private JLabel lb_title, lb_descripcion, lb_img_user;
    // Botones para la secretaria    
    private JButton btn_regresar, btn_listaBus, btn_facturas, btn_reportes;
    // Botones para almacenista
    private JButton btn_insumos, btn_lista_invent, btn_gener_inventario;
    // Botones de Recursos Humanos
    private JButton btn_trabajadores, btn_expedientes, btn_nuevosempleado, btn_iactivos;
    // Botones de Mantenimiento
    private JButton btn_nuevoreporte, btn_reUnidades, btn_historial;

    public RootGUI(String rol, String nombreUser, String urlimg){
        font = new Font("Segoe UI", Font.PLAIN, 14);
        listen = new CustomActionListener();
        this.rol_activo     = rol;
        this.nombre_usuario = nombreUser;
        this.urlimage       = urlimg;
        initComponents();
        switch(rol){            
            case "root":
                habilitarComponentes(btn_secre, btn_manten, btn_rrhh);
                break;
            case "secretaria":
                habilitarComponentes(btn_regresar, btn_listaBus, btn_facturas, btn_reportes);
                break;
            case "mantenimiento":
                habilitarComponentes(btn_nuevoreporte, btn_reUnidades, btn_historial);
                break;
            case "almacen":
                habilitarComponentes(btn_insumos, btn_lista_invent, btn_gener_inventario);
                break;
            case "recursos humanos":
                habilitarComponentes(btn_trabajadores, btn_expedientes, btn_nuevosempleado, btn_iactivos);
                break;
            default:
                // Code Here:
                // Accion para caso no previsto
        }
        if (!rol_activo.equals("root"))btn_regresar.setVisible(false);
    }
    public RootGUI() {
        this("root", "Alejo", "src/imagenes/icono usuario.jpeg");
    }

    public void initComponents() {
        // Frame principal y labels de ayuda
        root =                  Builder.construirFrame(nombre_usuario + " - Bienvenido -", new Rectangle(460, 506, 700, 600), false);
        panel =                 Builder.crearPanel(     root,       new Rectangle(0, 0, 700, 600),      "src/imagenes/pagina_de_fondo.png", true);
        lb_title =              Builder.crearLabel(     panel, "",               new Rectangle(317, 202, 200, 40), Color.BLUE,   null, font);        
        //lb_descripcion =        Builder.crearLabel(     panel, "descripcion",    new Rectangle(114, 445, 100, 40), null,         null, font);
        lb_img_user       =     FabricaComponentes.crearLabelImg(panel, "biv", new Rectangle(547,109, 32, 32), urlimage, true);
        lb_text =               Builder.crearLabel(     panel, "Seleccione el icono de la seccion que desea visitar",   new Rectangle(193, 172, 300, 60), null, null, font);
        
        bar_menu        =       new JMenuBar();//FabricaComponentes.crearMenuBar(panel, "b", new Rectangle(589,116, 67,18));
        panel.add(bar_menu);        
        bar_menu.       setBounds(new Rectangle(579,116, 77,18));
        bar_menu.       setBackground(Color.gray);
        ar = new JMenu(nombre_usuario);
        
        menu_cerrarsesion   =   new JMenuItem("Cerrar Sesion");        
        menu_cambiarpass    =   new JMenuItem("Cambiar Password");
        menu_cambiarpass.addActionListener(listen);
        menu_cerrarsesion.addActionListener(listen);
        
        //bar_menu.addMouseListener(l);
        //menu_cerrarsesion.addItemListener(listenItems);
        ar.add(menu_cambiarpass);
        ar.add(menu_cerrarsesion);
        
        bar_menu.add(ar);
        
        
        // Botones generales
        btn_regresar =          Builder.crearButtonIcon(panel, "Uncknown",          carpeta_img + "regresar.png",               new Rectangle(335, 523, 32,  32), listen, true, false,false);
        btn_close =             Builder.crearButtonIcon(panel, "CerrarSesion",      carpeta_img + "cerrar_sesion.png",          new Rectangle(460, 506, 201, 63), listen, true, true, true);
        // Botones de root
        btn_secre =             Builder.crearButtonIcon(panel, "btnSecretaria",     carpeta_img + "secretaria.png",             new Rectangle(85,  256, 256, 63), listen, true, true, false);
        //           Builder.crearButtonIcon(panel, "btnAlmacen",        carpeta_img + "almacen.png",                new Rectangle(85,  352, 256, 63), listen, true, true, false);
        btn_manten =            Builder.crearButtonIcon(panel, "btnManten",         carpeta_img + "mantenimiento.png",          new Rectangle(379, 256, 256, 63), listen, true, true, false);        
        btn_rrhh =              Builder.crearButtonIcon(panel, "btnRRHH",           carpeta_img + "recursos_humanos.png",       new Rectangle(379, 352, 256, 63), listen, true, true, false);
        btn_rutas =             Builder.crearButtonIcon(panel, "btnrutas",           carpeta_img + "rutas.png",                 new Rectangle(85, 352, 256, 63), listen, true, true, true);
        
        // Botones de Secretaria
        btn_listaBus =          Builder.crearButtonIcon(panel, "modulo_autobus",    carpeta_img + "lista_de_autobuses.png",     new Rectangle(85,  256, 256, 63), listen, false,true, false);
        btn_facturas =          Builder.crearButtonIcon(panel, "facturas",          carpeta_img + "sec_facturas.png",           new Rectangle(85,  352, 256, 63), listen, false,true, false);
        btn_reportes =          Builder.crearButtonIcon(panel, "reportes",          carpeta_img + "reportes.png",               new Rectangle(379, 256, 256, 63), listen, false,true, false);
        // Botones de Almacen
        btn_insumos =           Builder.crearButtonIcon(panel, "insumos",           carpeta_img + "Insumos.png",                new Rectangle(85,  256, 256, 63), listen, false,true, false);
        btn_lista_invent =      Builder.crearButtonIcon(panel, "lista_inventario",  carpeta_img + "lista_de_inventario.png",    new Rectangle(85,  352, 256, 63), listen, false,true, false);
        btn_gener_inventario =  Builder.crearButtonIcon(panel, "generarInventario", carpeta_img + "Generar_inventario.png",     new Rectangle(379, 256, 256, 63), listen, false,true, false);
        // Botones de Recursos Humanos
        btn_trabajadores =      Builder.crearButtonIcon(panel, "btntrabajadores",   carpeta_img + "trabajadores.png",           new Rectangle(85,  256, 256, 63), listen, false,true, false);
        btn_expedientes =       Builder.crearButtonIcon(panel, "btnexpedientes",    carpeta_img + "expedientes.png",            new Rectangle(85,  352, 256, 63), listen, false,true, false);
        btn_nuevosempleado =    Builder.crearButtonIcon(panel, "btnnuevoempleados", carpeta_img + "nuevos_empleados.png",       new Rectangle(379, 256, 256, 63), listen, false,true, false);
        btn_iactivos =          Builder.crearButtonIcon(panel, "btniactivos",       carpeta_img + "activos_e_inactivos.png",    new Rectangle(379, 352, 256, 63), listen, false,true, false);
        //Botones de Mantenimiento
        btn_nuevoreporte =      Builder.crearButtonIcon(panel, "MnuevoReporte",     carpeta_img + "Generar_nuevo_reporte.png",  new Rectangle(85,  256, 256, 63), listen, false,true, false);        
        btn_historial =         Builder.crearButtonIcon(panel, "Mhistorial",        carpeta_img + "Historial.png",              new Rectangle(379, 256, 256, 63), listen, false,true, false);
        btn_reUnidades =        Builder.crearButtonIcon(panel, "MreUnidades",  carpeta_img + "reincorporar_nuevas_unidades.png",new Rectangle(85, 352, 256, 63),  listen, false,true, false);
        
    }
    
    private static void habilitarComponentes(Component... btns) {
        for (Component b : btns) {
            b.setVisible(true);
        }
    }
    private static void deshabilitarComponentes(Component... btns) {
        for (Component b : btns) {
            b.setVisible(false);
        }
    }
    
    class CustomActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String op = e.getActionCommand();
            switch (op) {
                /*-------------------------------|Botones de flujo del programa|--------------------------*/
                case "CerrarSesion":
                    LoginGUI l = new LoginGUI();
                    root.dispose();
                    break;
                case "btnSecretaria":
                    deshabilitarComponentes(btn_secre, btn_manten, btn_rrhh);
                    habilitarComponentes(btn_regresar, btn_listaBus, btn_facturas, btn_reportes);
                    btn_regresar.setActionCommand("btn_regresar");
                    lb_title.setText("Secretaria");
                    break;
                case "btnAlmacen":
                    deshabilitarComponentes(btn_secre, btn_manten, btn_rrhh);
                    habilitarComponentes(btn_regresar, btn_insumos, btn_lista_invent, btn_gener_inventario);
                    btn_regresar.setActionCommand("btn_back_almacen");
                    lb_title.setText("Almacen");
                    break;
                    case "btnrutas":
                        //Rutas r = new Rutas();                        
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                new interfaz().setVisible(true);
                            }
                        });
                    break;
                    
                case "btnManten":
                    deshabilitarComponentes(btn_secre, btn_manten, btn_rrhh);
                    habilitarComponentes(btn_regresar, btn_nuevoreporte, btn_reUnidades, btn_historial);
                    btn_regresar.setActionCommand("btn_back_Mant");
                    lb_title.setText("Mantenimiento");
                    break;
                case "btnRRHH":
                    deshabilitarComponentes(btn_secre, btn_manten, btn_rrhh);
                    habilitarComponentes(btn_regresar, btn_trabajadores, btn_expedientes, btn_nuevosempleado, btn_iactivos);
                    btn_regresar.setActionCommand("btn_back_RH");
                    lb_title.setText("Recursos Humanos");
                    break;
                /*-------------------------------|Botones para el flujo el programa|--------------------------*/
                case "btn_regresar": //Secretaria
                    deshabilitarComponentes(btn_regresar, btn_listaBus, btn_facturas, btn_reportes);
                    habilitarComponentes(btn_secre, btn_manten, btn_rrhh);
                    
                    break;
                case "btn_back_almacen":
                    deshabilitarComponentes(btn_regresar, btn_insumos, btn_lista_invent, btn_gener_inventario);
                    habilitarComponentes(btn_secre, btn_manten, btn_rrhh);
                    break;
                case "btn_back_RH":
                    deshabilitarComponentes(btn_regresar, btn_trabajadores, btn_expedientes, btn_nuevosempleado, btn_iactivos);
                    habilitarComponentes(btn_secre, btn_manten, btn_rrhh);
                    break;
                case "btn_back_Mant":
                    deshabilitarComponentes(btn_regresar, btn_nuevoreporte, btn_reUnidades, btn_historial);
                    habilitarComponentes(btn_secre, btn_manten, btn_rrhh);
                    break;
                /*-------------------------------|Botones para Secretaria|--------------------------*/
                case "modulo_autobus":
                    AutobusGUI auto = new AutobusGUI();
                    break;
                case "facturas":
                    GFacturas facuras = new GFacturas();
                    break;
                case "reportes":
                    javax.swing.JOptionPane.showMessageDialog(null, "Esta funcion aun no esta implementada");
                    break;
                /*-------------------------------|Botones para Almacen|--------------------------*/
                case "insumos":
                    javax.swing.JOptionPane.showMessageDialog(null, "Esta funcion aun no esta implementada");
                    break;
                case "lista_inventario":
                    javax.swing.JOptionPane.showMessageDialog(null, "Esta funcion aun no esta implementada");
                    break;
                case "generarInventario":
                    javax.swing.JOptionPane.showMessageDialog(null, "Esta funcion aun no esta implementada");
                    break;
                /*-------------------------------|Botones para Recursos Humanos|--------------------------*/
                case "btntrabajadores":
                    TrabajadorGUI employer = new TrabajadorGUI();
                    break;
                case "btnexpedientes":
                    javax.swing.JOptionPane.showMessageDialog(null, "Esta funcion aun no esta implementada");
                    break;
                case "btnnuevoempleados":
                    javax.swing.JOptionPane.showMessageDialog(null, "Esta funcion aun no esta implementada");
                    break;
                case "btniactivos":
                    javax.swing.JOptionPane.showMessageDialog(null, "Esta funcion aun no esta implementada");
                    break;
                /*-------------------------------|Botones para Mantenimiento|--------------------------*/
                case "MnuevoReporte":
                    GUIReporteManten report = new GUIReporteManten();
                    break;
                case "MreUnidades":
                    javax.swing.JOptionPane.showMessageDialog(null, "Esta funcion aun no esta implementada");
                    break;
                case "Mhistorial":
                    HistorialMantenGUI h = new HistorialMantenGUI();
                    break;
                    
                 /** -------------------|Eventos para el JMenuBar|--------------------*/
                case "Cerrar Sesion":
                   new LoginGUI();
                   root.dispose();
                   break;
                case "Cambiar Password":
                    try{
                        String newPass = javax.swing.JOptionPane.showInputDialog("Ingrese su nueva contraseña");
                        String newPassConfirm  = javax.swing.JOptionPane.showInputDialog("Ingrese su nueva contraseña");
                        if (newPass.equals(newPassConfirm)){
                            
                            javax.swing.JOptionPane.showMessageDialog(null, "Contraseña cambiada");
                        }
                    }catch(NullPointerException ex){
                        
                    }catch (Exception ex){
                        
                    }
                   
                   break;
            }
        }
    }

    public void cambiarPasswordEvt(){
        
    }
}
