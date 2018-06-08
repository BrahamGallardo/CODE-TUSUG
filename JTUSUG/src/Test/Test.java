package Test;

import CONTROLLERS.Conexion;
import GUI.Builder;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;

public class Test {

    /**
     * JFrame ventana;
     *
     * ActionListener eventos; public Test(){ eventos = new
     * CustomActionListener(); ventana = Builder.construirFrame("Hi", new
     * Rectangle(100,0,600,600), false); ventana.setLayout(new FlowLayout());
     * Builder.crearBoton(ventana, "", new Rectangle(), eventos, true, true); }
     *
     * public void setEventos(ActionListener eventos) {
     *
     * this.eventos = eventos; }
     *
     * public static void main(String [] args){ Test t = new Test();
     * CustomActionListener action; action = new CustomActionListener();
     * CustomActionListener2 action2; action2 = new CustomActionListener2();
     *
     * t.setEventos(action); t.setEventos(action2); } }
     *
     * class CustomActionListener implements ActionListener{
     *
     * @Override public void actionPerformed(ActionEvent e) {
     * System.out.println("Test.Test.CustomActionListener.actionPerformed()"); }
     * }
     *
     * class CustomActionListener2 implements ActionListener{
     *
     * @Override public void actionPerformed(ActionEvent e) {
     * System.out.println("Test.Test.CustomActionListener2.actionPerformed()");
     * }
     *
     */
    public static void main(String[] args) {
        /**
         * Pattern p = Pattern.compile("([A-ZÑ&]{3,4}) ?(?:-
         * ?)?(\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01])) ?(?:-
         * ?)?([A-Z\\d]{2})([A\\d])"); Matcher m = p.matcher("GUR960505RF4");
         * boolean b = m.matches(); System.out.println(b);
         *
         * m = p.matcher("ORP830422DI6"); b = m.matches();
         * System.out.println(b);
         *
         * m = p.matcher("gur960505rrf4"); b = m.matches();
         * System.out.println(b);
         */
        //-----------------<Test obtener año de rfc>----------------------        
        /*System.err.println(
                "GUR960505RF4".substring(3,5)
        );
        System.err.println(
                "GUR960505RF4".substring(5,7)
        );
        System.err.println(
                "GUR960505RF4".substring(7,9)
        );*/
        //System.err.println(new java.util.Date().get);
    }
}
