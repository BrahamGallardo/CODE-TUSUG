/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entities.Municipio;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Alekhius
 */
public class Fachada {

    public static ArrayList<Municipio> lista;

    public static String getSelectedFileImage() {
        String path = "";
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        //if the user click on save in Jfilechooser
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            path = selectedFile.getAbsolutePath();
        } //if the user click on save in Jfilechooser
        else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("No File Select");
        }
        return path;
    }

    public static boolean isSomeEmpty(JTextComponent... fila) {
        for (JTextComponent actual : fila) {
            if (actual.getText().isEmpty()) {
                return true;
            }
        }
        return true;
    }

    public static ArrayList<Municipio> loadListaMunicipios() {
        //long time = System.currentTimeMillis();
        try {
            lista = new ArrayList<Municipio>();
            FileReader file = new FileReader("src/files/Oaxaca.txt");
            BufferedReader br = new BufferedReader(file);
            String[] munis;
            String line = br.readLine();
            line = br.readLine();
            Municipio actual;
            while (line != null) {
                //System.out.println(line);
                munis = line.split("-");                
                actual = new Municipio();
                actual.setCodigoPostal(Integer.parseInt(munis[0]));
                actual.setAsentamiento(munis[1]);
                actual.setTipo_asentamiento(munis[2]);
                actual.setMunicipio(munis[3]);
                lista.add(actual);
                line = br.readLine();
            }
            br.close();
            file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Municipio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Municipio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
        }
        return lista;
    }
    
    public static void main(String [] args){
        Fachada.loadListaMunicipios();
    }

}
