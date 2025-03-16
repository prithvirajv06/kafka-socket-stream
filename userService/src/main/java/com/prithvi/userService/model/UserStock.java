package com.prithvi.userService.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@Builder
@Getter
@Setter
public class UserStock {
    @Id
    private String userId;
    private List<Stock> stockList;
}
