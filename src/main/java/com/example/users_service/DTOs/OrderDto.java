package com.example.users_service.DTOs;

import com.example.users_service.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private int id;
    private String name;
    private OrderStatus status;
    private int userId;
}

