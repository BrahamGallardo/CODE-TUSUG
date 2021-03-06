package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GFacturas extends JFrame {

    String ruta = "src/imagenes/";
    public JLabel facturas;
    public JButton regresar, cSesion, gFactura, vFactura;
    JFrame f;
    JPanel p;
    ActionListener listener;

    public GFacturas() {
        listener = new GFacturas.CustomActionListener();
        f = Builder.construirFrame("Notas de Compra", new Rectangle(0, 0, 700, 600), false);
        p = Builder.crearPanel(f, new Rectangle(0, 0, 700, 600), ruta + "pagina_de_fondo.png", false);

        //Etiquetas
        facturas = Builder.crearLabel(p, "Notas", new Rectangle(317, 135, 61, 18), Color.darkGray, new Color(102, 205, 205), new Font("Arial", Font.BOLD, 14));
        listener = new CustomActionListener();
        //Botones
        gFactura = Builder.crearButtonIcon(p, "Generar Notas", ruta + "img_generar_factura.png", new Rectangle(104, 165, 198, 326), listener, true, false);
        vFactura = Builder.crearButtonIcon(p, "Visualizar Factura", ruta + "img_visualizar_factura.png", new Rectangle(380, 165, 198, 326), listener, true, false);
        cSesion = Builder.crearButtonIcon(p, "cerrarSesion", ruta + "boton_cerrar_sesion.png", new Rectangle(460, 506, 142, 45), listener, false, false);
        regresar = Builder.crearButtonIcon(p, "regresar", ruta + "regresar.png", new Rectangle(326, 510, 41, 41), listener, false, false);
        f.setVisible(true);
        p.setVisible(true);

    }

    //JFrame MenuPrincipal;
    ActionListener back = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }

    };
    GFacturas1 g = null;

    class CustomActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String op = e.getActionCommand();
            switch (op) {
                case "regresar":
                case "cerrarSesion":
                    f.dispose();
                    if (g != null) {
                        g.dispose();
                    }
                    break;
                case "Generar Notas":
                    g = new GFacturas1();
                    //g.cSesion.addActionListener(listener);
                    
                    //f.dispose();
                    break;
                case "Visualizar Factura":
                    VFacturas v = new VFacturas();
                //f.dispose();
            }
        }
    }

    public static void main(String[] agrs) {
        GFacturas g = new GFacturas();
    }
}
