package com.ijimenez.indiapi.repository;

import com.ijimenez.indiapi.model.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface PriceRepository extends CrudRepository<Price, Long> {
    @Query(value = "SELECT * FROM PRICES where BRAND_ID=:brandID AND PRODUCT_ID=:productID AND :date >= START_DATE AND :date <= END_DATE ORDER BY PRIORITY DESC LIMIT 1",
            nativeQuery = true)
    Price getPrice(
            @Param("brandID") Long brandID,
            @Param("productID") Long productID,
            @Param("date") LocalDateTime date);
}