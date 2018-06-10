/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CONTROLLERS.Expedientes;
import CONTROLLERS.SQLRepSinies;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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

/**
 *
 * @author Guillermo
 */
public class GUIExpedientes {
    
    public JButton lexpediente, Rexpdiente, regresar, guardar, abrir, imprimir;
    static String encabezado[] = {"RFC", "Nombre", "Apellido Paterno", "Apellido Materno"};
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
    Expedientes controlador;
    public DefaultTableModel model;
    GUIExpedientes a;
    public GUIExpedientes(){
        valor="";
        model=new DefaultTableModel(null, encabezado);
        controlador= new Expedientes(this);
        user = "Usuario";
        f = Builder.construirFrame("Lista de Expedientes", new Rectangle(0, 0, 700, 649), false);
        p = Builder.crearPanel(f, new Rectangle(2, 0, 703, 649), ruta + "fondo_lista_siniestros.png", false);
        a=this;
        //Menu
        barra = new JMenuBar();
        barra.setBackground(Color.GRAY);
        archivo = new JMenu(user);
        reestablecer = new JMenuItem("Reestablecer Contraseña");
        Cerrar_Sesion = new JMenuItem("Cerrar Sesión");
        archivo.add(reestablecer);
        archivo.add(Cerrar_Sesion);
        barra.add(archivo);
        p.add(barra);
        barra.setBounds(new Rectangle(513, 75, 55, 34));
        barra.setVisible(true);
        listener = new ReportCustomListener();
        //botones
        lexpediente = Builder.crearButtonIcon(p, "listado", ruta + "boton_listado_siniestros_selected.png", new Rectangle(135, 69, 142, 43), listener, true, false);
        Rexpdiente = Builder.crearButtonIcon(p, "reportar", ruta + "boton_reportar_siniestro.png", new Rectangle(313, 69, 142, 43), listener, true, false);
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
    
    public static void main(String[] args){
        GUIExpedientes d= new GUIExpedientes();
    }



    public String tableMouseClicked(MouseEvent evt) {
        int filasele = tabla.getSelectedRow();
        valor =tabla.getValueAt(filasele,0).toString();
        System.out.println(valor);
        return valor;
        
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
                  controlador.creaRepor();
                } catch (JRException ex) {
                    Logger.getLogger(GUIReporteManten.class.getName()).log(Level.SEVERE, null, ex);
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
