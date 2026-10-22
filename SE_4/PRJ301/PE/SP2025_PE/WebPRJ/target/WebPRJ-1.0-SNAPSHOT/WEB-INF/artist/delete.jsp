<%-- 
    Document   : delete
    Created on : May 26, 2026, 2:56:10 PM
    Author     : DungLT
--%>

<%@page import="model.Artist"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="./../include/header.jsp" %>
<%-- Delete Artist  --%>
<div class="container mt-5">

    <% Artist a = (Artist) request.getAttribute("artist");%>

    <h1>Delete Artist</h1>
    <form action="<%= request.getContextPath()%>/artist" method="POST">
        <input type="hidden" name="action" value="delete"/>
        <input type="hidden" name="artistId" value="<%= a.getId() %>"/>

        <div class="mt-5 mb-5"> 
            <h1>Are you sure to delete artist <span class="text-danger"><%= a.getName() %></span> with id <strong> <%= a.getId()%></strong>?</h1>
        </div>
        <div class="mt-5">
            <button type="submit" class="btn btn-danger">Yes</button>
            <a href="<%= request.getContextPath()%>/artist" type="button" class="btn btn-primary">No</a>
        </div>
    </form>
</div>   
<%@include file="./../include/footer.jsp" %>