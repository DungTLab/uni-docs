package dao;

import db.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mẫu chuẩn DAO. 
 * YÊU CẦU ĐI THI:
 * 1. Đổi 'BaseDAO' thành tên thực tế (VD: 'ProductDAO')
 * 2. Đổi 'Entity' thành tên Model thực tế (VD: 'Product')
 * 3. Thay đổi Tên Bảng (TableName) và Tên Cột (ColumnName) trong các câu SQL.
 */
public class BaseDAO extends DBContext {

    // ----------------------------------------------------
    // 1. READ: Lấy toàn bộ danh sách (Dành cho chức năng List/Display)
    // ----------------------------------------------------
    public List<Object> getAll() {
        List<Object> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM TableName"; // ĐỔI TÊN BẢNG
            PreparedStatement stm = this.getConnection().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                // Đọc dữ liệu từ ResultSet (ĐỔI TÊN CỘT BÊN DƯỚI)
                int id = rs.getInt("id");
                String name = rs.getString("name");
                
                // Khởi tạo đối tượng và đưa vào danh sách
                // Entity obj = new Entity(id, name);
                // list.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // ----------------------------------------------------
    // 2. READ: Lấy thông tin 1 đối tượng theo ID (Dùng khi Update hoặc xem Details)
    // ----------------------------------------------------
    public Object getById(int id) {
        try {
            String sql = "SELECT * FROM TableName WHERE id = ?"; // ĐỔI TÊN BẢNG VÀ CỘT KHÓA CHÍNH
            PreparedStatement stm = this.getConnection().prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                int retrievedId = rs.getInt("id");
                String name = rs.getString("name");
                
                // Trả về đối tượng
                // return new Entity(retrievedId, name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // ----------------------------------------------------
    // 3. CREATE: Thêm mới 1 đối tượng
    // ----------------------------------------------------
    public int insert(Object obj) { // ĐỔI KIỂU DỮ LIỆU OBJECT
        try {
            String sql = "INSERT INTO TableName (col1, col2) VALUES (?, ?)"; // ĐỔI BẢNG VÀ SỐ LƯỢNG CỘT
            PreparedStatement stm = this.getConnection().prepareStatement(sql);
            
            // Gán các giá trị tham số
            // stm.setString(1, obj.getCol1());
            // stm.setInt(2, obj.getCol2());
            
            return stm.executeUpdate(); // Trả về số dòng bị ảnh hưởng
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // ----------------------------------------------------
    // 4. UPDATE: Cập nhật thông tin đối tượng
    // ----------------------------------------------------
    public int update(Object obj) { // ĐỔI KIỂU DỮ LIỆU OBJECT
        try {
            String sql = "UPDATE TableName SET col1 = ?, col2 = ? WHERE id = ?"; // ĐỔI TÊN BẢNG, CỘT
            PreparedStatement stm = this.getConnection().prepareStatement(sql);
            
            // stm.setString(1, obj.getCol1());
            // stm.setInt(2, obj.getCol2());
            // stm.setInt(3, obj.getId());
            
            return stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // ----------------------------------------------------
    // 5. DELETE: Xóa 1 đối tượng theo ID
    // ----------------------------------------------------
    public int delete(int id) {
        try {
            String sql = "DELETE FROM TableName WHERE id = ?"; // ĐỔI TÊN BẢNG VÀ CỘT KHÓA CHÍNH
            PreparedStatement stm = this.getConnection().prepareStatement(sql);
            stm.setInt(1, id);
            
            return stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
