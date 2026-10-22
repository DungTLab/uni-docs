package dao;

import db.DBContext;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 * UserDAO - Xử lý đăng nhập, đăng ký tài khoản sử dụng thuật toán băm MD5 
 * (Khớp hoàn toàn với dự án WebPRJ của bạn).
 */
public class UserDAO extends DBContext {

    public UserDAO() {
    }

    /**
     * Thuật toán băm mật khẩu MD5 (Trùng khớp hàm hashMd5 trong WebPRJ).
     */
    private String hashMd5(String raw) {
        if (raw == null) return "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] mess = md.digest(raw.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : mess) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    /**
     * Xác thực đăng nhập.
     * @return Đối tượng User nếu đúng thông tin đăng nhập, ngược lại trả về null
     */
    public User login(String username, String rawPassword) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?;";
        try (PreparedStatement statement = this.getConnection().prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, hashMd5(rawPassword));
            
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("displayName"),
                        rs.getString("role"),
                        rs.getBoolean("status")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Đăng ký tài khoản người dùng mới.
     */
    public boolean register(User user) {
        String sql = "INSERT INTO users (username, password, displayName, role, status) VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement statement = this.getConnection().prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, hashMd5(user.getPassword()));
            statement.setString(3, user.getDisplayName());
            statement.setString(4, user.getRole() != null ? user.getRole() : "USER");
            statement.setBoolean(5, user.isStatus());
            
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Kiểm tra xem tên tài khoản đã tồn tại chưa.
     */
    public boolean checkDuplicateUsername(String username) {
        String sql = "SELECT id FROM users WHERE username = ?;";
        try (PreparedStatement statement = this.getConnection().prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
