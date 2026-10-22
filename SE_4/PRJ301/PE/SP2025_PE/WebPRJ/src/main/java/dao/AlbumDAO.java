/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Album;
import model.Artist;

/**
 *
 * @author DungLT
 */
public class AlbumDAO extends DBContext {

    public List<Album> getList() {
        List<Album> albums = new ArrayList<>();

        String sql = "SELECT al.AlbumId, al.Title, al.ArtistId, a.[Name] "
                + "FROM Album al "
                + "JOIN Artist a ON al.ArtistId = a.ArtistId "
                + "ORDER BY al.AlbumId;";

        try (PreparedStatement statement = this.getConnection().prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Integer albumId = rs.getInt("AlbumId");
                Integer artistId = rs.getInt("ArtistId");
                String title = rs.getString("Title");
                String name = rs.getString("Name");

                Artist artist = new Artist(artistId, name);

                Album album = new Album(albumId, artist, title);

                albums.add(album);
            }
            return albums;
        } catch (SQLException ex) {
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return albums;
    }
    
    public int create(Integer artistId, String albumName) {
        try {
                    
            String sql = "INSERT INTO Album (Title, ArtistId) VALUES (?, ?);";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, albumName);
            statement.setInt(2, artistId);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Album getById(Integer id) {
        Album a = new Album();
        try {
            String sql = "SELECT al.AlbumId, al.Title, al.ArtistId, a.[Name]\n"
                    + "FROM Album al\n"
                    + "JOIN Artist a ON al.ArtistId = a.ArtistId\n"
                    + "WHERE al.AlbumId = ?";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                a.setAlbumId(rs.getInt("AlbumId"));
                a.setTitle(rs.getString("Title"));
                Artist artist = new Artist(rs.getInt("ArtistId"), rs.getString("Name"));
                a.setArtist(artist);
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
