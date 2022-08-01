package com.ijimenez.indiapi.controller;

import com.ijimenez.indiapi.exceptions.ResourceNotFoundException;
import com.ijimenez.indiapi.model.Price;
import com.ijimenez.indiapi.repository.PriceRepository;
import com.ijimenez.indiapi.service.IPriceService;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PriceController {

    private final IPriceService priceService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public PriceController(IPriceService priceService) {
        this.priceService = priceService;
    }


    @GetMapping(value = "price/{brandID}/{productID}/{date}")
    public ResponseEntity<Price> getCurrentPrice(
            @Parameter(
                    description="Brand ID (1=ZARA)",
                    example="1",
                    required=true)
            @PathVariable(value = "brandID") Long brandId,
            @Parameter(
                    description="Product ID Number (35455=T-SHIRT)",
                    example="35455",
                    required=true)
            @PathVariable(value = "productID") Long productId,
            @Parameter(
                    description="Date (yyyy-MM-dd'T'HHmmss)",
                    example="2020-06-14T100000",
                    required=true)
            @PathVariable(value = "date") String date) throws ResourceNotFoundException {
      return priceService.getCurrentPrice(brandId, productId, date);
    }

    @GetMapping(value = "/prices")
    public List<Price> getPrices() {
        return priceService.findAll();
    }
    @PostMapping(value="/price")
    public ResponseEntity<Price> addPrice(@RequestBody Price price)  {

        Price p = priceService.addPrice(price);
        if (p == null) {
            return new ResponseEntity<Price>(HttpStatus.NOT_FOUND);
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body(p);
        }
    }
}
