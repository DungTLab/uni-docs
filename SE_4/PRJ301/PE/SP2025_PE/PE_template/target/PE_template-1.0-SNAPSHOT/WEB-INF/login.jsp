<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Login - FPT Store" scope="request" />
<%@include file="include/head.jsp" %>

<div class="row justify-content-center mt-5">
    <div class="col-md-4">
        
        <!-- Cảnh báo thành công đăng ký -->
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${successMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

        <!-- Cảnh báo lỗi đăng nhập -->
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${errorMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

        <div class="card shadow-sm">
            <div class="card-header bg-dark text-white text-center py-3">
                <h4 class="mb-0">System Login</h4>
            </div>
            <div class="card-body p-4">
                <form action="${pageContext.request.contextPath}/login" method="POST" autocomplete="off">
                    
                    <div class="mb-3">
                        <label for="userName" class="form-label text-dark fw-medium">Username</label>
                        <input type="text" class="form-control" id="userName" name="userName" 
                               placeholder="Enter username" 
                               value="${not empty usernameVal ? usernameVal : usernameCookie}" required>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label text-dark fw-medium">Password</label>
                        <input type="password" class="form-control" id="password" name="password" 
                               placeholder="Enter password" required>
                    </div>

                    <div class="mb-4 form-check">
                        <input class="form-check-input" type="checkbox" id="rememberMe" name="rememberMe" ${rememberMeChecked}>
                        <label class="form-check-label text-secondary" for="rememberMe">
                            Remember Username
                        </label>
                    </div>

                    <button type="submit" class="btn btn-primary w-100 py-2 fw-medium">
                        Login
                    </button>
                </form>

                <div class="text-center mt-3">
                    <span class="text-muted small">Don't have an account? </span>
                    <a href="${pageContext.request.contextPath}/register" class="small text-decoration-none">Register here</a>
                </div>

            </div>
        </div>
    </div>
</div>

<%@include file="include/foot.jsp" %>
