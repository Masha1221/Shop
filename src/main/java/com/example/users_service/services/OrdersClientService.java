package com.example.users_service.services;

import com.example.users_service.DTOs.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "orders-service", url = "http://localhost:8081")
public interface OrdersClientService {
    @PostMapping(value = "/users/orders/create")
    void createOrderForUser(@RequestBody OrderDto orderDto);

    @GetMapping(value = "/users/{id}/orders")
    List<OrderDto> getAllOrdersForUser(@PathVariable(value = "id") Integer userId);

    @PutMapping(value = "/users/{orderId}/orders/{userId}")
    void updateStatusOrderByUserId(@RequestBody OrderDto orderDto, @PathVariable Integer orderId,
                                   @PathVariable Integer userId);
}

