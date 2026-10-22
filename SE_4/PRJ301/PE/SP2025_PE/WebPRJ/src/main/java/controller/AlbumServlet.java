package controller;

import dao.AlbumDAO;
import dao.ArtistDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Album;
import model.Artist;
import model.User;

/**
 *
 * @author DungLT
 */
public class AlbumServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AlbumDAO dao = new AlbumDAO();
        List<Album> list = dao.getList();
        /*
            URL patterns:
            /album (hoặc /album?view=)
            /album?view=create
            /album?view=edit&id=222
            /album?view=delete&id=222*/
        String view = request.getParameter("view");
        if (view == null || view.equals("list")) {

            request.setAttribute("album", list);
            request.getRequestDispatcher("/WEB-INF/albums/list.jsp").forward(request, response);

        } else if (view.equals("create")) {
            ArtistDAO daoArtist = new ArtistDAO();
            List<Artist> listArtist = daoArtist.getlist();

            request.setAttribute("listArtist", listArtist);
            request.getRequestDispatcher("/WEB-INF/albums/create.jsp").forward(request, response);
        } else if (view.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Album album = dao.getById(id);
            ArtistDAO daoArtist = new ArtistDAO();
            List<Artist> listArtist = daoArtist.getlist();
            request.setAttribute("album", album);
            request.setAttribute("listArtist", listArtist);
            request.getRequestDispatcher("/WEB-INF/albums/update.jsp").forward(request, response);
        } else if (view.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Album album = dao.getById(id);
            request.setAttribute("album", album);
            request.setAttribute("listAlbum", list);
            request.getRequestDispatcher("/WEB-INF/albums/delete.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //kiem tra thong tin nguoi dung
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            // SAI
            // request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");

        if (action.equals("create")) {
            String albumName = request.getParameter("albumName");
            Integer artistId = Integer.parseInt(request.getParameter("artistId"));

            AlbumDAO dao = new AlbumDAO();
            if (dao.create(artistId, albumName) != 0) {
                response.sendRedirect(request.getContextPath() + "/albums");
            } else {
                response.sendRedirect(request.getContextPath() + "/albums?view=create");
            }

        } else if (action.equals("update")) {

        } else if (action.equals("delete")) {

        }
    }

}
