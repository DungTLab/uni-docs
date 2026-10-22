<%-- 
    Document   : list
    Created on : May 21, 2026, 4:05:17 PM
    Author     : DungLT
--%>

<%@page import="model.Artist"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="./../include/header.jsp" %>
<%-- List Artist --%>

<div class="container mt-5">

    <h1 class="mb-0">Artist list</h1>

    <div class="d-flex justify-content-end mb-3">

        <a href="<%= request.getContextPath()%>/artist?view=create" class="btn btn-success">Add</a>
    </div>
    <table  class="table table-striped table-hover">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Action</th>
        </tr>
        <%-- 1st comment JSP 
        <%
            List<Artist> artistsList = (List<Artist>) request.getAttribute("artist");

            if (artistsList != null) {
                for (Artist tmp : artistsList) {
        %>

        <tr>
            <td><%= tmp.getId()%></td>
            <td> <%= tmp.getName()%> </td>
            <td class="d-flex gap-2">
                <a href="<%= request.getContextPath()%>/artist?view=update&id=<%= tmp.getId()%>" class="btn btn-primary btn-sm">Edit</a> |
                <a href="<%= request.getContextPath()%>/artist?view=delete&id=<%= tmp.getId()%>" class="btn btn-danger btn-sm">Delete</a>
            </td>
        </tr>
        <%
                }
            }
        %>
        --%>

        <c:forEach items="${requestScope.artist}" var="artist"> 
            <tr>
                <td>${artist.id}</td>
                <td>${artist.name}</td>
                <td class="d-flex gap-2">
                    <c:url value="/artist" var="editUrl">
                        <c:param name="view" value="edit" />
                        <c:param name="id" value="${artist.id}" />
                    </c:url>

                    <c:url value="/artist" var="deleteUrl">
                        <c:param name="view" value="delete" />
                        <c:param name="id" value="${artist.id}" />
                    </c:url>

                    <a href="${editUrl}" class="btn btn-primary btn-sm">Edit</a>

                    <a href="${deleteUrl}" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
        </c:forEach>

    </table>
</div>
<%@include file="./../include/footer.jsp" %>                

