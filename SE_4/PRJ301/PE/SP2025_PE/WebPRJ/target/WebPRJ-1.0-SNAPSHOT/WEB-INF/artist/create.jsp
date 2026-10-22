<%-- 
    Document   : create
    Created on : May 26, 2026, 2:22:20 PM
    Author     : DungLT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Them Header --%>
<%@include file="./../include/header.jsp" %>
<%-- Create New Artist  --%>
<div class="container mt-5">

    <h1>Add new Artist</h1>
    <form action=" <%= request.getContextPath()%>/artist" method="POST">
        <input type="hidden" name="action" value="create"/>

        <div class="mb-3">
            <label for="artist-name" class="form-label">Name</label>
            <input type="text" name="artistName" class="form-control" id="artist-name">
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
        <button type="reset" class="btn btn-danger">Clear</button>
        <a href="<%= request.getContextPath()%>/artist" type="button" class="btn btn-secondary">Back</a>
    </form>
</div>   

<%@include file="./../include/footer.jsp" %>