package com.prithvi.stockService.dal;

import com.prithvi.stockService.model.StockModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<StockModel, String> {
}
