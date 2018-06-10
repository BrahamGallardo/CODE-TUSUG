/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLERS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alekhius
 */
public class SQLAutobus {

    Connection conn;

    public SQLAutobus() {
        //Conexion.setConfiguracion("postgres", "Gallardo.1997");
        conn = Conexion.getConexion();
    }

    public void ingresarAutobus(String matr, String id, String marca,
            String num_eco, int km, int asientos,
            String url_img) throws SQLException {

        String sql
                = "insert into sistemaTusug.autobus values (?,?,?,?,?,?,?)";
        PreparedStatement pst = null;
        pst = conn.prepareStatement(sql);
        pst.setString(1, matr);
        pst.setString(2, id);
        pst.setString(3, marca);
        pst.setString(4, num_eco);
        pst.setInt(5, km);
        pst.setInt(6, asientos);
        pst.setString(7, url_img);
        int n = pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "AUTOBUS AGREGADO");
        System.err.println(
                n);
    }

    // caso "matricula"
    public void borrarAutobusBy(String arg) throws SQLException {
        PreparedStatement stmt = null;
        //int tuplas = 0;
        try {
            stmt = conn.prepareStatement("delete from sistematusug.autobus where lower(matricula) = ?");
            stmt.setString(1, arg.toLowerCase());
            int tuplas = stmt.executeUpdate();
            //rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public String[] obtenerRegistro(String matricula) {
        String[] registro = new String[7];
        String sql = "select * from sistemaTusug.autobus where lower(matricula) = '" + matricula + "'";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            ResultSet res = pst.executeQuery();
           // res.next();
           while(res.next()){
            registro[0] = res.getString("matricula");
            registro[1] = res.getString("id");
            registro[2] = res.getString("marca");
            registro[3] = res.getString("numero_economico");
            registro[4] = Integer.toString(res.getInt("kilometraje"));
            registro[5] = Integer.toString(res.getInt("asientos"));
            registro[6] = res.getString("url_img");
           // res.close();
           }

        } catch (SQLException ex) {
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registro;
    }

    public ArrayList listaAutobuses() {
        Conexion.getConexion();
        ArrayList<String> listBuses = new ArrayList<String>();
        String sql = null;// = "select count(matricula) as total from sistemaTusug.autobus ";
        PreparedStatement pst;
        ResultSet res;
        try {
            sql = "select matricula from sistemaTusug.autobus ORDER BY matricula";
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            while (res.next()) {
                String dato = res.getString("matricula");
                listBuses.add(dato);
            }
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLAutobus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBuses;
    }

    public void update(String matricula, String marca, String num_eco, int km, int asientos) throws SQLException {
        try {
            PreparedStatement pstm = conn.prepareStatement("UPDATE sistemaTusug.autobus SET "
                    + //+"matricula= ? ," +
                    "marca= ? ,"
                    + "numero_economico= ? ,"
                    + "kilometraje= ? ,"
                    + "asientos= ? "
                    + "WHERE lower(matricula) = ? ");
            pstm.setString(1, marca);
            pstm.setString(2, num_eco);
            pstm.setInt(3, km);
            pstm.setInt(4, asientos);
            pstm.setString(5, matricula.toLowerCase());
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
