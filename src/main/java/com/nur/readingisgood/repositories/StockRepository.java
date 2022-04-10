package com.nur.readingisgood.repositories;

import com.nur.readingisgood.models.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {
    @Query(value="{itemId:'?0'}")
    Stock findByItemId(String itemId);
}
