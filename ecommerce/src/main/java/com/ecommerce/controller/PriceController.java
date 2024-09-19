package com.ecommerce.controller;

/*
 * Controller created with the objective of handling all requests made to the "getFee" EndPoint
 * with the objective of knowing the price to apply to a Product in a specific time.
 */

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

    public PriceController(PriceI priceI) {
        this.priceI = priceI;
    }

    @GetMapping("{applicationdate}/{productid}/{brandid}")
    public ResponseEntity<PriceDto> getFee(@PathVariable("applicationdate") String applicationDate,
                                           @PathVariable("productid") int productId, @PathVariable("brandid") int brandId) {

        //Validating parameter
        if (!Common.isDateValid(applicationDate))
            throw new BadRequestException(Common.MSG_DATE_FORMAT_INCORRECT);

        if (productId <= 0)
            throw new BadRequestException(Common.MSG_PRODUCT_ID_INVALID);

        if (brandId <= 0)
            throw new BadRequestException(Common.MSG_BRAND_ID_INVALID);

        return new ResponseEntity<>(priceI.getPriceInfo(applicationDate, productId, brandId), HttpStatus.OK);
    }
}
