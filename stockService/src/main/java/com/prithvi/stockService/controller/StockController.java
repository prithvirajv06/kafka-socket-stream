package com.prithvi.stockService.controller;

import com.prithvi.stockService.dto.CommonResponse;
import com.prithvi.stockService.dto.StockDTO;
import com.prithvi.stockService.service.StockManagementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/v1/manage")
@AllArgsConstructor
@Slf4j
public class StockController {

    private StockManagementService stockManagementService;

    
    @PostMapping("/stock")
    public ResponseEntity<StockDTO> saveStock(@RequestBody StockDTO stockDTO) {
        log.debug("saveStock called:{}", stockDTO);
        return ResponseEntity.ok(stockManagementService.saveStock(stockDTO));
    }

    
    @GetMapping("/stock")
    public ResponseEntity<StockDTO> getStock(@RequestParam String skuId) {
        log.debug("getStock called:{}", skuId);
        return ResponseEntity.ok(stockManagementService.getStock(skuId));
    }

    
    @PutMapping("/stock")
    public ResponseEntity<StockDTO> putStock(@RequestBody StockDTO stockDTO) {
        log.debug("putStock called:{}", stockDTO);
        return ResponseEntity.ok(stockManagementService.saveStock(stockDTO));
    }

    
    @DeleteMapping("/stock")
    public ResponseEntity<CommonResponse> deleteStock(@RequestParam String skuId) {
        log.debug("deleteStock called:{}", skuId);
        return ResponseEntity.ok(stockManagementService.deleteStock(skuId));
    }
}
