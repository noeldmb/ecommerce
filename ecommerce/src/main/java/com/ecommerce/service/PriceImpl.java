package com.ecommerce.service;
/*
 * Service implementing the contract defined by the interface "PriceI", and the logic
 * needed in order to know the right price for a product in specify time of day and Brand.
 */

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.common.Common;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.dto.PriceDto;
import com.ecommerce.model.entity.PriceEntity;
import com.ecommerce.repository.PriceRepository;

@Service
public class PriceImpl implements PriceI {

    private final PriceRepository priceRepository;

    public PriceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public PriceDto getPriceInfo(String dateApplication, int productId, int brandId) {

        //Casting String Date parameter to LocalDateTime
        LocalDateTime date = Common.convertStringToLocalDateTime(dateApplication);

        //Fetching information to data Bases according parameter
        List<PriceEntity> prices = priceRepository.findPrices(productId, brandId, date);

        //If not exist information in database an Exception is thrown.
        if (prices.isEmpty())
            throw new ResourceNotFoundException(Common.MSG_RESOURCE_NOT_FOUND);

        PriceEntity priceEntity = prices.stream().max(Comparator.comparing(PriceEntity::getPriority)).get();

        //The entity is casting to DTO
        return new PriceDto(
                priceEntity.getProductId(),
                priceEntity.getBrandId(),
                priceEntity.getPriceList(),
                Common.convertLocalDateTimeToString(priceEntity.getStartDate()),
                Common.convertLocalDateTimeToString(priceEntity.getEndDate()),
                priceEntity.getPrice());
    }

}