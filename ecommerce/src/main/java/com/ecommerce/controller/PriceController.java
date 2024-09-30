package com.ecommerce.controller;

/*
 * Controller created with the objective of handling all requests made to the "getFee" EndPoint
 * with the objective of knowing the price to apply to a Product in a specific time.
 */

import com.ecommerce.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.common.Common;
import com.ecommerce.exception.BadRequestException;
import com.ecommerce.model.dto.PriceDto;
import com.ecommerce.service.PriceI;

@RestController
@RequestMapping("${price.controller.request-mapping}")
public class PriceController {

    private final PriceI priceI;
    private final Common common;

    public PriceController(PriceI priceI, com.ecommerce.common.Common common) {
        this.priceI = priceI;
        this.common = common;
    }

    @GetMapping("{applicationdate}/{productid}/{brandid}")
    public ResponseEntity<PriceDto> getFee(@PathVariable("applicationdate") String applicationDate,
                                           @PathVariable("productid") int productId, @PathVariable("brandid") int brandId) {

        //Validating parameter
        if (!common.isDateValid(applicationDate))
            throw new BadRequestException("msg.date.format.incorrect");

        if (productId <= 0)
            throw new BadRequestException("msg.product.id.invalid");

        if (brandId <= 0)
            throw new BadRequestException("msg.brand.id.invalid");

        PriceDto currentPrice = priceI.getPriceInfo(applicationDate, productId, brandId);

        //Validating response
        if (currentPrice == null)
            throw new ResourceNotFoundException("msg.resource.not.found");

        return new ResponseEntity<>(currentPrice, HttpStatus.OK);
    }
}
