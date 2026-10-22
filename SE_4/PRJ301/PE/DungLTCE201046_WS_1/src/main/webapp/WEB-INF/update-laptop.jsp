<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="include/head.jsp" %>

<div class="row justify-content-center">
    <div class="col-md-8">
        <div class="card border-0 shadow-sm p-4 mt-4">
            <h2 class="fw-bold mb-4 text-dark">Update Laptop #${laptop.id}</h2>
            <div class="card-body p-0">
                
                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        ${error}
                    </div>
                </c:if>
                
                <form action="${pageContext.request.contextPath}/laptops" method="POST" id="update-laptop-form">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="${laptop.id}">

                    <div class="mb-3">
                        <label for="laptop-brand" class="form-label fw-semibold">Brand</label>
                        <input type="text" class="form-control" id="laptop-brand" name="brand" 
                               value="${laptop.brand}" required>
                    </div>
                    
                    <div class="mb-3">
                        <label for="laptop-model" class="form-label fw-semibold">Model</label>
                        <input type="text" class="form-control" id="laptop-model" name="model" 
                               value="${laptop.model}" required>
                    </div>
                    
                    <div class="mb-3">
                        <label for="laptop-release-year" class="form-label fw-semibold">Release Year</label>
                        <input type="number" class="form-control" id="laptop-release-year" name="release-year" 
                               value="${laptop.releaseYear}" min="1000" max="<%= java.time.Year.now().getValue() %>" required>
                    </div>
                    
                    <div class="mb-3">
                        <label for="laptop-price" class="form-label fw-semibold">Price ($)</label>
                        <input type="number" class="form-control" id="laptop-price" name="price" 
                               value="${laptop.price}" step="0.01" min="0.01" required>
                    </div>
                    
                    <div class="mb-3">
                        <label for="laptop-quantity" class="form-label fw-semibold">Quantity</label>
                        <input type="number" class="form-control" id="laptop-quantity" name="quantity" 
                               value="${laptop.quantity}" min="0" step="1" required>
                    </div>

                    <div class="d-flex gap-2 justify-content-start mt-4">
                        <button type="submit" class="btn btn-primary px-4">Save Changes</button>
                        <a href="${pageContext.request.contextPath}/laptops" class="btn btn-secondary px-4">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    document.getElementById('update-laptop-form').addEventListener('submit', function (e) {
        let brand = document.getElementById('laptop-brand').value.trim();
        let model = document.getElementById('laptop-model').value.trim();
        let releaseYearInput = document.getElementById('laptop-release-year');
        let priceInput = document.getElementById('laptop-price');
        let quantityInput = document.getElementById('laptop-quantity');

        let releaseYear = parseInt(releaseYearInput.value, 10);
        let price = parseFloat(priceInput.value);
        let quantity = parseInt(quantityInput.value, 10);

        let currentYear = new Date().getFullYear();

        if (!brand || !model) {
            alert('Brand and Model cannot be empty.');
            e.preventDefault();
            return;
        }
        if (isNaN(releaseYear) || releaseYear < 1000 || releaseYear > currentYear) {
            alert('Release Year must be a valid number between 1000 and ' + currentYear + '.');
            e.preventDefault();
            return;
        }
        if (isNaN(price) || price <= 0) {
            alert('Price must be a valid positive number greater than 0.');
            e.preventDefault();
            return;
        }
        if (isNaN(quantity) || quantity < 0) {
            alert('Quantity must be a valid non-negative integer.');
            e.preventDefault();
            return;
        }
    });
</script>
<%@ include file="include/foot.jsp" %>
