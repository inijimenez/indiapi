package com.ijimenez.indiapi.service;

import com.ijimenez.indiapi.exceptions.ResourceNotFoundException;
import com.ijimenez.indiapi.model.Price;
import com.ijimenez.indiapi.repository.PriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PriceService implements IPriceService
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${indiapp-dateformat}")
    private String dateFormatPattern;

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public ResponseEntity<Price> getCurrentPrice(Long brandID, Long productID, String date) throws ResourceNotFoundException {
        logger.info("Retrieving price for brandId: {}, productId: {}, date: {}", brandID, productID, date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatPattern);
        LocalDateTime myDate = LocalDateTime.parse(date, formatter);

        Price priceResponse = priceRepository.getPrice(brandID, productID, myDate);
        if (priceResponse == null) {
            String strError = String.format("No price found for brandId: %d, productId: %d, date: %s", brandID, productID, date);
            throw new ResourceNotFoundException(strError);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(priceResponse);
        }

    }

    @Override
    public List<Price> findAll() {
       return priceRepository.findAll();
    }

    @Override
    public Price addPrice(Price price) {
        return priceRepository.save(price);
    }


}
