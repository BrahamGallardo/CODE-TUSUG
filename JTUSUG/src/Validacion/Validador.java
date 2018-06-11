package Validacion;

import CustomException.InvalidFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class Validador extends JFrame {

    public Validador() {
    }

    public static void validaNombre(java.awt.event.KeyEvent evt, JTextField text, int x) {
        Matcher m = Pattern.compile("[a-zA-ZáéíóúÁÉÍÓÚ]+").matcher(Character.toString(evt.getKeyChar()));
        if (!m.find()) {
            evt.consume();
        }
        if (text.getText().length() >= x) {
            evt.consume();
        }
        txtNumeroControlKeyTyped(evt);
    }

    public static void validaAlfanumerico(java.awt.event.KeyEvent evt, JTextField text, int x) {
        Matcher m = Pattern.compile("[a-zA-ZáéíóúÁÉÍÓÚ1234567890-]+").matcher(Character.toString(evt.getKeyChar()));
        if (!m.find()) {
            evt.consume();
        }
        if (text.getText().length() >= x) {
            evt.consume();
        }
        txtNumeroControlKeyTyped(evt);
    }

    public static void validaNum(java.awt.event.KeyEvent evt, JTextField text, int x) {//Valida sólo numeros                                                                               //x es la longitud
        Matcher m = Pattern.compile("[0-9]").matcher(Character.toString(evt.getKeyChar()));
        if (!m.find()) {
            evt.consume();
        }
        if (text.getText().length() >= x) {
            evt.consume();
        }
    }

    private static void jTextField1KeyReleased(java.awt.event.KeyEvent evt, JTextField text) {
        text.setText(text.getText().toUpperCase());
    }

    private static void txtNumeroControlKeyTyped(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        String cad = ("" + c).toUpperCase();
        c = cad.charAt(0);
        evt.setKeyChar(c);
    }

    public static boolean validaIngreso(JTextField... lb) {
        boolean aux = true;
        for (JTextField l : lb) {
            if (l.getText().equals("")) {
                aux = false;
                l.requestFocus();
                break;
            }
        }
        return aux;
    }

    public static boolean validarFecha(String fecha1, String fecha2) {
        boolean bandera = true;
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatoFecha_inicial = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha_inicial.setLenient(false);
            formatoFecha.parse(fecha1);
            formatoFecha_inicial.parse(fecha2);
            if (formatoFecha_inicial.parse(fecha1).compareTo(formatoFecha.parse(fecha2)) < 0) {
            } else {
                bandera = false;
                System.err.println("Error: Fecha inicial mayor que fecha final");
            }
        } catch (ParseException e) {
            bandera = false;
        }
        return bandera;
    }

    public static void validafloat(java.awt.event.KeyEvent evt, JTextField text, int x) {
        Matcher m = Pattern.compile("[0-9.,]+").matcher(Character.toString(evt.getKeyChar()));
        if (!m.find()) {
            evt.consume();
        }
        if (text.getText().length() >= x) {
            evt.consume();
        }
        txtNumeroControlKeyTyped(evt);
    }

    /*------------------------------<Funciones de Alejo>--------------------------------------*/
 /*------------------------------<Funciones para la clase Trabajador>----------------------*/
    public static String getRfcIfIsValid(String rfc) throws InvalidFormatException {
        Pattern p = Pattern.compile("([A-ZÑ&]{3,4}) ?(?:- ?)?(\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01])) ?(?:- ?)?([A-Z\\d]{2})([A\\d])");
        Matcher m = p.matcher(rfc);
        boolean b = m.matches();
        if (b) {
            return rfc;
        } else {
            throw new InvalidFormatException("RFC", 0);
        }
    }

    public static String getDateIfIsValid(String date) throws InvalidFormatException {
        return null;
    }

    public static KeyListener KEYonlyNumbers = new KeyAdapter(){  
        @Override
        public void keyTyped(KeyEvent e) {
            Matcher m = Pattern.compile("[0-9]").matcher(Character.toString(e.getKeyChar()));
            if (!m.find()) {
                e.consume();
            }
        }
    };
    
    public static KeyListener KEYalphanumeric = new KeyAdapter(){  
        @Override
        public void keyTyped(KeyEvent evt) {
            Matcher m = Pattern.compile("[[\\s]a-zA-ZáéíóúÁÉÍÓÚ1234567890-]+").matcher(Character.toString(evt.getKeyChar()));
            boolean c = Character.isSpaceChar(evt.getKeyChar());
        }
    };

    public static boolean emailValido(String email){
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(email);        
        return m.find();
    }
}
