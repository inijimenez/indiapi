package com.ijimenez.indiapi.service;

import com.ijimenez.indiapi.exceptions.ResourceNotFoundException;
import com.ijimenez.indiapi.model.Price;
import com.ijimenez.indiapi.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService {
    @Autowired
    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getPrice(Long brandId, Long productId, LocalDateTime date) throws ResourceNotFoundException {

       Price price = priceRepository.getPrice(brandId, productId, date);
       if (price == null) {
           throw new ResourceNotFoundException("Price not found for this:: " + brandId + " " + productId + " " + date);
       }
       else {
           return price;
       }

    }
}
