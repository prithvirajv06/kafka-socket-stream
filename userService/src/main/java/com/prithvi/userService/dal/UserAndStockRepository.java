package com.prithvi.userService.dal;

import com.prithvi.userService.model.UserStock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAndStockRepository extends MongoRepository<UserStock, String> {
}
