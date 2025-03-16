package com.prithvi.userService.controller;

import com.prithvi.userService.dal.UserAndStockRepository;
import com.prithvi.userService.model.UserStock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserStockController {

    @Autowired
    UserAndStockRepository userAndStockRepository;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/stock")
    public UserStock getUserStock(){
        return userAndStockRepository.findById("USER123").get();
    }

    @MessageMapping("/send")
    @SendTo("/topic/stock-price")
    public String sendMessage(String message) {
        return message;
    }
}
