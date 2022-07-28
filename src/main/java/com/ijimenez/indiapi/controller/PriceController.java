package com.ijimenez.indiapi.controller;

import com.ijimenez.indiapi.exceptions.ResourceNotFoundException;
import com.ijimenez.indiapi.model.Price;
import com.ijimenez.indiapi.repository.PriceRepository;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1")
public class PriceController {
    private final String dateFormatPattern = "yyyy-MM-dd HH.mm.ss";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private PriceRepository priceRepository;


    @GetMapping(value = "price/{brandID}/{productID}/{date}")
    public ResponseEntity<Price> getPrice(
            @Parameter(
                    description="Brand ID",
                    example="1",
                    required=true)
            @PathVariable(value = "brandID") Long brandId,
            @Parameter(
                    description="Product ID",
                    example="35455",
                    required=true)
            @PathVariable(value = "productID") Long productId,
            @Parameter(
                    description="Date (yyyy-MM-dd HH.mm.ss)",
                    example="2020-06-14 10.00.00",
                    required=true)
            @PathVariable(value = "date") String date) throws ResourceNotFoundException {
        logger.info("Retrieving price for brandId: {}, productId: {}, date: {}", brandId, productId, date);

        Price priceResponse = priceRepository.getPrice(brandId, productId, LocalDateTime.parse(date, DateTimeFormatter.ofPattern(dateFormatPattern)));
        if (priceResponse == null) {
            throw new ResourceNotFoundException("Price not found for this:: " + brandId + " " + productId + " " + date);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(priceResponse);
        }
    }

}
