package com.daw_project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.daw_project.utils.Db;

public class CrudDAO {

    public Connection con;
    public String nombreTabla;
    public List<String> campos;

    public CrudDAO() {
        // CORRECCIÓN: Llamamos al método y luego asignamos la variable estática
        Db.conectar();
        this.con = Db.connection; 
    }

    public ResultSet cargarPorId(Object id) {
        ResultSet rs = null;
        try {
            String query = "select * from " + nombreTabla + " where id = ?";
            PreparedStatement stmt = this.con.prepareStatement(query);
            stmt.setObject(1, id);
            rs = stmt.executeQuery();
        } catch (Exception e) {
            System.out.println("Hubo un problema con la BD");
            e.printStackTrace();
        }
        return rs;
    }

    public Map<String, Object> cargarListaPorId(Object id) {
        ResultSet rs = null;
        Map<String, Object> registro = null; // Corregido: añadido diamante genérico

        try {
            String query = "select * from " + nombreTabla + " where id = ?";
            PreparedStatement stmt = this.con.prepareStatement(query);
            stmt.setObject(1, id);
            rs = stmt.executeQuery();

            // Importante: falta un rs.next() aquí para posicionar el puntero
            if (rs.next()) {
                registro = new HashMap<String, Object>();
                for (String campo : campos) {
                    registro.put(campo, rs.getObject(campo));
                }
            }

        } catch (Exception e) {
            System.out.println("Hubo un problema con la BD");
            e.printStackTrace();
        }
        return registro;
    }

    public ResultSet cargarTodos() {
        ResultSet rs = null;
        try {
            String query = "select * from " + nombreTabla;
            PreparedStatement stmt = this.con.prepareStatement(query);
            rs = stmt.executeQuery();
        } catch (Exception e) {
            System.out.println("Hubo un problema con la BD");
            e.printStackTrace();
        }
        return rs;
    }

    public List<Map<String, Object>> cargarListaTodos() {
        ResultSet rs = null;
        List<Map<String, Object>> listaDatos = null;
        try {
            String query = "select * from " + nombreTabla;
            PreparedStatement stmt = this.con.prepareStatement(query);
            rs = stmt.executeQuery();

            listaDatos = new ArrayList<Map<String, Object>>();
            while (rs.next()) {
                Map<String, Object> registro = new HashMap<String, Object>();
                for (String campo : campos) {
                    registro.put(campo, rs.getObject(campo));
                }
                listaDatos.add(registro);
            }
        } catch (Exception e) {
            System.out.println("Hubo un problema con la BD");
            e.printStackTrace();
        }
        return listaDatos;
    }
}