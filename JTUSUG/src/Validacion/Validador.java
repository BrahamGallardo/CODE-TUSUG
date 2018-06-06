package Validacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Validador extends JFrame
{
    public Validador(){}
    public static void validaNombre(java.awt.event.KeyEvent evt,JTextField text,int x){
        Matcher m = Pattern.compile("[a-zA-ZáéíóúÁÉÍÓÚ]+").matcher(Character.toString(evt.getKeyChar()));
        if (!m.find())
           evt.consume();
        if(text.getText().length()>=x)
            evt.consume();  
        txtNumeroControlKeyTyped(evt);
    }
    public static void validaAlfanumerico(java.awt.event.KeyEvent evt,JTextField text,int x){
        Matcher m = Pattern.compile("[a-zA-ZáéíóúÁÉÍÓÚ1234567890-]+").matcher(Character.toString(evt.getKeyChar()));
        if (!m.find())
           evt.consume();
        if(text.getText().length()>=x)
            evt.consume();  
        txtNumeroControlKeyTyped(evt);
    }
    public static void validaNum(java.awt.event.KeyEvent evt,JTextField text,int x){//Valida sólo numeros                                                                               //x es la longitud
        Matcher m = Pattern.compile("[0-9]").matcher(Character.toString(evt.getKeyChar()));
        if (!m.find())
           evt.consume();
        if(text.getText().length()>=x)
            evt.consume();  
    }
    private static void jTextField1KeyReleased(java.awt.event.KeyEvent evt,JTextField text){                                        
        text.setText(text.getText().toUpperCase());
    }
    private static void txtNumeroControlKeyTyped(java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar();
        String cad = ("" + c).toUpperCase();
        c = cad.charAt(0);
        evt.setKeyChar(c);
    } 
    public static boolean validaIngreso(JTextField... lb){
        boolean aux=true;
        for(JTextField l:lb)
            if(l.getText().equals(""))
            {
                aux=false;
                break;
            }
        return aux;
    }

}
