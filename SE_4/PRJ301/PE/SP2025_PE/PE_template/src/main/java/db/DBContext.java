package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DBContext - Lớp kết nối CSDL SQL Server của bạn (đặt trong package db).
 * 
 * 💡 Mẹo Phòng Thi:
 * - Bước 1: Chạy script SQL (.sql) đề thi cấp trong SSMS.
 * - Bước 2: Điền đúng tên CSDL vừa chạy vào phần 'databaseName' ở DB_URL dưới đây.
 * - Bước 3: Thay đổi mật khẩu sa ở biến DB_PWD tương ứng mật khẩu máy phòng thi.
 */
public class DBContext {

    private Connection conn;
    
    // =========================================================================
    // ⚠️ ĐỔI THÔNG TIN KẾT NỐI DATABASE TẠI ĐÂY
    // =========================================================================
    private final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=PRJ301_PE_Template;encrypt=true;trustServerCertificate=true;";
    private final String DB_USER = "sa";
    private final String DB_PWD = "123456"; // Đổi mật khẩu sa của phòng thi (thường là 123456, sa, hoặc rỗng)
    // =========================================================================

    public DBContext() {
        try {
            // Nạp Driver SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // Khởi tạo đối tượng Connection
            this.conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
        } catch (ClassNotFoundException ex) {
            System.err.println("Lỗi nạp Driver: " + ex.getMessage());
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.err.println("Lỗi kết nối CSDL: " + ex.getMessage());
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Lấy đối tượng Connection đang kết nối.
     * Dùng getConnection() ở các lớp DAO kế thừa.
     */
    public Connection getConnection() {
        return conn;
    }
}
