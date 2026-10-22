<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Delete Product - FPT Store" scope="request" />
<%@include file="include/head.jsp" %>

<div class="row justify-content-center mt-5">
    <div class="col-md-6">
        <div class="card shadow-sm border-danger">
            <div class="card-header bg-danger text-white text-center py-3">
                <h4 class="mb-0">Delete Product Confirmation</h4>
            </div>
            <div class="card-body p-4 text-center">
                
                <h5 class="text-secondary mb-4">Are you sure to delete this product?</h5>
                
                <div class="p-3 bg-light rounded border mb-4">
                    <h4 class="fw-bold text-dark">${product.name}</h4>
                    <span class="text-secondary small">Product ID: #${product.id}</span>
                </div>
                
                <p class="text-danger small mb-4">
                    <i class="bi bi-exclamation-triangle-fill"></i> Warning: This action cannot be undone and will permanently remove the item.
                </p>

                <!-- Form gửi hành động delete (POST) -->
                <form action="${pageContext.request.contextPath}/products" method="POST">
                    <input type="hidden" name="action" value="delete" />
                    <input type="hidden" name="productId" value="${product.id}" />

                    <div class="d-flex justify-content-center gap-2">
                        <a href="${pageContext.request.contextPath}/products" class="btn btn-secondary px-4">
                            No (Cancel)
                        </a>
                        <button type="submit" class="btn btn-danger px-4">
                            Yes (Confirm Delete)
                        </button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>

<%@include file="include/foot.jsp" %>
