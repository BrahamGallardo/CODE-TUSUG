
package GUI;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.JPanel;
 
 
public class CuadroImagen extends JPanel implements Printable{
 
 private float zoom;
 private int ancho,alto;
 private BufferedImage bufferImagen;
 private Image imagen,imagenAux;
 private boolean hayFoto = false;
 public CuadroImagen(){
  this.zoom=0;
  this.setBounds(0, 0, 500, 500);
  this.setVisible(true);
   
 }
  
 //metodo que coloca la imagen que va ser dibujada 
  
 public void setImagen(String dirImg){
  this.zoom=0;
  try {
   this.bufferImagen = ImageIO.read(new File(dirImg));
         imagen = bufferImagen;
         imagenAux=imagen;
   hayFoto = true;
         ancho = imagen.getWidth(this);
         alto = imagen.getHeight(this);
         this.resize();
   repaint();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
  
 //metodo paint que dibuja la imagen en el JPanel
  
 public void paint(Graphics g){
  Graphics2D g2d = (Graphics2D) g;
  if(hayFoto){
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         //se anade la foto
         g2d.translate(this.getWidth() / 2, this.getHeight() / 2);
      g2d.translate(-ancho / 2, -alto / 2);
         g2d.drawImage(imagenAux, 0, 0,ancho, alto, this);
         setOpaque(false);
  }else{
   setOpaque(true);
  }
  super.paintComponent(g2d);
 }
  
 //metodos del zoom
  
 public void aumentar(){
  this.zoom=(float) (this.zoom + 0.10);
 
  ancho = (int) (imagen.getWidth(this)*(zoom+1));
  alto = (int) (imagen.getHeight(this)*(zoom+1));
  imagenAux = imagen.getScaledInstance(ancho, alto, Image.SCALE_AREA_AVERAGING);
  resize();
  repaint();
  }
  
 public void disminuir(){
  this.zoom= (float) (this.zoom - 0.10);
  ancho = (int) (imagen.getWidth(this)*(zoom+1));
  alto = (int) (imagen.getHeight(this)*(zoom+1));
  imagenAux = imagen.getScaledInstance(ancho, alto, Image.SCALE_AREA_AVERAGING);
  resize();
  repaint();
 }
  
  
 //redimenzionar
  
  
 @SuppressWarnings("deprecation")
 public void resize(){
  this.setPreferredSize(new Dimension(ancho,alto));
  this.resize(ancho, alto);
 }
  
 //metodo de la interface printable
 
 @Override
 public int print(Graphics g, PageFormat pf, int indexPage)
   throws PrinterException {
  if(indexPage==0){
   g.clearRect(0, 0, this.getWidth(), this.getHeight());
   g.drawImage(imagen, 0, 0, (int)pf.getWidth(),(int) pf.getHeight(), this);
   return Printable.PAGE_EXISTS;
  }else{
   return Printable.NO_SUCH_PAGE;
  }
 }
  
 public void imprimir(){
  PrinterJob job = PrinterJob.getPrinterJob();
  job.setPrintable(this);
  PageFormat pageFormat = new PageFormat();
  pageFormat = job.pageDialog(pageFormat);
  // Diálogo para confirmar impresion.
  // Devuelve true si el usuario decide imprimir.
  if (job.printDialog())
   try {
    job.print();
   } catch (PrinterException e) {
    e.printStackTrace();
   }
 }
} 