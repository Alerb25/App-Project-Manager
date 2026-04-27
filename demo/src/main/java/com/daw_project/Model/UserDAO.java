import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public boolean insert(UserDO user) throws SQLException {
        String sql = "INSERT INTO User (name_tag, name, mail, pass) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getNameTag());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getMail());
            stmt.setString(4, user.getPass());
            return stmt.executeUpdate() > 0;
        }
    }

    // READ - by ID
    public UserDO findById(int idU) throws SQLException {
        String sql = "SELECT * FROM User WHERE id_U = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idU);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    // READ - by mail
    public UserDO findByMail(String mail) throws SQLException {
        String sql = "SELECT * FROM User WHERE mail = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, mail);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    // READ - by name_tag
    public UserDO findByNameTag(String nameTag) throws SQLException {
        String sql = "SELECT * FROM User WHERE name_tag = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nameTag);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    // READ - all
    public List<UserDO> findAll() throws SQLException {
        String sql = "SELECT * FROM User";
        List<UserDO> users = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(mapRow(rs));
            }
        }
        return users;
    }

    // UPDATE
    public boolean update(UserDO user) throws SQLException {
        String sql = "UPDATE User SET name_tag = ?, name = ?, mail = ?, pass = ? WHERE id_U = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getNameTag());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getMail());
            stmt.setString(4, user.getPass());
            stmt.setInt(5, user.getIdU());
            return stmt.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean delete(int idU) throws SQLException {
        String sql = "DELETE FROM User WHERE id_U = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idU);
            return stmt.executeUpdate() > 0;
        }
    }

    // Helper: map ResultSet row to UserDO
    private UserDO mapRow(ResultSet rs) throws SQLException {
        return new UserDO(
                rs.getInt("id_U"),
                rs.getString("name_tag"),
                rs.getString("name"),
                rs.getString("mail"),
                rs.getString("pass")
        );
    }
}