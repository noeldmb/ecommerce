package com.ecommerce.service;

import com.ecommerce.common.Common;
import com.ecommerce.model.dto.PriceDto;
import com.ecommerce.model.entity.PriceEntity;
import com.ecommerce.repository.PriceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceImpl priceImpl;

    @Test
    void getPriceInfoTest() {

        int productId = 35455;
        int brandId = 1;
        LocalDateTime date = Common.convertStringToLocalDateTime("2020-06-14 00:00:00");
        PriceDto priceWaited = new PriceDto(35455, 1,0,"","",7.7);

        //PriceEntity priceEntity = mock(PriceEntity.class);
        List<PriceEntity> prices = List.of(
                new PriceEntity(1, Common.convertStringToLocalDateTime("2020-06-14 00:00:00"), Common.convertStringToLocalDateTime("2020-06-14 00:00:00"), 1, 35455, 0, 35.50, "EUR")
        );

        when(priceRepository.findPrices(productId,brandId,date)).thenReturn(prices);

        final PriceDto priceDto1 = priceImpl.getPriceInfo("2020-06-14 00:00:00",productId,brandId);


        Assertions.assertEquals(priceWaited.getProductId(),priceDto1.getProductId());
    }
}