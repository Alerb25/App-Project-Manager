import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    private Connection connection;

    public ProjectDAO(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public boolean insert(ProjectDO project) throws SQLException {
        String sql = "INSERT INTO Project (title, descr, url) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, project.getTitle());
            stmt.setString(2, project.getDescr());
            stmt.setString(3, project.getUrl());
            return stmt.executeUpdate() > 0;
        }
    }

    // READ - by ID
    public ProjectDO findById(int idPro) throws SQLException {
        String sql = "SELECT * FROM Project WHERE id_Pro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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
    public List<ProjectDO> findAll() throws SQLException {
        String sql = "SELECT * FROM Project";
        List<ProjectDO> projects = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                projects.add(mapRow(rs));
            }
        }
        return projects;
    }

    // UPDATE
    public boolean update(ProjectDO project) throws SQLException {
        String sql = "UPDATE Project SET title = ?, descr = ?, url = ? WHERE id_Pro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, project.getTitle());
            stmt.setString(2, project.getDescr());
            stmt.setString(3, project.getUrl());
            stmt.setInt(4, project.getIdPro());
            return stmt.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean delete(int idPro) throws SQLException {
        String sql = "DELETE FROM Project WHERE id_Pro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPro);
            return stmt.executeUpdate() > 0;
        }
    }

    // Helper: map ResultSet row to ProjectDO
    private ProjectDO mapRow(ResultSet rs) throws SQLException {
        return new ProjectDO(
                rs.getInt("id_Pro"),
                rs.getString("title"),
                rs.getString("descr"),
                rs.getString("url")
        );
    }
}
