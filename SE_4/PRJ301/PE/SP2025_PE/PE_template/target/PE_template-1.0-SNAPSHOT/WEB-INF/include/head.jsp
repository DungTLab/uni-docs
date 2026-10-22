<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.User" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title != null ? title : "FPT Store"}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <!-- Navbar tối giản chuẩn phong cách Workshop -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/products">FPT Store</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/products?action=list">Products</a>
                    </li>
                </ul>
                <div class="d-flex align-items-center gap-2 text-white">
                    <%-- Hiển thị thông tin đăng nhập tối giản chuẩn WS 2 --%>
                    <% User loggedIn = (User) session.getAttribute("loggedInUser"); %>
                    <% if (loggedIn != null) { %>
                        <span>Welcome, <strong><%= loggedIn.getDisplayName() != null ? loggedIn.getDisplayName() : loggedIn.getUsername() %></strong></span>
                        <a class="btn btn-outline-danger btn-sm px-3 ms-2" href="${pageContext.request.contextPath}/logout">Logout</a>
                    <% } else { %>
                        <a class="btn btn-outline-light btn-sm" href="${pageContext.request.contextPath}/login">Login</a>
                        <a class="btn btn-primary btn-sm ms-2" href="${pageContext.request.contextPath}/register">Register</a>
                    <% } %>
                </div>
            </div>
        </div>
    </nav>
    <div class="container">
