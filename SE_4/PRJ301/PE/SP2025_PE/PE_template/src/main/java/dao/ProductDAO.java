package dao;

import db.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Product;

/**
 * ProductDAO - Thao tác CRUD cơ bản liên quan đến Product.
 * Kế thừa db.DBContext và sử dụng this.getConnection() tương tự dự án WebPRJ.
 */
public class ProductDAO extends DBContext {

    public ProductDAO() {
    }

    /**
     * 1. READ ALL - Lấy toàn bộ danh sách sản phẩm.
     */
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.*, c.name AS categoryName " +
                     "FROM Product p LEFT JOIN Category c ON p.categoryId = c.id " +
                     "ORDER BY p.id DESC;";
        try (PreparedStatement ps = this.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToProduct(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * 2. READ BY ID - Lấy thông tin sản phẩm theo ID.
     */
    public Product getById(int id) {
        String sql = "SELECT p.*, c.name AS categoryName " +
                     "FROM Product p LEFT JOIN Category c ON p.categoryId = c.id " +
                     "WHERE p.id = ?;";
        try (PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToProduct(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * 3. INSERT - Thêm sản phẩm mới.
     */
    public int insert(Product p) {
        String sql = "INSERT INTO Product (name, price, quantity, description, image, categoryId, status, createdDate) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = this.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getQuantity());
            ps.setString(4, p.getDescription());
            ps.setString(5, p.getImage());
            if (p.getCategoryId() > 0) {
                ps.setInt(6, p.getCategoryId());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }
            ps.setBoolean(7, p.isStatus());
            ps.setDate(8, p.getCreatedDate() != null ? p.getCreatedDate() : new Date(System.currentTimeMillis()));

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * 4. UPDATE - Cập nhật thông tin sản phẩm.
     */
    public boolean update(Product p) {
        String sql = "UPDATE Product SET name = ?, price = ?, quantity = ?, description = ?, image = ?, " +
                     "categoryId = ?, status = ?, createdDate = ? WHERE id = ?;";
        try (PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getQuantity());
            ps.setString(4, p.getDescription());
            ps.setString(5, p.getImage());
            if (p.getCategoryId() > 0) {
                ps.setInt(6, p.getCategoryId());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }
            ps.setBoolean(7, p.isStatus());
            ps.setDate(8, p.getCreatedDate());
            ps.setInt(9, p.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * 5. DELETE - Xoá vĩnh viễn sản phẩm.
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM Product WHERE id = ?;";
        try (PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * 6. CHECK DUPLICATE NAME - Kiểm tra trùng lặp tên sản phẩm.
     */
    public boolean checkDuplicateName(String name, int excludeId) {
        String sql = "SELECT id FROM Product WHERE name = ? AND id != ?;";
        try (PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, excludeId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * 7. MAP RESULTSET TO MODEL - Hàm ánh xạ bản ghi.
     */
    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getDouble("price"));
        p.setQuantity(rs.getInt("quantity"));
        p.setDescription(rs.getString("description"));
        p.setImage(rs.getString("image"));
        p.setCategoryId(rs.getInt("categoryId"));
        p.setStatus(rs.getBoolean("status"));
        p.setCreatedDate(rs.getDate("createdDate"));

        int catId = rs.getInt("categoryId");
        if (!rs.wasNull()) {
            Category c = new Category();
            c.setId(catId);
            c.setName(rs.getString("categoryName"));
            p.setCategory(c);
        }
        return p;
    }
}
