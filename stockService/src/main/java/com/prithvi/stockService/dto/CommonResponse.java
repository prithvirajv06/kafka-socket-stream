package com.prithvi.stockService.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse {
    private String message;
    private Boolean status;
}
