<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Update Product #${product.id} - FPT Store" scope="request" />
<%@include file="include/head.jsp" %>

<div class="row justify-content-center">
    <div class="col-md-8">
        
        <div class="mb-3">
            <a href="${pageContext.request.contextPath}/products" class="btn btn-outline-secondary btn-sm">
                &larr; Back to List
            </a>
        </div>

        <c:if test="${not empty generalError}">
            <div class="alert alert-danger" role="alert">
                ${generalError}
            </div>
        </c:if>

        <div class="card shadow-sm">
            <div class="card-header bg-dark text-white">
                <h4 class="mb-0">Update Product #${product.id}</h4>
            </div>
            <div class="card-body p-4">
                <form action="${pageContext.request.contextPath}/products" method="POST" autocomplete="off">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="productId" value="${product.id}">

                    <div class="mb-3">
                        <label for="name" class="form-label fw-bold">Product Name <span class="text-danger">*</span></label>
                        <input type="text" class="form-control ${not empty errName ? 'is-invalid' : ''}" 
                               id="name" name="name" placeholder="Enter product name" 
                               value="${product.name}" required>
                        <c:if test="${not empty errName}">
                            <div class="invalid-feedback">${errName}</div>
                        </c:if>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="categoryId" class="form-label fw-bold">Category <span class="text-danger">*</span></label>
                            <select class="form-select ${not empty errCategory ? 'is-invalid' : ''}" 
                                    id="categoryId" name="categoryId" required>
                                <option value="">-- Select Category --</option>
                                <c:forEach var="cat" items="${categoryList}">
                                    <option value="${cat.id}" ${product.categoryId == cat.id ? 'selected' : ''}>
                                        ${cat.name}
                                    </option>
                                </c:forEach>
                            </select>
                            <c:if test="${not empty errCategory}">
                                <div class="invalid-feedback">${errCategory}</div>
                            </c:if>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="price" class="form-label fw-bold">Price (VNĐ) <span class="text-danger">*</span></label>
                            <input type="number" step="1000" class="form-control ${not empty errPrice ? 'is-invalid' : ''}" 
                                   id="price" name="price" placeholder="Enter price" 
                                   value="${product.price}" required>
                            <c:if test="${not empty errPrice}">
                                <div class="invalid-feedback">${errPrice}</div>
                            </c:if>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="quantity" class="form-label fw-bold">Stock Quantity <span class="text-danger">*</span></label>
                            <input type="number" class="form-control ${not empty errQuantity ? 'is-invalid' : ''}" 
                                   id="quantity" name="quantity" placeholder="Enter quantity" 
                                   value="${product.quantity}" required>
                            <c:if test="${not empty errQuantity}">
                                <div class="invalid-feedback">${errQuantity}</div>
                            </c:if>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="image" class="form-label fw-bold">Image URL</label>
                            <input type="url" class="form-control" id="image" name="image" 
                                   placeholder="Image URL" value="${product.image}">
                        </div>
                    </div>

                    <div class="mb-3 form-check">
                        <input class="form-check-input" type="checkbox" id="status" name="status" 
                               ${product.status ? 'checked' : ''}>
                        <label class="form-check-label text-dark" for="status">
                            Available for Sale
                        </label>
                    </div>

                    <div class="mb-3">
                        <label for="description" class="form-label fw-bold">Description</label>
                        <textarea class="form-control" id="description" name="description" 
                                  rows="3" placeholder="Enter product details...">${product.description}</textarea>
                    </div>

                    <div class="d-flex justify-content-end gap-2 mt-4 border-top pt-3">
                        <a href="${pageContext.request.contextPath}/products" class="btn btn-secondary px-4">
                            Cancel
                        </a>
                        <button type="submit" class="btn btn-primary px-4">
                            Save Changes
                        </button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>

<%@include file="include/foot.jsp" %>
