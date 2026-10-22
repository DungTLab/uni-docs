<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/include/head.jsp" %>

<div class="row justify-content-center align-items-center min-vh-100">
    <div class="col-12 col-sm-8 col-md-6 col-lg-4">
        <c:if test="${not empty logoutMsg}">
            <div class="alert alert-success text-start p-3 mb-4 rounded shadow-sm" role="alert">
                ${logoutMsg}
            </div>
        </c:if>

        <div class="card shadow border-0 rounded-3">
            <div class="card-body p-4">
                <h1 class="text-center mb-4 fw-normal text-dark display-6">Login</h1>
                <form action="<%= request.getContextPath()%>/login" method="POST">
                    <input type="hidden" name="action" value="create"/>

                    <div class="mb-3">
                        <label for="user-name" class="form-label fw-medium text-dark">Username</label>
                        <input type="text" name="userName" class="form-control py-2 px-3 rounded" id="user-name" placeholder="Enter username" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label fw-medium text-dark">Password</label>
                        <input type="password" name="password" class="form-control py-2 px-3 rounded" id="password" placeholder="Enter password" required>
                    </div>
                    <div class="mt-4 mb-2">
                        <button type="submit" class="btn btn-primary w-100 py-2 fw-medium rounded">Login</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/foot.jsp" %>
