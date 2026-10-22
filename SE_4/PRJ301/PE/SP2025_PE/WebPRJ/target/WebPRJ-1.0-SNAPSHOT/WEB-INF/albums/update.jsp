<%-- 
    Document   : update
    Created on : Jun 9, 2026, 10:02:22 PM
    Author     : DungLT
--%>

<%@page import="model.Artist"%>
<%@page import="model.Album"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<h1>Edit Album</h1>

<% List<Artist> listArtist = (List<Artist>) request.getAttribute("listArtist"); 
    Album album = (Album) request.getAttribute("album"); %>

<form action=" <%= request.getContextPath()%>/albums" method="POST">
    <input type="hidden" name="action" value="create"/>

    <div class="mb-3">
        <label for="album-name" class="form-label">Album name</label>
        <input type="text" name="albumName" class="form-control" id="album-name" value="<%= album.getTitle() %>">
    </div>
    <label for="artist-name" id="artist-name" class="form-label">Artist name</label>
    <div class="input-group">    
       <select class="form-select" id="artist-name" aria-label="Example select with button addon" name="artistId">
                <option selected value="<%= album.getArtist().getId() %>"><%=  album.getArtist().getName() %></option>
                <%
                    for (Artist tmp : listArtist) {
                %>
                <option value="<%= tmp.getId()%>"> <%= tmp.getName()%>  </option>
                <%
                    }
                %>

            </select>
    </div>
    <div class="mt-5 d-flex gap-2">
        <button type="submit" class="btn btn-primary">Save</button>
        <button type="reset" class="btn btn-danger">Clear</button>
        <a href="<%= request.getContextPath()%>/albums" type="button" class="btn btn-secondary">Back</a>
    </div>
</form>
</div>   

<%@include file="../include/footer.jsp"%>
