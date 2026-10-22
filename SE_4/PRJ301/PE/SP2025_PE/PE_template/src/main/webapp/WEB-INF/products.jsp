<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:set var="title" value="Products List - FPT Store" scope="request" />
<%@include file="include/head.jsp" %>

<!-- Banner thông báo kết quả CRUD -->
<c:if test="${not empty sessionScope.toastSuccess}">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        ${sessionScope.toastSuccess}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <% session.removeAttribute("toastSuccess"); %>
</c:if>

<c:if test="${not empty sessionScope.toastError}">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        ${sessionScope.toastError}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <% session.removeAttribute("toastError"); %>
</c:if>

<div class="d-flex justify-content-between align-items-center mb-4 border-bottom pb-3">
    <div>
        <h2 class="fw-normal text-dark fs-2">Product Menu</h2>
    </div>
    <c:if test="${sessionScope.loggedInUser.role eq 'ADMIN'}">
        <a href="${pageContext.request.contextPath}/products?action=create" class="btn btn-primary px-4 py-2">
            Add new product
        </a>
    </c:if>
</div>

<!-- Bảng hiển thị sản phẩm tối giản -->
<div class="table-responsive shadow-sm rounded-3">
    <table class="table table-striped table-hover align-middle mb-0">
        <thead class="table-dark">
            <tr>
                <th class="py-3 px-4" style="width: 80px;">ID</th>
                <th>Name</th>
                <th>Category</th>
                <th class="text-end">Price</th>
                <th class="text-center" style="width: 100px;">Quantity</th>
                <th>Status</th>
                <th class="text-center" style="width: 180px;">Action</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty productList}">
                    <c:forEach var="p" items="${productList}">
                        <tr>
                            <td class="px-4 text-secondary">#${p.id}</td>
                            <td>
                                <strong class="text-dark d-block">${p.name}</strong>
                                <span class="text-muted small">${not empty p.description ? p.description : 'No description.'}</span>
                            </td>
                            <td>
                                <span class="text-secondary">${not empty p.category ? p.category.name : 'Uncategorized'}</span>
                            </td>
                            <td class="text-end fw-bold">
                                <fmt:formatNumber value="${p.price}" pattern="#,###"/> đ
                            </td>
                            <td class="text-center">${p.quantity}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${p.status && p.quantity > 0}">
                                        <span class="badge bg-success">In Stock</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-secondary">Out of Stock</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="text-center">
                                <c:choose>
                                    <c:when test="${sessionScope.loggedInUser.role eq 'ADMIN'}">
                                        <div class="d-flex justify-content-center gap-1">
                                            <a href="${pageContext.request.contextPath}/products?action=update&id=${p.id}" 
                                               class="btn btn-primary btn-sm px-3">Edit</a>
                                            <a href="${pageContext.request.contextPath}/products?action=delete&id=${p.id}" 
                                               class="btn btn-danger btn-sm px-3">Delete</a>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="descEscaped" value="${not empty p.description ? p.description : 'None'}" />
                                        <button class="btn btn-outline-secondary btn-sm" 
                                                onclick="alert('Product description: ${descEscaped}')">
                                            View Detail
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="7" class="text-center py-5 text-muted">
                            No products found.
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>

<%@include file="include/foot.jsp" %>
