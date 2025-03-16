package com.prithvi.userService.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stock {
    private String skuId;
    private String stockName;
    private Integer stockCount;
}
