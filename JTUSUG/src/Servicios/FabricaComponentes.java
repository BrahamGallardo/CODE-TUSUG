package Servicios;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Alekhius
 */
public class FabricaComponentes {

    /**
     *
     * @param ui
     * @param params
     * @param args
     * @return
     */
    public static JTextField crearTextField(Container ui, String params, Object... args) {
        JTextField producto = new JTextField();
        ui.add(producto);

        int index = 0;
        addAtribs(producto, params, args);
        for (Character op : params.toCharArray()) {
            switch (op) {
                case 'T':
                    producto.setText((String) args[index++]);
                    break;
                case 'K':
                    producto.addKeyListener((KeyListener) args[index++]);
                    break;
                default:
                    index++;
            }
        }
        return producto;
    }

    private static void addAtribs(Component producto, String params, Object... args) {
        int index = 0;
        for (Character op : params.toCharArray()) {
            switch (op) {
                case 'b':
                    producto.setBounds((Rectangle) args[index++]);
                    break;
                case 'v':
                    producto.setVisible((Boolean) args[index++]);
                    break;
                case 'e':
                    producto.setEnabled((Boolean) args[index++]);
                    break;
                case 'f':
                    producto.setFont((Font) args[index++]);
                    break;
                case 'c':
                    producto.setForeground((Color) args[index++]);
                    break;
                case 'g':
                    producto.setBackground((Color) args[index++]);
                    break;
                default:
                    index++;
            }
        }
    }

    /**
     *
     * @param ui
     * @param params i = Image
     * @param args
     * @return
     */
    public static JLabel crearLabelImg(Container ui, String params, Object... args) {
        JLabel producto = new JLabel();
        ui.add(producto);

        int index = 0;
        addAtribs(producto, params, args);
        for (Character op : params.toCharArray()) {
            switch (op) {
                case 'i':
                    String Path = (String) args[index++];//.replace('\u005C\u005C', '\u002F'));
                    System.err.println(Path);
                    ImageIcon fot = new ImageIcon(Path.replace('\u005C\u005C', '\u002F'));
                    Icon icon = new ImageIcon(fot.getImage().getScaledInstance(producto.getWidth(), producto.getHeight(), Image.SCALE_DEFAULT));
                    //Icon icon = new ImageIcon(Path);
                    producto.setIcon(icon);
                    break;
                default:
                    index++;
            }
        }
        return producto;
    }

    public static JMenuBar crearMenuBar(Container ui, String params, Object... args) {
        JMenuBar producto = new JMenuBar();
        ui.add(producto);

        int index = 0;
        addAtribs(producto, params, args);
        for (Character op : params.toCharArray()) {
            switch (op) {
                case 'o':
                    producto.setOpaque((Boolean)args[index++]);
                    break;
            }
        }
        return producto;
    }

    public static JMenu crearMenu(Container ui, String params, Object... args) {
        JMenu producto = new JMenu();
        ui.add(producto);

        int index = 0;
        addAtribs(producto, params, args);
        for (Character op : params.toCharArray()) {
            switch (op) {
                case 'T':
                    producto.setText((String)args[index++]);
                    break;
            }
        }
        return producto;
    }
}
