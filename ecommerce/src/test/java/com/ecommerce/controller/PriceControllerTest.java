package com.ecommerce.controller;

import com.ecommerce.exception.BadRequestException;
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

        ResponseEntity<PriceDto> result = priceController.getFee(date, productId, brandId);

        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    void getFee_throwBadRequestExceptionInvalidDateFormatTest() {

        BadRequestException thrown = Assertions.assertThrows(BadRequestException.class, () -> {

            // ... Code under test  ...
            priceController.getFee("2020-50-14 00:00:00", productId, brandId);
            throw new BadRequestException("Format of Date incorrect, correct format yyyy-MM-dd HH:mm:ss");
        });

        Assertions.assertEquals("Format of Date incorrect, correct format yyyy-MM-dd HH:mm:ss", thrown.getMessage());
    }

    @Test
    void getFee_throwBadRequestExceptionInvalidProductIdTest() {

        BadRequestException thrown = Assertions.assertThrows(BadRequestException.class, () -> {

            // ... Code under test  ...
            priceController.getFee(date, 0, brandId);
            throw new BadRequestException("Product ID invalid");
        });

        Assertions.assertEquals("Product ID invalid", thrown.getMessage());
    }

    @Test
    void getFee_throwBadRequestExceptionInvalidBrandIdTest() {

        BadRequestException thrown = Assertions.assertThrows(BadRequestException.class, () -> {

            // ... Code under test  ...
            priceController.getFee(date, productId, 0);
            throw new BadRequestException("Brand ID invalid");
        });

        Assertions.assertEquals("Brand ID invalid", thrown.getMessage());
    }

}