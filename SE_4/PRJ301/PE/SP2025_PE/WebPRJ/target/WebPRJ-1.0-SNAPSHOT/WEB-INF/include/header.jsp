<%-- 
    Document   : header
    Created on : May 28, 2026, 3:47:16 PM
    Author     : DungLT
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FPT Music Store</title>
        <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    </head>
    <body>
        <%-- Navbar --%>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="<%= request.getContextPath()%>">FPT Music Store</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Artist
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="<%= request.getContextPath()%>/artist">List All Artist</a></li>
                                <li><a class="dropdown-item" href="<%= request.getContextPath()%>/artist?view=create">Create New Artist</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%= request.getContextPath()%>/albums">Albums</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Track</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Genres</a>
                        </li>

                    </ul>
                </div>
                <ul class="navbar-nav gap-2">
                    <li class="nav-item">

<!--                        <%-- 1st approach: use JAVA --%>

                        <%
                            //lay tat ca cookie tu request
                            Cookie[] cookies = request.getCookies();
                            String theme = "dark";
                            //tim cookie "Theme" trong mang

                            if (cookies != null) {
                                for (Cookie c : cookies) {
                                    if (c.getName().equals("theme")) {
                                        theme = c.getValue();
                                    }
                                }
                            }

                        %>
                        <a class="nav-link" href="#" >Theme: <%= theme%> </a>-->
                        
                        
                        <%-- 2st approach: use EL --%>
                        <a class="nav-link" href="#" >Theme: ${empty cookie.theme.value ? 'dark' : cookie.theme.value} </a>


                    </li>

                    <% User loggedInUser = (User) session.getAttribute("loggedInUser"); %>
                    <% if (loggedInUser == null) {%>
                    <li class="nav-item d-flex gap-2">
                        <a class="btn btn-primary d-flex " href="<%= request.getContextPath()%>/login">Login</a>
                        <a class="btn btn-primary d-flex " href="<%= request.getContextPath()%>/login">Register</a>
                    </li> 
                    <% } else {%>

                    <li class="nav-item d-flex gap-2">
                        <div class="mt-2">${loggedInUser.displayInfo}</div>
                        <a class="nav-link btn" href="<%= request.getContextPath()%>/logout">Logout</a>
                    </li> 
                    <% }%>

                </ul>



            </div>
        </nav> 
        <main class="container mt-5">