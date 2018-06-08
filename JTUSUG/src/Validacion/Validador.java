package Validacion;

import CustomException.InvalidFormatException;
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
    
    /*------------------------------<Funciones de Alejo>--------------------------------------*/
    /*------------------------------<Funciones para la clase Trabajador>----------------------*/
    public static String getRfcIfIsValid(String rfc) throws InvalidFormatException{
        Pattern p = Pattern.compile("([A-ZÑ&]{3,4}) ?(?:- ?)?(\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01])) ?(?:- ?)?([A-Z\\d]{2})([A\\d])");
        Matcher  m = p.matcher(rfc);
        boolean b = m.matches();
        if (b) return rfc;
        else throw new InvalidFormatException("RFC", 0);
    }
    
        public static String getDateIfIsValid(String date) throws InvalidFormatException{
           return null;
        }

}
