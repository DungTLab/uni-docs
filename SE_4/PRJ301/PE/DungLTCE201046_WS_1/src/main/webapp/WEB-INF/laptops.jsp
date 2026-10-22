<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ include file="include/head.jsp" %>

<div class="d-flex justify-content-between align-items-center mb-4 mt-3">
    <h1 class="fw-bold text-dark mb-0">Our Laptops</h1>
    <a href="${pageContext.request.contextPath}/laptops?action=create" class="btn btn-primary px-4 py-2">Add new laptop</a>
</div>

<div class="container p-0">
    <table id="laptop-table" class="table table-bordered table-striped table-hover align-middle">
        <thead class="table-dark">
            <tr class="laptop-row">
                <th style="width: 80px;">ID</th>
                <th>Brand</th>
                <th>Model</th>
                <th>Release Year</th>
                <th class="text-end">Price</th>
                <th class="text-center" style="width: 100px;">Quantity</th>
                <th class="text-center" style="width: 180px;">Action</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty list}">
                    <c:forEach var="tmp" items="${list}">
                        <tr class="laptop-row ${tmp.quantity < 5 ? 'table-warning text-danger fw-semibold' : ''}">
                            <td>${tmp.id}</td>
                            <td>${tmp.brand}</td>
                            <td>${tmp.model}</td>
                            <td>${tmp.releaseYear}</td>
                            <td class="text-end">
                                <fmt:formatNumber value="${tmp.price}" type="currency" currencySymbol="$" />
                            </td>
                            <td class="text-center">${tmp.quantity}</td>
                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/laptops?action=update&id=${tmp.id}" class="btn btn-primary btn-sm me-1">Edit</a>
                                <a href="${pageContext.request.contextPath}/laptops?action=delete&id=${tmp.id}" class="btn btn-danger btn-sm">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="7" class="text-center py-4 text-muted">
                            No laptops found in the database.
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>

<%@ include file="include/foot.jsp" %>
