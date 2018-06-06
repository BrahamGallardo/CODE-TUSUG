package Test;

import CONTROLLERS.Conexion;
import GUI.Builder;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Test {
    JFrame ventana;
    
    ActionListener eventos;
    public Test(){
        eventos = new CustomActionListener();
        ventana = Builder.construirFrame("Hi", new Rectangle(100,0,600,600), false);
        ventana.setLayout(new FlowLayout());
        Builder.crearBoton(ventana, "", new Rectangle(), eventos, true, true);
    }

    public void setEventos(ActionListener eventos) {
        
        this.eventos = eventos;
    }
             
    public static void main(String [] args){
        Test t = new Test();
        CustomActionListener action;
        action = new CustomActionListener();
        CustomActionListener2 action2;
        action2 = new CustomActionListener2();
        
        t.setEventos(action);
        t.setEventos(action2);
    }
}

class CustomActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Test.Test.CustomActionListener.actionPerformed()");
        }
    }
    
    class CustomActionListener2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Test.Test.CustomActionListener2.actionPerformed()");
        }
    }