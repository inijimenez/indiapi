package com.ijimenez.indiapi.controller;

import com.ijimenez.indiapi.exceptions.ResourceNotFoundException;
import com.ijimenez.indiapi.model.Price;
import com.ijimenez.indiapi.repository.PriceRepository;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1")
public class PriceController {

    @Value("${indiapp-dateformat}")
    private String dateFormatPattern;

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
                    description="Date (yyyy-MM-dd'T'HHmmss)",
                    example="2020-06-14T100000",
                    required=true)
            @PathVariable(value = "date") String date) throws ResourceNotFoundException {
        logger.info("Retrieving price for brandId: {}, productId: {}, date: {}", brandId, productId, date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatPattern);
        LocalDateTime myDate = LocalDateTime.parse(date, formatter);

        Price priceResponse = priceRepository.getPrice(brandId, productId, myDate);
        if (priceResponse == null) {
            throw new ResourceNotFoundException("Price not found for this:: " + brandId + " " + productId + " " + date);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(priceResponse);
        }
    }

}
