
package GUI;

import com.sun.awt.AWTUtilities;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Splash implements Runnable{
    String              ruta = "src/imagenes/";
    public JFileChooser url_img;
    JFrame a;
    JPanel p;
    JLabel progreso,status;
    
    public Splash(){
    a= new JFrame();
    a.setBounds(200,50,650,285);
    
    a.setLocationRelativeTo(null);
    a.setUndecorated(true);
    AWTUtilities.setWindowOpaque(a, false);
    initSplash();
    Thread hilo = new Thread(this);
    hilo.start();
    }
    
    
    public void initSplash(){
        
        
       // p = Builder.crearPanel(a, new Rectangle(0, 0, 700, 600),"", false );
        JLabel fondo    =   Builder.crearLabelImagen(a, ruta + "tusug.png",     new Rectangle(0,0,600,205));
        p               =   Builder.crearPanel(a,                               new Rectangle(0, 0, 700, 600),"", false );
        status          =   Builder.crearLabel(p, "Cargando...",                new Rectangle(262,228,107,25), null, null, new Font("Segoe UI", Font.PLAIN, 15));
       progreso         =   Builder.crearLabelImagen(p, ruta + "bus.png",       new Rectangle(104,173,64,64));
       
       Color aguamarina = new Color(0,153,204);
       status.setForeground(aguamarina);
      a.setVisible(true);
    }
    
    
    public void run(){
        for(int i = 1;i<=360;i++){
            progreso.setLocation(progreso.getX()+1,progreso.getY());
            pausa(10);
            if(i>=250){
                status.setText("Completando...");
            }
        }
        a.dispose();
        LoginGUI l = new LoginGUI();
    }
    
    public void pausa(int mlSeg){
        try{
            Thread.sleep(mlSeg);
        }catch(Exception e){}
    }
    
    public static void main(String[] args){
        Splash p = new Splash();
        //p.initSplash();
    }
}
