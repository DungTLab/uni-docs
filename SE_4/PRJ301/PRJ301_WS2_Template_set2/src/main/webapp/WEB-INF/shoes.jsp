<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/include/head.jsp" %>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4 border-bottom pb-3">
        <h2 class="fw-normal text-dark fs-2">Shoe Menu</h2>
        <div class="d-flex align-items-center gap-2">
            <span>Welcome, <strong class="text-dark">${loggedInStaff.fullName}</strong></span>
            <a class="btn btn-outline-danger btn-sm px-3 ms-2" href="<%= request.getContextPath()%>/logout">Logout</a>                    
        </div>
    </div>

    <!-- Calculate Average Price -->
    <c:set var="totalPrice" value="0" />
    <c:set var="shoeCount" value="0" />
    <c:forEach var="shoe" items="${shoes}">
        <c:set var="totalPrice" value="${totalPrice + shoe.price}" />
        <c:set var="shoeCount" value="${shoeCount + 1}" />
    </c:forEach>
    <c:set var="avgPrice" value="${shoeCount > 0 ? totalPrice / shoeCount : 0}" />

    <div class="table-responsive shadow-sm rounded-3">
        <table class="table table-striped table-hover align-middle mb-0">
            <thead class="table-light">
                <tr>
                    <th class="py-3 px-4 fw-semibold text-secondary">Name</th>
                    <th class="py-3 fw-semibold text-secondary">Category</th>
                    <th class="py-3 fw-semibold text-secondary">Purpose</th>
                    <th class="py-3 fw-semibold text-secondary">Size</th>
                    <th class="py-3 text-end fw-semibold text-secondary pe-4">Price</th>
                    <th class="py-3 fw-semibold text-secondary">Created By</th>
                    <th class="py-3 text-center fw-semibold text-secondary">Stock</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="shoe" items="${shoes}">
                    <tr>
                        <td class="py-3 px-4 fw-medium text-dark">${shoe.name}</td>
                        <td class="py-3 text-secondary">${shoe.category.name}</td>
                        <td class="py-3">
                            <c:choose>
                                <c:when test="${shoe.purpose == 'Casual'}">
                                    <span class="fw-bold text-dark">Casual</span>
                                </c:when>
                                <c:when test="${shoe.purpose == 'Running'}">
                                    <span class="fw-bold text-primary">Running</span>
                                </c:when>
                                <c:when test="${shoe.purpose == 'Soccer'}">
                                    <span class="fw-bold text-success">Soccer</span>
                                </c:when>
                                <c:when test="${shoe.purpose == 'Basketball' or shoe.purpose == 'BasketBall'}">
                                    <span class="fw-bold" style="color: #fd7e14;">Basketball</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="fw-bold text-secondary">${shoe.purpose}</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="py-3">${shoe.size}</td>
                        <td class="py-3 text-end fw-semibold text-dark pe-4">
                            <fmt:formatNumber value="${shoe.price}" pattern="#,###" />
                        </td>
                        <td class="py-3 text-secondary">${shoe.createdBy}</td>
                        <td class="py-3 text-center">
                            <c:choose>
                                <c:when test="${shoe.stockQuantily > 0}">
                                    <span class="badge rounded-pill bg-success px-3 py-2 ">In Stock</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge rounded-pill bg-secondary px-3 py-2 ">Out of Stock</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                <!-- Average Price Row -->
                <tr class="fw-bold table-light text-primary border-top border-2">
                    <td colspan="4" class="py-3 px-4">Average Price</td>
                    <td class="py-3 text-end pe-4">
                        <fmt:formatNumber value="${avgPrice}" pattern="#,###" />
                    </td>
                    <td colspan="2" class="py-3"></td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<%@include file="/WEB-INF/include/foot.jsp" %>
