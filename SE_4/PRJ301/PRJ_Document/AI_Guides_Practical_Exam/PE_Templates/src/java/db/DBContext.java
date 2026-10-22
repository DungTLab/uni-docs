package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mẫu cấu hình Database Connection.
 * Yêu cầu: Copy class này vào package 'db' trong bài thi của bạn.
 * Chú ý: Đổi tên CSDL trong biến DB_URL nếu đề bài yêu cầu kết nối vào DB khác.
 */
public class DBContext {
    private Connection conn;
    
    // CẤU HÌNH DATABASE Ở ĐÂY
    private final String DB_URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=YourDatabaseName;encrypt=false";
    private final String DB_USER = "sa";
    private final String DB_PWD = "12341234"; // Thay đổi theo mật khẩu máy trường hoặc máy bạn

    public DBContext() {
        try {
            // Đăng ký Driver SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Khởi tạo Connection
            this.conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Phương thức dùng để lấy Connection
     * @return Connection object
     */
    public Connection getConnection() {
        return conn;
    }
}
