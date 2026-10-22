package com.bookstore.controller;

import com.bookstore.controller.dto.OrderDetailQuantityUpdateRequest;
import com.bookstore.model.OrderDetail;
import com.bookstore.model.OrderDetailId;
import com.bookstore.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order-details")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @GetMapping
    public List<OrderDetail> getAll() {
        return orderDetailService.getAll();
    }

    @GetMapping("/{orderId}/{bookId}")
    public OrderDetail getById(@PathVariable Integer orderId, @PathVariable Integer bookId) {
        OrderDetailId id = new OrderDetailId(orderId, bookId);
        return orderDetailService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "OrderDetail not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDetail addBookToOrder(@RequestBody OrderDetail orderDetail) {
        return orderDetailService.createUsingStoredProcedure(orderDetail);
    }

    @PutMapping("/{orderId}/{bookId}")
    public OrderDetail updateQuantity(
            @PathVariable Integer orderId,
            @PathVariable Integer bookId,
            @RequestBody OrderDetailQuantityUpdateRequest request
    ) {
        OrderDetailId id = new OrderDetailId(orderId, bookId);
        return orderDetailService.updateQuantityUsingStoredProcedure(id, request.getQuantity());
    }

    @DeleteMapping("/{orderId}/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBookFromOrder(@PathVariable Integer orderId, @PathVariable Integer bookId) {
        orderDetailService.deleteUsingStoredProcedure(new OrderDetailId(orderId, bookId));
    }
}

