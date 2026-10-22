package dao;

import db.DBContext;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Staff;

public class StaffDAO extends DBContext {

    public Staff login(String username, String password) {
        String sql = "SELECT id, username, [password], full_name FROM Staff WHERE username = ? AND [password] = ?;";

        if (username != null && password != null && !username.trim().isEmpty() && !password.trim().isEmpty()) {
            try {
                String hashPassword = hashMd5(password);
                PreparedStatement statement = this.getConnection().prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, hashPassword);
                
                ResultSet rs = statement.executeQuery();
                
                if (rs.next()) {
                    Integer id = rs.getInt("id");
                    String fullName = rs.getString("full_name");
                    
                    Staff staff = new Staff();
                    staff.setId(id);
                    staff.setUserName(username);
                    staff.setHashPassword(hashPassword);
                    staff.setFullName(fullName);
                    
                    return staff;
                }
            } catch (SQLException ex) {
                Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    private String hashMd5(String raw) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] mess = md.digest(raw.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : mess) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
}
