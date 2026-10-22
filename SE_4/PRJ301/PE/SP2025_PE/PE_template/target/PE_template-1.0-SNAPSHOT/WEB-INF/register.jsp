<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Register - FPT Store" scope="request" />
<%@include file="include/head.jsp" %>

<div class="row justify-content-center mt-5">
    <div class="col-md-4">

        <!-- Cảnh báo lỗi đăng ký -->
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${errorMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

        <div class="card shadow-sm">
            <div class="card-header bg-dark text-white text-center py-3">
                <h4 class="mb-0">Create Account</h4>
            </div>
            <div class="card-body p-4">
                <form action="${pageContext.request.contextPath}/register" method="POST" autocomplete="off">
                    
                    <div class="mb-3">
                        <label for="userName" class="form-label text-dark fw-medium">Username</label>
                        <input type="text" class="form-control" id="userName" name="userName" 
                               placeholder="Enter username" value="${usernameVal}" required>
                    </div>

                    <div class="mb-3">
                        <label for="displayName" class="form-label text-dark fw-medium">Display Name</label>
                        <input type="text" class="form-control" id="displayName" name="displayName" 
                               placeholder="Enter full name" value="${displayNameVal}" required>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label text-dark fw-medium">Password</label>
                        <input type="password" class="form-control" id="password" name="password" 
                               placeholder="Enter password" required>
                    </div>

                    <div class="mb-4">
                        <label for="confirmPassword" class="form-label text-dark fw-medium">Confirm Password</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" 
                               placeholder="Confirm password" required>
                    </div>

                    <button type="submit" class="btn btn-primary w-100 py-2 fw-medium">
                        Register
                    </button>
                </form>

                <div class="text-center mt-3">
                    <span class="text-muted small">Already have an account? </span>
                    <a href="${pageContext.request.contextPath}/login" class="small text-decoration-none">Login here</a>
                </div>

            </div>
        </div>
    </div>
</div>

<%@include file="include/foot.jsp" %>
