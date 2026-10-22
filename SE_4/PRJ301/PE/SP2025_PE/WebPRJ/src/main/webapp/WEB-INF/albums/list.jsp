<%-- 
    Document   : list
    Created on : Jun 9, 2026, 2:37:57 PM
    Author     : DungLT
--%>

<%@page import="java.util.List"%>
<%@page import="model.Album"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>

<%-- List Album --%>

<div class="container mt-5">

    <h1 class="mb-0">Album list</h1>

    <div class="d-flex justify-content-end mb-3">

        <a href="<%= request.getContextPath()%>/albums?view=create" class="btn btn-success">Add</a>
    </div>
    <table  class="table table-striped table-hover">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Artist</th>
            <th>Action</th>
        </tr>
        <%-- comment JSP --%>
        <%
            List<Album> AlbumsList = (List<Album>) request.getAttribute("album");

            if (AlbumsList != null) {
                for (Album tmp : AlbumsList) {
        %>

        <tr>
            <td><%= tmp.getAlbumId()%></td>
            <td> <%= tmp.getTitle()%> </td>
            <td> <%= tmp.getArtist().getName()%> </td>
            <td class="d-flex gap-2">
                <a href="<%= request.getContextPath()%>/albums?view=update&id=<%= tmp.getAlbumId()%>" class="btn btn-primary btn-sm">Edit</a> |
                <a href="<%= request.getContextPath()%>/albums?view=delete&id=<%= tmp.getAlbumId()%>" class="btn btn-danger btn-sm">Delete</a>
            </td>
        </tr>
        <%
                }
            }
        %>

    </table>
</div>

<%@include file="../include/footer.jsp" %> <%-- Đặt footer ở dưới cùng hẳn --%>
