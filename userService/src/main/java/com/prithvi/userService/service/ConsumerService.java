package com.prithvi.userService.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prithvi.userService.dal.UserAndStockRepository;
import com.prithvi.userService.model.Stock;
import com.prithvi.userService.model.UserStock;
import jakarta.ws.rs.core.NoContentException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConsumerService {

    private final SimpMessagingTemplate messagingTemplate;
    private final ModelMapper modelMapper;
    private final UserAndStockRepository userAndStock;

    @KafkaListener(topics = "UPDATE_STOCK", groupId = "websocket-group")
    void consume(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Stock stock = objectMapper.readValue(message, Stock.class);
            UserStock user123 = userAndStock.findById("USER123").orElseThrow(() -> new NoContentException("No content fount with ID:USER123"));
            if(user123.getStockList().stream()
                    .filter(stock1 -> stock.getSkuId().equalsIgnoreCase(stock1.getSkuId())).collect(Collectors.toList()).size()>0)
            user123.getStockList().stream()
                    .filter(stock1 -> stock.getSkuId().equalsIgnoreCase(stock1.getSkuId()))
                    .findFirst()
                    .ifPresent(stockToUpdate -> {
                        stockToUpdate.setStockCount(stock.getStockCount());
                        stockToUpdate.setStockName(stock.getStockName());
                    });
            else{
                user123.getStockList().add(stock);
            }
            userAndStock.save(user123);
            messagingTemplate.convertAndSend("/topic/stock-price", userAndStock.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @KafkaListener(topics = "NEW_STOCK", groupId = "websocket-group")
    void consumeNew(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Stock stock = objectMapper.readValue(message, Stock.class);
            UserStock user123 = userAndStock.findById("USER123").orElseThrow(() -> new NoContentException("No content fount with ID:USER123"));
            user123.getStockList().add(stock);
            userAndStock.save(user123);
            messagingTemplate.convertAndSend("/topic/stock-price", userAndStock.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
