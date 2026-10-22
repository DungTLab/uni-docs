<%-- 
    Document   : login
    Created on : Jun 16, 2026, 1:39:47 PM
    Author     : DungLT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>

<div class="container mt-5">

    <h1 class="text-center h1">Login Page</h1>
    <form action=" <%= request.getContextPath()%>/login" method="POST">
        <input type="hidden" name="action" value="create"/>
        
        <h2 class=" h2 mt-5 ">Please Sign in</h2>
        <div class="mb-3">
            <label for="user-name" class="form-label">Username</label>
            <input type="text" name="userName" class="form-control" id="user-name">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" name="password" class="form-control" id="password">
        </div>
        <div class="d-flex justify-content-center gap-3">
        <button type="submit" class="btn btn-primary">Sign In</button>
        <button type="reset" class="btn btn-danger">Clear</button>
        </div>
    </form>
</div>   

    <%@include file="../include/footer.jsp" %>