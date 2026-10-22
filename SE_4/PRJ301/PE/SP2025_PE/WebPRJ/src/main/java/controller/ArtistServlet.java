package controller;

import dao.ArtistDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Artist;
import model.User;

/**
 *
 * @author DungLT
 */
public class ArtistServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        //CRUD
        // http://localhost:8080/WebPRJ/artist?view
        // C http://localhost:8080/WebPRJ/artist?view=create
        // R http://localhost:8080/WebPRJ/artist?view=read
        // U http://localhost:8080/WebPRJ/artist?view=update
        // D http://localhost:8080/WebPRJ/artist?view=delete
        ArtistDAO dao = new ArtistDAO();
        List<Artist> artistsList = dao.getlist();

        String view = request.getParameter("view");
        if (view == null || view.equals("list")) {
            request.setAttribute("artist", artistsList);

            request.getRequestDispatcher("/WEB-INF/artist/list.jsp").forward(request, response);
        } else if (view.equals("create")) {
            request.getRequestDispatcher("/WEB-INF/artist/create.jsp").forward(request, response);
        } else if (view.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Artist a = dao.getById(id);
            if (a != null) {
                String name = a.getName();
                request.setAttribute("artistId", id);
                request.setAttribute("artistName", name);
                request.getRequestDispatcher("/WEB-INF/artist/update.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/artist");
            }
        } else if (view.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Artist a = dao.getById(id);
            if (a != null) {
                String name = a.getName();
                request.setAttribute("artist", a);
                request.setAttribute("artistId", id);
                request.setAttribute("artistName", name);
                request.getRequestDispatcher("/WEB-INF/artist/delete.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/artist");
            }
        } else if (view.equals("read")) {
            request.getRequestDispatcher("/WEB-INF/artist/list.jsp").forward(request, response);
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
            String nameRaw = request.getParameter("artistName");
            Artist artist = new Artist(null, nameRaw);
            ArtistDAO dao = new ArtistDAO();

            if (dao.create(artist) != 0) {
                response.sendRedirect(request.getContextPath() + "/artist");
            } else {
                response.sendRedirect(request.getContextPath() + "/artist?view=create");
            }
        } else if (action.equals("update")) {
            Integer idRaw = Integer.parseInt(request.getParameter("artistId"));
            String nameRaw = request.getParameter("artistName");
            Artist artist = new Artist(idRaw, nameRaw);
            ArtistDAO dao = new ArtistDAO();
            if (dao.update(artist) != 0) {
                response.sendRedirect(request.getContextPath() + "/artist");
            } else {
                response.sendRedirect(request.getContextPath() + "/artist?view=update&id=" + idRaw);
            }
        } else if (action.equals("delete")) {
            Integer idRaw = Integer.parseInt(request.getParameter("artistId"));
            ArtistDAO dao = new ArtistDAO();
            if (dao.delete(idRaw) != 0) {
                response.sendRedirect(request.getContextPath() + "/artist");
            } else {
                response.sendRedirect(request.getContextPath() + "/artist?view=delete&id=" + idRaw);
            }
        }
    }
}
