package com.daw_project.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.daw_project.utils.Db;

public class PermissionDAO {

   

    // CREATE
    public boolean insert(PermissionDO permission) throws SQLException {
        String sql = "INSERT INTO Permission (fk_Pro, fk_U, per) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = Db.connection.prepareStatement(sql)) {
            stmt.setInt(1, permission.getFkPro());
            stmt.setInt(2, permission.getFkU());
            stmt.setInt(3, permission.getPer());
            return stmt.executeUpdate() > 0;
        }
    }

    // READ - by ID
    public PermissionDO findById(int idPer) throws SQLException {
        String sql = "SELECT * FROM Permission WHERE id_Per = ?";
        try (PreparedStatement stmt = Db.connection.prepareStatement(sql)) {
            stmt.setInt(1, idPer);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    // READ - all permissions of a project
    public List<PermissionDO> findByProject(int fkPro) throws SQLException {
        String sql = "SELECT * FROM Permission WHERE fk_Pro = ?";
        List<PermissionDO> permissions = new ArrayList<>();
        try (PreparedStatement stmt = Db.connection.prepareStatement(sql)) {
            stmt.setInt(1, fkPro);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    permissions.add(mapRow(rs));
                }
            }
        }
        return permissions;
    }

    // READ - all permissions of a user
    public List<PermissionDO> findByUser(int fkU) throws SQLException {
        String sql = "SELECT * FROM Permission WHERE fk_U = ?";
        List<PermissionDO> permissions = new ArrayList<>();
        try (PreparedStatement stmt = Db.connection.prepareStatement(sql)) {
            stmt.setInt(1, fkU);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    permissions.add(mapRow(rs));
                }
            }
        }
        return permissions;
    }

    // READ - permission for a specific user in a specific project
    public PermissionDO findByProjectAndUser(int fkPro, int fkU) throws SQLException {
        String sql = "SELECT * FROM Permission WHERE fk_Pro = ? AND fk_U = ?";
        try (PreparedStatement stmt = Db.connection.prepareStatement(sql)) {
            stmt.setInt(1, fkPro);
            stmt.setInt(2, fkU);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    // READ - all
    public List<PermissionDO> findAll() throws SQLException {
        String sql = "SELECT * FROM Permission";
        List<PermissionDO> permissions = new ArrayList<>();
        try (PreparedStatement stmt = Db.connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                permissions.add(mapRow(rs));
            }
        }
        return permissions;
    }

    // UPDATE
    public boolean update(PermissionDO permission) throws SQLException {
        String sql = "UPDATE Permission SET fk_Pro = ?, fk_U = ?, per = ? WHERE id_Per = ?";
        try (PreparedStatement stmt = Db.connection.prepareStatement(sql)) {
            stmt.setInt(1, permission.getFkPro());
            stmt.setInt(2, permission.getFkU());
            stmt.setInt(3, permission.getPer());
            stmt.setInt(4, permission.getIdPer());
            return stmt.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean delete(int idPer) throws SQLException {
        String sql = "DELETE FROM Permission WHERE id_Per = ?";
        try (PreparedStatement stmt = Db.connection.prepareStatement(sql)) {
            stmt.setInt(1, idPer);
            return stmt.executeUpdate() > 0;
        }
    }

    private PermissionDO mapRow(ResultSet rs) throws SQLException {
        return new PermissionDO(
                rs.getInt("id_Per"),
                rs.getInt("fk_Pro"),
                rs.getInt("fk_U"),
                rs.getInt("per")
        );
    }
}