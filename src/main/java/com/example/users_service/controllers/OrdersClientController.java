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

    @GetMapping("/users/orders")
    public List<OrderDto> getAllOrdersByUserId() {
        return usersService.getAllOrdersForUser();
    }

    @PutMapping("/users/orders/{orderId}")
    public void updateStatusOrderByUserId(@RequestBody OrderDto orderDto,
                                          @PathVariable Integer orderId) {
       usersService.updateStatusOrderForUser(orderDto,orderId);
        log.info("New order status {} ", orderDto.getStatus());
    }
}
