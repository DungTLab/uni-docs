<%-- 
    Document   : delete
    Created on : Jun 9, 2026, 10:01:54 PM
    Author     : DungLT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<%-- Delete album  --%>
<div class="container mt-5">

    <%-- <% Artist a = (Artist) request.getAttribute("artist");%> --%>

    <h1>Delete Artist</h1>
    <form action="<%= request.getContextPath()%>/albums" method="POST">
        <input type="hidden" name="action" value="delete"/>
        <input type="hidden" name="artistId" value=""/>

        <div class="mt-5 mb-5"> 
            <h1>Do you want to delete album named " <span class="text-danger"></span> " ?</h1>
        </div>
        <div class="mt-5">
            <button type="submit" class="btn btn-danger">Yes</button>
            <a href="<%= request.getContextPath()%>/albums" type="button" class="btn btn-primary">No</a>
        </div>
    </form>
</div>   

<%@include file="../include/footer.jsp"%>
