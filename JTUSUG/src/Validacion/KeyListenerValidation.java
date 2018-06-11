/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validacion;

import java.awt.event.KeyAdapter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.JTextComponent;


public class KeyListenerValidation extends KeyAdapter{
        int numLetrasValidas;
        public KeyListenerValidation(int length){
            super();
            numLetrasValidas = length;
        }
        @Override
        public void keyTyped(java.awt.event.KeyEvent evt){            
            Matcher m = Pattern.compile("[[\\s]a-zA-ZáéíóúÁÉÍÓÚ1234567890-]+").matcher(Character.toString(evt.getKeyChar()));
            //boolean b = Character.isAlphabetic(evt.getKeyChar());            
            boolean c = Character.isSpaceChar(evt.getKeyChar());
            if(!m.find()||((JTextComponent)evt.getComponent()).getText().length()>=numLetrasValidas)
                evt.consume();
            else evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
        }
    }
