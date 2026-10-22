<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Đổi tiêu đề -->
        <title>List Template</title>
        <style>
            table { width: 80%; border-collapse: collapse; margin: 20px auto; }
            th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
            th { background-color: #f2f2f2; }
            .btn { padding: 5px 10px; text-decoration: none; border-radius: 3px; }
            .btn-create { background: #4CAF50; color: white; display: inline-block; margin: 20px 10%; }
            .btn-edit { background: #008CBA; color: white; }
            .btn-delete { background: #f44336; color: white; }
        </style>
    </head>
    <body>
        <h2 style="text-align: center;">Item List</h2>

        <!-- Nút thêm mới, điều hướng tới Controller với action=create -->
        <a href="base?action=create" class="btn btn-create">Create New Item</a>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Actions</th> <!-- ĐỔI TIÊU ĐỀ CỘT -->
                </tr>
            </thead>
            <tbody>
                <!-- Vòng lặp hiển thị dữ liệu lấy từ request attribute "dataList" -->
                <c:forEach items="${dataList}" var="item">
                    <tr>
                        <!-- Dùng EL để in thuộc tính của item (phải có getter trong model) -->
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        
                        <!-- Cột thao tác -->
                        <td>
                            <!-- Sửa điều hướng: gọi servlet kèm ID và action -->
                            <a href="base?action=edit&id=${item.id}" class="btn btn-edit">Edit</a>
                            
                            <!-- Cảnh báo trước khi xóa -->
                            <a href="base?action=delete&id=${item.id}" 
                               class="btn btn-delete" 
                               onclick="return confirm('Are you sure you want to delete ${item.name}?');">
                               Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
