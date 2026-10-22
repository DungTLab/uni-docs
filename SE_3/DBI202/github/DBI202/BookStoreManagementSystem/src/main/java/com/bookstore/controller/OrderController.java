package com.bookstore.controller;

import com.bookstore.controller.dto.CreateCustomerAndOrderRequest;
import com.bookstore.model.Order;
import com.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{orderId}")
    public Order getById(@PathVariable Integer orderId) {
        return orderService.getById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody Order order) {
        return orderService.create(order);
    }

    @PutMapping("/{orderId}")
    public Order update(@PathVariable Integer orderId, @RequestBody Order order) {
        return orderService.update(orderId, order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer orderId) {
        // sp_CancelOrder deletes ORDER_DETAIL first (stock is restored by trg_Delete_OrderDetail).
        orderService.cancelOrderUsingStoredProcedure(orderId);
    }

    @PostMapping("/{orderId}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable Integer orderId) {
        orderService.cancelOrderUsingStoredProcedure(orderId);
    }

    @PostMapping("/create-with-customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createCustomerAndOrder(@RequestBody CreateCustomerAndOrderRequest request) {
        return orderService.createCustomerAndOrderUsingStoredProcedure(
                request.getCustomerId(),
                request.getName(),
                request.getPhone(),
                request.getEmail(),
                request.getOrderId(),
                request.getOrderDate()
        );
    }
}

