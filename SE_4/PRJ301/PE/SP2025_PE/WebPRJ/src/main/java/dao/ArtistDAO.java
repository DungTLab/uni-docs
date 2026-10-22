package dao;

import db.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Artist;

/**
 *
 * @author DungLT
 */
public class ArtistDAO extends DBContext {

    // CRUD danh cho Artist
    public List<Artist> getlist() {
        List<Artist> list = new ArrayList<>();
        try {
            //cau lenh SQL
            String sql = "SELECT * FROM Artist;";
            //ketnoidatabase chua bi cau lenh sql
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            //thuc thi cau lenh sql
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("ArtistId");
                String name = rs.getString("Name");
                Artist artist = new Artist(id, name);
                list.add(artist);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int create(Artist artist) {
        try {

            String sql = "INSERT INTO Artist (Name) VALUES (?);";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, artist.getName());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int delete(Integer id) {
        try {

            String sql = "DELETE FROM Artist "
                    + "WHERE ArtistId = ?;";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int update(Artist artist) {
        try {

            String sql = "UPDATE Artist "
                    + "SET Name = ? "
                    + "WHERE ArtistId = ?;";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, artist.getName());
            statement.setInt(2, artist.getId());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Artist getById(Integer id) {
        try {
            //Cau lenh sql
            String sql = "SELECT * "
                    + "FROM Artist "
                    + "WHERE ArtistId = ?;";
            //tao stament ket noi comment với sql và  tránh sql injection 
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            //tao bien nhan thong tin tu db
            ResultSet rs = statement.executeQuery();

            //rs next di chuyen con tro tu tile cua hang xuong record dau tien
            if (rs.next()) {
                Artist artist = new Artist();
                //nhan data tu record 
                artist.setId(rs.getInt("ArtistId"));
                artist.setName(rs.getString("Name"));
                return artist;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
