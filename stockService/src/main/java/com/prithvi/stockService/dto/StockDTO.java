package com.prithvi.stockService.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StockDTO {
    private String skuId;
    private String stockName;
    private Integer stockCount;
}
