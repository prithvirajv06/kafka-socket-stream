package com.prithvi.stockService.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.prithvi.stockService.dal.StockRepository;
import com.prithvi.stockService.dto.CommonResponse;
import com.prithvi.stockService.dto.StockDTO;
import com.prithvi.stockService.model.StockModel;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;

@Service
@AllArgsConstructor
public class StockManagementService {
    private final ModelMapper modelMapper;
    private final StockRepository stockRepository;
    private final KafkaTemplate kafkaTemplate;

    public StockDTO saveStock(StockDTO stockDTO) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(stockDTO);
            StockModel stockModel = modelMapper.map(stockDTO, StockModel.class);
            if(stockRepository.findById(stockDTO.getSkuId()).isEmpty()){
                kafkaTemplate.send("NEW_STOCK", "STOCK", json);
            }else {
                kafkaTemplate.send("UPDATE_STOCK", "STOCK", json);
            }
            return modelMapper.map(stockRepository.save(stockModel), StockDTO.class);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public CommonResponse deleteStock(String skuId) {
        kafkaTemplate.send("NEW_STOCK", "STOCK", skuId);
        stockRepository.deleteById(skuId);
        return CommonResponse.builder().status(true).message("Deleted").build();
    }

    public StockDTO getStock(String skuId) {
        return modelMapper.map(stockRepository.findById(skuId), StockDTO.class);
    }
}
