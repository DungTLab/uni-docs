package com.bookstore.service;

import com.bookstore.model.Order;
import com.bookstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final JdbcTemplate jdbcTemplate;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> getById(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    @Transactional
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Transactional
    public Order update(Integer orderId, Order order) {
        order.setOrderId(orderId);
        return orderRepository.save(order);
    }

    @Transactional
    public void delete(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    @Transactional
    public void cancelOrderUsingStoredProcedure(int orderId) {
        jdbcTemplate.update(conn -> {
            CallableStatement cs = conn.prepareCall("{call sp_CancelOrder(?)}");
            cs.setInt(1, orderId);
            return cs;
        });
    }

    @Transactional
    public Order createCustomerAndOrderUsingStoredProcedure(
            int customerId,
            String name,
            String phone,
            String email,
            int orderId,
            LocalDate orderDate
    ) {
        jdbcTemplate.update(conn -> {
            CallableStatement cs = conn.prepareCall("{call sp_CreateCustomerAndOrder(?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, customerId);
            cs.setString(2, name);
            cs.setString(3, phone);
            cs.setString(4, email);
            cs.setInt(5, orderId);
            cs.setDate(6, Date.valueOf(orderDate));
            return cs;
        });

        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("Order not found after stored procedure call"));
    }
}

