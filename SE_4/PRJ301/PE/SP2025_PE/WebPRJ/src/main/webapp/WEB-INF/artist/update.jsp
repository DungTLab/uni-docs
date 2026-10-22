<%-- 
    Document   : update
    Created on : May 26, 2026, 2:53:16 PM
    Author     : DungLT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="./../include/header.jsp" %>
<%-- Create New Artist  --%>
<div class="container mt-5">

    <h1>UpdateArtist</h1>
    <form action="<%= request.getContextPath()%>/artist" method="POST">
        <input type="hidden" name="action" value="update"/>
        
        <div class="mb-3">
            <label for="artist-id" class="form-label">Id</label>
            <input name="artistId" type="text" class="form-control" id="artist-id" readonly="" value="${artistId}">
        </div>
        <div class="mb-3">
            <label for="artist-name" class="form-label">Name</label>
            <input name="artistName" type="text" class="form-control" id="artist-name" value="${artistName}">
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
        <button type="reset" class="btn btn-danger">Clear</button>
        <a href="<%= request.getContextPath()%>/artist" type="button" class="btn btn-secondary">Back</a>
    </form>
</div>   
<%@include file="./../include/footer.jsp" %>