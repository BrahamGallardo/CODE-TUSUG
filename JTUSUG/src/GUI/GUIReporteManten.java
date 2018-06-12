package GUI;

import CONTROLLERS.Conexion;
import CONTROLLERS.ControladorMantenimiento;
import CONTROLLERS.SQLAutobus;
import Entities.AutobusE;
import Validacion.KeyListenerValidation;
import Validacion.Validador;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import static Validacion.Validador.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListDataListener;
import net.sf.jasperreports.engine.JRException;
public class GUIReporteManten{
    String carpeta_img = "src/imagenes/";

    private ControladorMantenimiento controlador;
    private JFrame          ventana;
    public JPanel          panel;
    public JTextField      txt_responsable, txt_solicitante, txt_areaTrabajo,
                            txt_prioridad, txt_tipoManten, txt_direccion, txt_telefono, txt_email, 
                            txt_descripGenerica,
                            txt_marca, txt_matricula, txt_costo;
    public JComboBox<String> cbx_codAutobus, cbx_prioridad, cbx_tipoManten;
    public JComboBox<AutobusE>  cbx_matricula;
    public JTextArea       text_descripcionEquipos, text_solicitud;
    public JButton         btn_genReporte, btn_cerrarsesion, btn_regresar;
    
    public JDateChooser    calendario;
    public GUIReporteManten(){
        
        ControladorMantenimiento ui= new ControladorMantenimiento(this);
        controlador=ui;
        initComponents();
    }
    
    
    private void initComponents(){
        //-------------------------------------------<Variables locales>---------------------------------------
        Font  f = new Font("Segoe UI", Font.PLAIN, 11);
        Color c = new Color(33,33,33);
        //-----------------------------------------------------------------------------------------------------
        ventana =       Builder.construirFrame("Reporte de Matenimiento", new Rectangle(200,50,700,600),false);
        panel   =       Builder.crearPanel(ventana, new Rectangle(0,0,700,600), "src/imagenes/fondo_frame_ter.png", true);
        calendario      =       new JDateChooser();
        panel.add(calendario);
        calendario.setBounds(355,354,155, 22);
        JLabel lb_fecha          = Builder.crearLabel(  panel, "Fecha:",                    new Rectangle(517,57,154,15),   null, c, f);
        JLabel lb_datosGenerales = Builder.crearLabel(  panel, "Datos generales",           new Rectangle(83,90,120,20),    null, c, f);
        JLabel lb_responsable    = Builder.crearLabel(  panel, "Responsable de trabajo",    new Rectangle(44,162,129,15),   null, c, f);
        JLabel lb_solicitante    = Builder.crearLabel(  panel, "Solicitante",               new Rectangle(44,120,63,15),    null, c, f);
        JLabel lb_solicitud      = Builder.crearLabel(  panel, "Solicitud",                 new Rectangle(44,207,54,15),    null, c, f);
        JLabel lb_areaTrabajo    = Builder.crearLabel(  panel, "Area de Trabajo",           new Rectangle(44,298,85,15),    null, c, f);
        JLabel lb_prioridad      = Builder.crearLabel(  panel, "Prioridad",                 new Rectangle(171,298,57,15),   null, c, f);
        JLabel lb_tipoManten     = Builder.crearLabel(  panel, "Tipo de mantenimiento",     new Rectangle(44,343,127,15),   null, c, f);
        JLabel lb_direccion      = Builder.crearLabel(  panel, "Direccion:",                new Rectangle(44,388,58,15),    null, c, f);
        JLabel lb_telefono       = Builder.crearLabel(  panel, "Telefono:",                 new Rectangle(44,432,54,15),    null, c, f);
        JLabel lb_email          = Builder.crearLabel(  panel, "E - Mail",               new Rectangle(44,477,54,15),    null, c, f);
        JLabel lb_descripGenerica= Builder.crearLabel(  panel, "Descripcion_generica",      new Rectangle(431,90,153,20),   null, c, f);
        //JLabel lb_codAutobus     = Builder.crearLabel(  panel, "Codigo del Autobus",        new Rectangle(355,137,107,15),   null, c, f);
        JLabel lb_marca          = Builder.crearLabel(  panel, "Marca",                     new Rectangle(355,162,44,15),   null, c, f);
        JLabel lb_matricula      = Builder.crearLabel(  panel, "Matricula",                 new Rectangle(355,137,107,15),  null, c, f);//new Rectangle(504,162,59,15),   null, c, f);
        JLabel lb_costoman = Builder.crearLabel(  panel, "Costo de mantenimiento",      new Rectangle(355,207,114,15),  null, c, f);
        JLabel lb_descEquipoObsver=Builder.crearLabel(  panel, "Descripcion de equipo"
                                                                          + "observaciones",new Rectangle(355,251,193,15),  null, c, f);
        JLabel lb_plazoEntrega   = Builder.crearLabel(  panel, "Plazo de Entrega (Fechay hora)",new Rectangle(355,329,165,15),null, c, f);
        JLabel lb_notas          = Builder.crearLabel(  panel, "Notas",                     new Rectangle(345,374,305,53),  null, c, f);
        
        txt_responsable=        Builder.crearTextField( panel, new Rectangle(44,177,186,20), "", null, null, f, true, true, true);
        txt_solicitante=        Builder.crearTextField( panel, new Rectangle(44,135,186,20), "", null, null, f, true, true, true);
        //text_solicitud  =       Builder.crearTextArea(panel, new Rectangle(44,222,219,66), c);
        text_solicitud  =       Builder.crearTextArea(panel, "B", new Rectangle(44,222,219,66));
        txt_areaTrabajo=        Builder.crearTextField( panel, new Rectangle(44,313,110,20), "", null, null, f, true, true, true);
        //txt_prioridad  =        Builder.crearTextField( panel, new Rectangle(171,313,92,20), "", null, null, f, true, true, true);
        String [] ops = {"Normal", "Alto", "Bajo"};
        cbx_prioridad =         Builder.crearComboBox(panel, new Rectangle(171,313,92,20), ops, null, null, null);
        //txt_tipoManten =        Builder.crearTextField( panel, new Rectangle(44,358,135,20), "", null, null, f, true, true, true);
        String [] tipos = {"Correctivo", "Preventivo"};
        cbx_tipoManten     =    Builder.crearComboBox(panel, new Rectangle(44,358,135,20), tipos, null, null, null);
        txt_direccion  =        Builder.crearTextField( panel, new Rectangle(44,402,186,20), "", null, null, f, true, true, true);
        txt_telefono   =        Builder.crearTextField( panel, new Rectangle(44,447,135,20), "", null, null, f, true, true, true);
        txt_email      =        Builder.crearTextField( panel, new Rectangle(44,492,162,20), "", null, null, f, true, true, true);
        //cbx_codAutobus =        //Builder.crearComboBox(  panel, new Rectangle(462,135,139,22),    null, null, null, c);        
        txt_marca      =        Builder.crearTextField( panel, new Rectangle(355,177,127,20),"", null, null, f, true, true, true);
        //txt_matricula  =        Builder.crearTextField( panel, new Rectangle(504,177,127,20),"", null, null, f, true, true, true);
        
        cbx_matricula =         Builder.crearComboBox(panel, new Rectangle(410,135,139,22),matriculaSelected);
        
        
        txt_costo      =        Builder.crearTextField( panel, new Rectangle(355,222,165,20),"", null, null, f, true, true, true);
        text_descripcionEquipos=Builder.crearTextArea(  panel, new Rectangle(355,266,276,53), c);
        ReportCustomListener listen = new ReportCustomListener();
        btn_genReporte =        Builder.crearButtonIcon(     panel, "Generar Reporte", carpeta_img + "btn_new_reporte.png", new Rectangle(440,447,150,27), listen, true, true);
        btn_cerrarsesion=       Builder.crearButtonIcon(     panel, "cerrarSesion",   carpeta_img + "cerrar_sesion.png",   new Rectangle(460,506,201,63), listen, true, true);
        btn_regresar    =       Builder.crearButtonIcon(     panel, "regresar",        carpeta_img + "regresar.png",        new Rectangle(326,518,32,32),  listen, true, true);
        valida();
        loadAutobusesFromDB();
    }
    
    
    public void loadAutobusesFromDB(){
        Connection conn = Conexion.getConexion();
        //ArrayList<AutobusE> listBuses = new ArrayList<AutobusE>();
        String sql = null;// = "select count(matricula) as total from sistemaTusug.autobus ";
        PreparedStatement pst;
        ResultSet res;
        try {
            sql = "select * from sistemaTusug.autobus ORDER BY matricula";
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            AutobusE dat;
            cbx_matricula.removeAllItems();
            while (res.next()) {
                // Mapear Datos
                dat = new AutobusE();
                dat.setMatricula(res.getString("matricula"));
                dat.setId(res.getString("id"));
                dat.setMarca(res.getString("marca"));
                dat.setNo_economico(res.getString("numero_economico"));
                dat.setKm(res.getInt("kilometraje"));
                dat.setAsientos(res.getInt("asientos"));
                dat.setDirImage(res.getString("url_img"));
                //listBuses.add(dat);
                cbx_matricula.addItem(dat);
            }
            res.close();
            pst.close();
            
            //--------<Cargarlo al Combo Box>-------------            
        } catch (SQLException ex) {
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void valida(){
        KeyListener len20 = new KeyListenerValidation(20);
        KeyListener len40 = new KeyListenerValidation(40);
        KeyListener len10= new KeyListenerValidation(10);
        
        txt_responsable.addKeyListener(len20);        
        txt_solicitante.addKeyListener(len20);        
        txt_areaTrabajo.addKeyListener(len20);        
        txt_marca.addKeyListener(len20);
        //txt_prioridad.addKeyListener(len20);txt_prioridad.addKeyListener(Validador.KEYonlyNumbers);
        txt_costo.addKeyListener(len20);txt_costo.addKeyListener(Validador.KEYonlyNumbers);
        //txt_matricula.addKeyListener(len20);
        //txt_tipoManten.addKeyListener(len20);
        txt_direccion.addKeyListener(len40);
        txt_telefono.addKeyListener(len10);txt_telefono.addKeyListener(Validador.KEYonlyNumbers);
        txt_email.addKeyListener(len40);
        text_solicitud.addKeyListener(new KeyListenerValidation(60));
        text_descripcionEquipos.addKeyListener(len40);
    }
    
    
    class ReportCustomListener implements ActionListener {

    String op;

    @Override
    public void actionPerformed(ActionEvent e) {
        op = e.getActionCommand();
        switch (op) {
            case "Generar Reporte":
                if (validaIngreso(txt_areaTrabajo, txt_costo, txt_direccion, txt_telefono)&&
                        Validador.emailValido(txt_email.getText())) {
                    try {
                        controlador.agregaMan();
                        controlador.creaRepor();
                    } catch (JRException ex) {
                        Logger.getLogger(GUIReporteManten.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Error..!!", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "cerrarSesion":
                ventana.dispose();
                break;
            case "regresar":
                ventana.dispose();
                break;
        }
    }

}
    
    ItemListener matriculaSelected = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            AutobusE auto = (AutobusE)e.getItem();
            txt_marca.setText(auto.getMarca());
            //txt_/    
        }
    };
    
    
    public static void main(String [] args){
        Conexion.setRol("root");
        new GUIReporteManten();
    }
    
    
}