<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Tự động chuyển hướng trang chủ sang trang danh sách sản phẩm
    response.sendRedirect(request.getContextPath() + "/products");
%>
