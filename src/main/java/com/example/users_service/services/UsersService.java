package com.example.users_service.services;

import com.example.users_service.DTOs.OrderDto;
import com.example.users_service.DTOs.UserDto;
import com.example.users_service.entities.UserEntity;
import com.example.users_service.repositories.UsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    private final OrdersClientService ordersClientService;


    public UsersService(UsersRepository usersRepository, OrdersClientService ordersClientService) {
        this.usersRepository = usersRepository;
        this.ordersClientService = ordersClientService;
    }

    public UserEntity convertDtoTOEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setLogin(userDto.getLogin());
        userEntity.setPassword(userDto.getPassword());
        return userEntity;
    }

    public Integer getIdOfAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity userEntity = usersRepository.findUserEntityByLogin(currentPrincipalName).orElseThrow();
        return userEntity.getId();
    }

    public void createUser(UserDto userDto) {
        List<UserEntity> entities = usersRepository.findAll();
        for (UserEntity user : entities) {
            if (user.getLogin().equals(userDto.getLogin())) {
                throw new RuntimeException("A user with the same login already exists!!!");
            }
        }
        UserEntity userEntity = convertDtoTOEntity(userDto);
        usersRepository.save(userEntity);
    }

    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntities = usersRepository.findAll();
        return userEntities
                .stream()
                .map(user -> (new UserDto(user.getId(), user.getName(), user.getLogin(), user.getPassword())))
                .collect(Collectors.toList());
    }

    public void createOrderForUser(OrderDto orderDto) {
        orderDto.setUserId(getIdOfAuthenticatedUser());
        ordersClientService.createOrderForUser(orderDto);
    }

    public List<OrderDto> getAllOrdersForUser() {
        return ordersClientService.getAllOrdersForUser(getIdOfAuthenticatedUser());
    }

    public void updateStatusOrderForUser(OrderDto orderDto, Integer orderId) {
        ordersClientService.updateStatusOrderByUserId(orderDto, getIdOfAuthenticatedUser(), orderId);
    }
}

