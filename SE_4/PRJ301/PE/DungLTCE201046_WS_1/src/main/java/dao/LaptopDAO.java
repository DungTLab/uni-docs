package dao;

import db.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Laptop;

public class LaptopDAO extends DBContext {

    // TODO: Implement findAll()
    public List<Laptop> findAll() {
        try {
            List<Laptop> list = new ArrayList<>();
            String sql = "SELECT * FROM Laptop ORDER BY price ASC;";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                Integer releaseYear = rs.getInt("release_year");
                double price = rs.getDouble("price");
                Integer quantity = rs.getInt("quantity");
                Laptop laptop = new Laptop(id, brand, model, releaseYear, price, quantity);
                list.add(laptop);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // TODO: Implement insert(Laptop)
    public int insert(Laptop laptop) {
        try {

            String sql = "INSERT INTO Laptop (brand, model, release_year, price, quantity) VALUES ( ?, ?, ?, ?, ?);";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, laptop.getBrand());
            statement.setString(2, laptop.getModel());
            statement.setInt(3, laptop.getReleaseYear());
            statement.setDouble(4, laptop.getPrice());
            statement.setInt(5, laptop.getQuantity());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Laptop findById(int id) {
        try {
            String sql = "SELECT * FROM Laptop WHERE id = ?;";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                Integer releaseYear = rs.getInt("release_year");
                double price = rs.getDouble("price");
                Integer quantity = rs.getInt("quantity");
                return new Laptop(id, brand, model, releaseYear, price, quantity);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void delete(int id) {
        try {
            String sql = "DELETE FROM Laptop WHERE id = ?;";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int update(Laptop laptop) {
        try {
            String sql = "UPDATE Laptop SET brand = ?, model = ?, release_year = ?, price = ?, quantity = ? WHERE id = ?;";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, laptop.getBrand());
            statement.setString(2, laptop.getModel());
            statement.setInt(3, laptop.getReleaseYear());
            statement.setDouble(4, laptop.getPrice());
            statement.setInt(5, laptop.getQuantity());
            statement.setInt(6, laptop.getId());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
