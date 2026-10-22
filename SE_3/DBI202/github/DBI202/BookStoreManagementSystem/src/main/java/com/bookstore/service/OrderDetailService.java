package com.bookstore.service;

import com.bookstore.model.OrderDetail;
import com.bookstore.model.OrderDetailId;
import com.bookstore.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final JdbcTemplate jdbcTemplate;

    public List<OrderDetail> getAll() {
        return orderDetailRepository.findAll();
    }

    public Optional<OrderDetail> getById(OrderDetailId id) {
        return orderDetailRepository.findById(id);
    }

    @Transactional
    public OrderDetail createUsingStoredProcedure(OrderDetail orderDetail) {
        if (orderDetail.getId() == null) {
            throw new IllegalArgumentException("OrderDetail id must be provided");
        }

        return addBookToOrder(
                orderDetail.getId().getOrderId(),
                orderDetail.getId().getBookId(),
                orderDetail.getQuantity(),
                orderDetail.getPrice()
        );
    }

    @Transactional
    public OrderDetail updateQuantityUsingStoredProcedure(OrderDetailId id, int newQuantity) {
        jdbcTemplate.update(conn -> {
            CallableStatement cs = conn.prepareCall("{call sp_UpdateOrderDetailQuantity(?, ?, ?)}");
            cs.setInt(1, id.getOrderId());
            cs.setInt(2, id.getBookId());
            cs.setInt(3, newQuantity);
            return cs;
        });

        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("OrderDetail not found after quantity update"));
    }

    @Transactional
    public void deleteUsingStoredProcedure(OrderDetailId id) {
        jdbcTemplate.update(conn -> {
            CallableStatement cs = conn.prepareCall("{call sp_RemoveBookFromOrder(?, ?)}");
            cs.setInt(1, id.getOrderId());
            cs.setInt(2, id.getBookId());
            return cs;
        });
    }

    @Transactional
    public OrderDetail addBookToOrder(int orderId, int bookId, int quantity, BigDecimal price) {
        jdbcTemplate.update(conn -> {
            CallableStatement cs = conn.prepareCall("{call sp_AddBookToOrder(?, ?, ?, ?)}");
            cs.setInt(1, orderId);
            cs.setInt(2, bookId);
            cs.setInt(3, quantity);
            cs.setBigDecimal(4, price);
            return cs;
        });

        OrderDetailId id = new OrderDetailId(orderId, bookId);
        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("OrderDetail not found after stored procedure insert"));
    }
}

