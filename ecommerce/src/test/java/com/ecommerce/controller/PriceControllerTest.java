package com.ecommerce.controller;

import com.ecommerce.model.dto.PriceDto;
import com.ecommerce.service.PriceImpl;
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

    @Test
    void getFeeTest() {

        int productId = 35455;
        int brandId = 1;
        String date = "2020-06-14 00:00:00";
        PriceDto response = new PriceDto(35455, 1,0,"","",7.7);


        when(priceImpl.getPriceInfo(date, productId, brandId)).thenReturn(response);

        ResponseEntity<PriceDto> result = priceController.getFee(date,productId,brandId);

        assertEquals(HttpStatus.OK,result.getStatusCode());

    }

}