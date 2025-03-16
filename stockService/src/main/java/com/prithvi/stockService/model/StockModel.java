package com.prithvi.stockService.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
public class StockModel {
    @Id
    private String skuId;
    private String stockName;
    private Integer stockCount;
}
