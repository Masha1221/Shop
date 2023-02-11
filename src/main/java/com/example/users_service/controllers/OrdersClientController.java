package com.example.users_service.controllers;

import com.example.users_service.DTOs.OrderDto;
import com.example.users_service.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
public class OrdersClientController {

    private final UsersService usersService;

    @PostMapping("/users/orders")
    public void createOrderForUser(@RequestBody OrderDto orderDto) {
        usersService.createOrderForUser(orderDto);
        log.info("Order {} for user with ID - {} is created.", orderDto, orderDto.getUserId());
    }

    @GetMapping("/users/{id}/orders")
    public List<OrderDto> getAllOrdersByUserId(@PathVariable(value = "id") Integer userId) {
        return usersService.getAllOrdersByUserId(userId);
    }

    @PutMapping("/users/{orderId}/orders/{userId}")
    public void updateStatusOrderByUserId(@RequestBody OrderDto orderDto,
                                          @PathVariable Integer orderId,
                                          @PathVariable Integer userId) {
        usersService.updateStatusOrderByUserId(orderDto, orderId, userId);
        log.info("New order status {} ", orderDto.getStatus());
    }
}
