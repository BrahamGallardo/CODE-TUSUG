package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Rutas extends JFrame implements ActionListener {

    private JPanel pOpciones;
    private JButton btnAdel, btnAtras, btnAbrir, btnAumentar, btnDisminuir, btnImprimir;
    private File archivo;
    private CuadroImagen img;
    private JScrollPane scroll;
    private String imagenes[];
    private ArrayList<String> ListaImagenes;
    private String path;
    private int numImg;

    public Rutas() {
        super("Mi visor :-)");

        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        numImg = 1;

        //ListaImagenes.add("Ruta7");
        img = new CuadroImagen();
        scroll = new JScrollPane(img, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(scroll, BorderLayout.CENTER);
        pOpciones = new JPanel();
        btnAdel = new JButton("-->");
        btnAtras = new JButton("<--");
        btnAumentar = new JButton("+");
        btnDisminuir = new JButton("-");
        btnImprimir = new JButton("Imprimir");
        pOpciones.add(btnAtras);
        pOpciones.add(btnAdel);
//  pOpciones.add(btnAbrir);
        pOpciones.add(btnAumentar);
        pOpciones.add(btnDisminuir);
        pOpciones.add(btnImprimir);
        this.add(pOpciones, BorderLayout.SOUTH);
        //listeners
        btnAtras.addActionListener(this);
        btnAdel.addActionListener(this);
//  btnAbrir.addActionListener(this);
        btnAumentar.addActionListener(this);
        btnDisminuir.addActionListener(this);
        btnImprimir.addActionListener(this);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    //metodo de ActionListener
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdel) {
            numImg++;
            if (numImg >= ListaImagenes.size()) {
                numImg = 0;
            }
            img.setImagen(path + "/" + ListaImagenes.get(numImg));
        } else if (e.getSource() == btnAtras) {
            numImg--;
            if (numImg < 0) {
                numImg = ListaImagenes.size() - 1;
            }
            img.setImagen(path + "/" + ListaImagenes.get(numImg));
        } else if (e.getSource() == btnAbrir) {
            JFileChooser jfc = new JFileChooser();
            int apr = jfc.showOpenDialog(this);
            if (apr == jfc.APPROVE_OPTION) {
                archivo = jfc.getSelectedFile();
                imagenes = archivo.getParentFile().list();
                ListaImagenes = new ArrayList<String>();
                for (String img : imagenes) {
                    if (this.esImagen(img)) {
                        ListaImagenes.add(img);//cargo solo las imagenes
                    }
                }
                path = archivo.getParent();
                img.setImagen(archivo.getAbsoluteFile().toString());
            }
        } else if (e.getSource() == btnAumentar) {
            img.aumentar();
        } else if (e.getSource() == btnDisminuir) {
            img.disminuir();
        } else if (e.getSource() == btnImprimir) {
            img.imprimir();
        }
    }//funciones getter y setter

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    //Comprueba que solo cargue las imagenes
    public boolean esImagen(String dirImg) {
        if (dirImg.lastIndexOf(".jpg") > 0 || dirImg.lastIndexOf(".png") > 0 || dirImg.lastIndexOf(".gif") > 0 || dirImg.lastIndexOf(".jpeg") > 0) {
            return true;
        }
        return false;
    }



}