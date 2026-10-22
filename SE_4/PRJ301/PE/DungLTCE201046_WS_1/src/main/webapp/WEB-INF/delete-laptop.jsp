<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="include/head.jsp" %>

<div class="row justify-content-center">
    <div class="col-md-6">
        <div class="card border-0 shadow-sm p-4 mt-4">
            <h2 class="text-center fw-bold mb-3 text-dark">Confirm Delete</h2>
            <p class="text-center text-muted mb-4">Are you sure you want to delete this laptop?</p>
            <div class="table-responsive">
                <table class="table table-bordered mb-4">
                    <tbody>
                        <tr>
                            <td class="fw-bold w-40 text-dark bg-light py-2 px-3">ID</td>
                            <td class="py-2 px-3 text-dark">#${laptop.id}</td>
                        </tr>
                        <tr>
                            <td class="fw-bold text-dark bg-light py-2 px-3">Brand</td>
                            <td class="py-2 px-3 text-dark">${laptop.brand}</td>
                        </tr>
                        <tr>
                            <td class="fw-bold text-dark bg-light py-2 px-3">Model</td>
                            <td class="py-2 px-3 text-dark">${laptop.model}</td>
                        </tr>
                        <tr>
                            <td class="fw-bold text-dark bg-light py-2 px-3">Price</td>
                            <td class="py-2 px-3 text-dark">$${laptop.price}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <form action="${pageContext.request.contextPath}/laptops" method="POST" class="d-flex justify-content-center gap-3">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="${laptop.id}">
                <button type="submit" class="btn btn-danger px-4 py-2">Yes, Delete</button>
                <a href="${pageContext.request.contextPath}/laptops" class="btn btn-secondary px-4 py-2">Cancel</a>
            </form>
        </div>
    </div>
</div>
<%@ include file="include/foot.jsp" %>