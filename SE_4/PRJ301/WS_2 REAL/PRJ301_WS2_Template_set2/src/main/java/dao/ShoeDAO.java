package dao;

import db.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Shoe;

public class ShoeDAO extends DBContext {

    public ArrayList<Shoe> findAll() {
        ArrayList<Shoe> list = new ArrayList<>();
        String sql = "SELECT s.id, s.[name], c.[name] AS 'category_name' ,s.purpose,  s.[size], s.price, s.stock_quantity, st.full_name AS 'creator_name', c.id AS 'category_id'\n"
                + "FROM Shoe s \n"
                + "JOIN Category c ON s.category_id = c.id \n"
                + "JOIN Staff st ON s.created_by = st.id\n"
                + "ORDER BY s.price ASC;";

        try (Connection conn = this.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Shoe s = new Shoe();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setPurpose(rs.getString("purpose"));
                s.setSize(rs.getInt("size"));
                s.setPrice(rs.getInt("price"));
                s.setStockQuantily(rs.getInt("stock_quantity"));
                s.setCreatedBy(rs.getString("creator_name"));
                s.setCategoryName(rs.getString("category_name"));
                s.setCreatorName(rs.getString("creator_name"));

                Category c = new Category();
                c.setId(rs.getInt("category_id"));
                c.setName(rs.getString("category_name"));
                s.setCategory(c);

                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShoeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
