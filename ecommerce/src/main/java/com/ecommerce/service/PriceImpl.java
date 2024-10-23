package com.ecommerce.service;
/*
 * Service implementing the contract defined by the interface "PriceI", and the logic
 * needed in order to know the right price for a product in specify time of day and Brand.
 */

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.ecommerce.common.Common;
import com.ecommerce.model.dto.PriceDto;
import com.ecommerce.model.entity.PriceEntity;
import com.ecommerce.repository.PriceRepository;

@Service
public class PriceImpl implements PriceI {

    private final PriceRepository priceRepository;
    private final Common common;

    public PriceImpl(PriceRepository priceRepository, Common common) {
        this.priceRepository = priceRepository;
        this.common = common;
    }

    @Override
    public PriceDto getPriceInfo(String dateApplication, int productId, int brandId) {

        //Casting String Date parameter to LocalDateTime
        LocalDateTime date = common.convertStringToLocalDateTime(dateApplication);

        //Fetching information to data Bases according parameter
        List<PriceEntity> prices = priceRepository.findPrices(productId, brandId, date);

        //If not exist information in database an Exception is thrown.
        if (prices.isEmpty())
            return null;

        PriceEntity priceEntity = prices.stream().max(Comparator.comparing(PriceEntity::getPriority)).get();

        //The entity is casting to DTO
        return new PriceDto(
                priceEntity.getProductId(),
                priceEntity.getBrandId(),
                priceEntity.getPriceList(),
                common.convertLocalDateTimeToString(priceEntity.getStartDate()),
                common.convertLocalDateTimeToString(priceEntity.getEndDate()),
                priceEntity.getPrice());
    }

}