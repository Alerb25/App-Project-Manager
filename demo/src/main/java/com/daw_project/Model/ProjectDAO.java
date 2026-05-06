package com.daw_project.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.daw_project.utils.Db;

public class ProjectDAO {

    public ProjectDAO() {
        Db.conectar();
    }

    // CREATE
    public boolean insert(ProjectDO project) throws SQLException {
        String sql = "INSERT INTO Project (title, descr, url, dif, theme, updated) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = Db.connection.prepareStatement(sql)) {
            stmt.setString(1, project.getTitle());
            stmt.setString(2, project.getDescr());
            stmt.setString(3, project.getUrl());
            stmt.setInt(4, project.getDif());
            stmt.setString(5, project.getTheme());
            stmt.setBoolean(6, project.getUpdated());
            return stmt.executeUpdate() > 0;
        }
    }

    // READ - by ID
    public ProjectDO findById(int idPro) throws SQLException {
        String sql = "SELECT * FROM Project WHERE id_Pro = ?";
        try (PreparedStatement stmt = Db.connection.prepareStatement(sql)) {
            stmt.setInt(1, idPro);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    // READ - all
    public List<ProjectDO> listar() throws SQLException {
        String sql = "SELECT * FROM Project";
        List<ProjectDO> projects = new ArrayList<>();
        try (PreparedStatement stmt = Db.connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                projects.add(mapRow(rs));
            }
        }
        return projects;
    }

    // UPDATE
    public boolean update(ProjectDO project) throws SQLException {
        String sql = "UPDATE Project SET title = ?, descr = ?, url = ?, dif = ?, theme = ?, updated = ?, WHERE id_Pro = ?";
        try (PreparedStatement stmt = Db.connection.prepareStatement(sql)) {
            stmt.setString(1, project.getTitle());
            stmt.setString(2, project.getDescr());
            stmt.setString(3, project.getUrl());
            stmt.setInt(4, project.getDif());
            stmt.setString(5, project.getTheme());
            stmt.setBoolean(6, project.getUpdated());
            stmt.setInt(7, project.getIdPro());            
            return stmt.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean delete(int idPro) throws SQLException {
        String sql = "DELETE FROM Project WHERE id_Pro = ?";
        try (PreparedStatement stmt = Db.connection.prepareStatement(sql)) {
            stmt.setInt(1, idPro);
            return stmt.executeUpdate() > 0;
        }
    }

    private ProjectDO mapRow(ResultSet rs) throws SQLException {
        return new ProjectDO(
                rs.getInt("id_Pro"),
                rs.getString("title"),
                rs.getString("descr"),
                rs.getString("url"),
                rs.getInt("dif"),
                rs.getString("theme"),
                rs.getBoolean("updated")

        );
    }
}