package com.ecommerce.controller;

import com.ecommerce.common.Common;
import com.ecommerce.exception.BadRequestException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.dto.PriceDto;
import com.ecommerce.service.PriceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    PriceImpl priceImpl;

    @Mock
    private Common common;

    @InjectMocks
    PriceController priceController;

    int productId;
    int brandId;
    String date;

    @BeforeEach
    void setUp() {
        productId = 35455;
        brandId = 1;
        date = "2020-06-14 00:00:00";
    }

    @Test
    void getFeeTest() {

        PriceDto response = new PriceDto(35455, 1, 0, "", "", 7.7);

        when(priceImpl.getPriceInfo(date, productId, brandId)).thenReturn(response);
        when(common.isDateValid(date)).thenReturn(true);

        ResponseEntity<PriceDto> result = priceController.getFee(date, productId, brandId);

        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    void getFee_throwBadRequestExceptionInvalidDateFormatTest() {

        BadRequestException thrown = Assertions.assertThrows(BadRequestException.class, () -> {

            // ... Code under test  ...
            priceController.getFee("2020-50-14 00:00:00", productId, brandId);
            throw new BadRequestException("msg.date.format.incorrect");
        });

        Assertions.assertEquals("msg.date.format.incorrect", thrown.getMessage());
    }

    @Test
    void getFee_throwBadRequestExceptionInvalidProductIdTest() {

        when(common.isDateValid(date)).thenReturn(true);

        BadRequestException thrown = Assertions.assertThrows(BadRequestException.class, () -> {

            // ... Code under test  ...
            priceController.getFee(date, 0, brandId);
            throw new BadRequestException("msg.product.id.invalid");
        });

        Assertions.assertEquals("msg.product.id.invalid", thrown.getMessage());
    }

    @Test
    void getFee_throwBadRequestExceptionInvalidBrandIdTest() {

        when(common.isDateValid(date)).thenReturn(true);

        BadRequestException thrown = Assertions.assertThrows(BadRequestException.class, () -> {

            // ... Code under test  ...
            priceController.getFee(date, productId, 0);
            throw new BadRequestException("msg.brand.id.invalid");
        });

        Assertions.assertEquals("msg.brand.id.invalid", thrown.getMessage());
    }

    @Test
    void getFee_throwResourceNotFoundExceptionTest() {

        when(common.isDateValid(date)).thenReturn(true);

        ResourceNotFoundException thrown = Assertions.assertThrows(ResourceNotFoundException.class, () -> {

            // ... Code under test  ...
            priceController.getFee(date, productId, 2);
            throw new ResourceNotFoundException("msg.resource.not.found");
        });

        Assertions.assertEquals("msg.resource.not.found", thrown.getMessage());
    }

}