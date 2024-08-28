package model;

import java.sql.*;
import java.time.LocalDateTime;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            }
        }
        return null;
    }

    public boolean updateUserPassword(int userId, String newPassword) throws SQLException {
        String sql = "UPDATE users SET password = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newPassword); // Ensure password is hashed before saving
            statement.setInt(2, userId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    }
    
    // Phương thức cập nhật mật khẩu cho người dùng dựa trên email
    public boolean updatePasswordByEmail(String email, String newPassword) throws SQLException {
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newPassword);
            statement.setString(2, email);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public User getUserByEmailOrPhone(String emailOrPhone) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ? OR phone = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, emailOrPhone);
            statement.setString(2, emailOrPhone);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            }
        }
        return null;
    }
    
    public boolean saveUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, email, phone, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getPassword()); // Ensure password is hashed before saving
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
    }

    public boolean saveResetToken(int userId, String resetToken) throws SQLException {
        // Add a token expiration time (e.g., 1 hour from now)
        LocalDateTime expirationTime = LocalDateTime.now().plusHours(1);

        String sql = "INSERT INTO reset_tokens (user_id, token, expiration_time) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setString(2, resetToken);
            statement.setObject(3, expirationTime);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
    }

    public boolean validateResetToken(String token) throws SQLException {
        String sql = "SELECT * FROM reset_tokens WHERE token = ? AND expiration_time > NOW()";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, token);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next(); // If a valid token exists, return true
            }
        }
    }

    public void deleteResetToken(String token) throws SQLException {
        String sql = "DELETE FROM reset_tokens WHERE token = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, token);
            statement.executeUpdate();
        }
    }
}
