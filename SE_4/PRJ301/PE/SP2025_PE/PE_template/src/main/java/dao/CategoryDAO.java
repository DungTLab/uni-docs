package dao;

import db.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;

/**
 * CategoryDAO - Thao tác CSDL liên quan đến Category.
 * Kế thừa db.DBContext và sử dụng this.getConnection() tương tự dự án WebPRJ.
 */
public class CategoryDAO extends DBContext {

    public CategoryDAO() {
    }

    /**
     * Lấy toàn bộ danh sách danh mục đổ vào dropdown lọc/thêm mới.
     */
    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT id, name FROM Category ORDER BY name ASC;";
        
        try (PreparedStatement ps = this.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Category(
                    rs.getInt("id"),
                    rs.getString("name")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Lấy chi tiết danh mục theo ID.
     */
    public Category getById(int id) {
        String sql = "SELECT id, name FROM Category WHERE id = ?;";
        try (PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
