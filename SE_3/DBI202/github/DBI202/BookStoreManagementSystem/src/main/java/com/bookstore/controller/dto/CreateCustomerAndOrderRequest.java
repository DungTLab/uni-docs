package com.bookstore.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCustomerAndOrderRequest {
    private Integer customerId;
    private String name;
    private String phone;
    private String email;

    private Integer orderId;
    private LocalDate orderDate;
}

