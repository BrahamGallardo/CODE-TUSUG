package GUI;
import CONTROLLERS.ControladorSiniestro;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static Validacion.Validador.*;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JRException;
public class ReportarSiniestroGUI {
    public JButton lSiniestros,Rsiniestro,regresar,guardar,cerrar;
    public JLabel fechas,ingreso,indemnizado,siniestro,detalle,comentarios,tipo,importe,reclamado,pagado,estado,reclamante;
    public JTextField comentario,reclama,paga,Recla;
    public JRadioButton cliente,tercero;
    static String estadoSiniestro[]  = {"Pendiente","Resuelto"};
    static String tipoSiniestro[]  = {"Choques","Colisiones","Atropellos","Salidas de Via"};
    public JComboBox tipoS,status;
    ButtonGroup group;
    String ruta = "src/imagenes/";
    JFrame f;
    JPanel p;
    ActionListener listener;
    public String user;
    JMenuBar barra;
    JMenu archivo;
    JTextField ingresov,ingreso2v,ingreso3v;
    JMenuItem reestablecer;
    JMenuItem Cerrar_Sesion;
    public JDateChooser calendarioSiniestro,calendarioIngreso,calendarioIndemnizado;
    public JTextArea areaComentarios,Descripcion;
    ControladorSiniestro controlador;
    public ReportarSiniestroGUI()
    {
        controlador= new ControladorSiniestro(this);
        f = Builder.construirFrame("Reportar Siniestro", new Rectangle(0,0, 700, 649), false); 
        p = Builder.crearPanel(f, new Rectangle(0, 0, 700, 649),ruta+"fondo_vta_siniestro.png", false);
        
        user = "Usuario";
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
         
         
         //JCalendar
        calendarioSiniestro = new JDateChooser ();
        p.add(calendarioSiniestro);
        calendarioSiniestro.setBounds(92,153,114,22);
        
        calendarioIngreso = new JDateChooser ();
        p.add(calendarioIngreso);
        calendarioIngreso.setBounds(293,155,114,22);
        
        calendarioIndemnizado = new JDateChooser ();
        p.add(calendarioIndemnizado);
        calendarioIndemnizado.setBounds(509,155,114,22);
        
        listener = new ReportCustomListener();
        //labels
         fechas  = Builder.crearLabel(p,"Fechas",new Rectangle(325,130,61,18),new Color(66,66,66), null, new Font("Segoe UI", Font.BOLD, 14)); 
         siniestro = Builder.crearLabel(p,"Siniestro:",new Rectangle(35,157,55,18),new Color(66,66,66), null, new Font("Segoe UI", Font.PLAIN, 14)); 
         indemnizado = Builder.crearLabel(p,"Indemnizado:",new Rectangle(424,157,85,18),new Color(66,66,66), null, new Font("Segoe UI", Font.PLAIN, 14)); 
         ingreso = Builder.crearLabel(p,"Ingreso:",new Rectangle(232,157,52,18),new Color(66,66,66), null, new Font("Segoe UI", Font.PLAIN, 14)); 
         detalle = Builder.crearLabel(p,"Detalle del Siniestro:",new Rectangle(285,216,138,18),new Color(66,66,66), null, new Font("Segoe UI", Font.BOLD, 14)); 
         comentarios = Builder.crearLabel(p,"Comentarios Adicionales:",new Rectangle(39,239,162,18),new Color(66,66,66), null, new Font("Segoe UI", Font.PLAIN, 14)); 
         tipo = Builder.crearLabel(p,"Tipo:",new Rectangle(430,239,33,18),new Color(66,66,66), null, new Font("Segoe UI", Font.PLAIN, 14));  
         importe = Builder.crearLabel(p,"Importe",new Rectangle(216,364,60,18),new Color(66,66,66), null, new Font("Segoe UI", Font.BOLD, 14));  
         reclamado = Builder.crearLabel(p,"Reclamado:",new Rectangle(24,393,77,18),new Color(66,66,66), null, new Font("Segoe UI", Font.PLAIN, 14));  
         pagado = Builder.crearLabel(p,"Pagado:",new Rectangle(255,393,55,18),new Color(66,66,66), null, new Font("Segoe UI", Font.PLAIN, 14));  
         estado = Builder.crearLabel(p,"Estado",new Rectangle(553,364,46,18),new Color(66,66,66), null, new Font("Segoe UI", Font.BOLD, 14));  
         reclamante = Builder.crearLabel(p,"Reclamante",new Rectangle(305,436,80,18),new Color(66,66,66), null, new Font("Segoe UI", Font.BOLD, 14));  
         
        //botones
         lSiniestros = Builder.crearButtonIcon(p, "listado", ruta+"boton_listado_siniestros.png",new Rectangle(135,69,142,43), listener, true, false);
         Rsiniestro = Builder.crearButtonIcon(p, "reportar", ruta+"boton_reportar_siniestro_selected.png",new Rectangle(313,69,142,43), listener, true, false);
         regresar = Builder.crearButtonIcon(p, "regresar", ruta+"regresar.png",new Rectangle(53,560, 41,41), listener, true, false);
         guardar = Builder.crearButtonIcon(p, "guardar", ruta+"boton_guardar.png",new Rectangle(554,560,101,39), new ReportCustomListener(), true, false);
         //cerrar = Builder.crearButtonIcon(p, "cerrar", ruta+"boton_cerrar.png",new Rectangle(554,560,101,39), listener, true, false);
         
         
         
         //JTextArea
         areaComentarios = new JTextArea();
         p.add(areaComentarios);
         areaComentarios.setBounds(new Rectangle(39,267, 622,61));
         Descripcion = new JTextArea();
         p.add(Descripcion);
         Descripcion.setBounds(new Rectangle(124,464,537,70));
         
         //ComboBox
         tipoS = Builder.crearComboBox(p, new Rectangle(463,237,198,22),tipoSiniestro,null,null,null);
         status = Builder.crearComboBox(p, new Rectangle(519,389,114,22),estadoSiniestro,null,null,null);
         
         //JTextField
         reclama  = Builder.crearTextField(p,new Rectangle(101,389,137,25), "", null, null, new Font("Segoe UI", Font.PLAIN, 11), true,true, true);
         paga  = Builder.crearTextField(p,new Rectangle(320,389,137,25), "", null, null, new Font("Segoe UI", Font.PLAIN, 11), true,true, true);
         valida();
         
         //RadioButtons
         group = new ButtonGroup();
         cliente = new JRadioButton("Cliente");
         p.add(cliente);
         group.add(cliente);
         cliente.setBounds(new Rectangle(31,474,90,16));
         tercero = new JRadioButton("Tercero");
         p.add(tercero);
         group.add(tercero);
         tercero.setBounds(new Rectangle(31,500,90,16));
         
         
    }
    public void valida()
    {
        reclama.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validafloat(evt,reclama,20);
            }
        });
        paga.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validafloat(evt,paga,20);
            }
        });
    }
        class ReportCustomListener implements ActionListener{
        String op;
        @Override
        public void actionPerformed(ActionEvent e) {
            op = e.getActionCommand();
            switch (op){
                
                case "guardar":
                    
            {
                
                try {
                    String formato = calendarioIngreso.getDateFormatString();
                    Date date = calendarioIngreso.getDate();
                    SimpleDateFormat sdf = new SimpleDateFormat(formato);
                    String ingreso = String.valueOf(sdf.format(date));
                    String formato2 = calendarioIndemnizado.getDateFormatString();
                    Date date2 = calendarioIndemnizado.getDate();
                    SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
                    String ingreso2 = String.valueOf(sdf2.format(date2));
                    String formato3 = calendarioSiniestro.getDateFormatString();
                    Date date3 = calendarioSiniestro.getDate();
                    SimpleDateFormat sdf3 = new SimpleDateFormat(formato3);
                    String ingreso3 = String.valueOf(sdf3.format(date3));
                    ingresov=new JTextField();ingreso2v=new JTextField();ingreso3v=new JTextField();
                    ingresov.setText(ingreso);ingreso2v.setText(ingreso2);ingreso3v.setText(ingreso3);
                    if(validaIngreso(ingresov,ingreso2v,ingreso3v,reclama,paga))
                        if(validarFecha(ingreso3,ingreso)&&validarFecha(ingreso,ingreso2)){
                            controlador.agregaMan();
                            controlador.creaRepor();
                        }
                        else
                            JOptionPane.showMessageDialog(null, "Error del orden de fechas", "Error..!!", JOptionPane.ERROR_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Error..!!", JOptionPane.ERROR_MESSAGE);
                } catch (JRException ex) {
                    Logger.getLogger(ReportarSiniestroGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch(HeadlessException ef)
                {
                    System.err.println("Elija fecha valida "+ "Error..!!");
                }
            }
                    break;
                case "cerrarSesion":
                    break;
                case "regresar":
                    f.dispose();
                    break;
            }
        }
        
    }
}
