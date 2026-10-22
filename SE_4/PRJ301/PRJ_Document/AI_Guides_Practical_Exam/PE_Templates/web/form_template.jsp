<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Dùng c:choose để đặt tiêu đề linh hoạt Create/Update -->
        <title>
            <c:choose>
                <c:when test="${not empty item}">Update Item</c:when>
                <c:otherwise>Create Item</c:otherwise>
            </c:choose>
        </title>
        <style>
            .form-container { width: 50%; margin: 20px auto; padding: 20px; border: 1px solid #ccc; }
            .form-group { margin-bottom: 15px; }
            label { display: block; margin-bottom: 5px; }
            input[type="text"] { width: 100%; padding: 8px; box-sizing: border-box; }
            .error { color: red; margin-bottom: 10px; }
            .btn { padding: 10px 15px; background: #4CAF50; color: white; border: none; cursor: pointer; }
        </style>
    </head>
    <body>
        <div class="form-container">
            <h2>
                <c:choose>
                    <c:when test="${not empty item}">Update Item Form</c:when>
                    <c:otherwise>Create New Item Form</c:otherwise>
                </c:choose>
            </h2>

            <!-- Vùng hiển thị lỗi (ví dụ: validation fail) -->
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>

            <form action="base" method="POST">
                <!-- Action hidden để báo cho servlet biết đây là thao tác gì -->
                <input type="hidden" name="action" value="${not empty item ? 'edit' : 'create'}" />
                
                <!-- ID bị ẩn, chỉ cần thiết khi UPDATE -->
                <c:if test="${not empty item}">
                    <input type="hidden" name="id" value="${item.id}" />
                </c:if>

                <div class="form-group">
                    <label>Name: (Đổi tên cột)</label>
                    <!-- Tự động điền dữ liệu cũ bằng thuộc tính value="${item.name}" -->
                    <input type="text" name="name" value="${item.name}" required />
                </div>
                
                <!-- Thêm các trường khác tại đây -->

                <button type="submit" class="btn">Submit</button>
                <a href="base" style="margin-left: 10px;">Cancel (Back to List)</a>
            </form>
        </div>
    </body>
</html>
