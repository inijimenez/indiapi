package com.ijimenez.indiapi.service;

import com.ijimenez.indiapi.exceptions.ResourceNotFoundException;
import com.ijimenez.indiapi.model.Price;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface IPriceService {
    ResponseEntity<Price> getCurrentPrice(Long brandID, Long productID, String date) throws ResourceNotFoundException;

    List<Price> findAll();

    Price addPrice(Price price);
}

