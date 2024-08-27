package com.ecommerce.service;
/**
 * Service implementing the contract defined by the interface "PriceI", and the logic
 * needed in order to know the right price for a product in specify time of day and Brand.
 */
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ecommerce.common.Common;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.dto.PriceDto;
import com.ecommerce.model.entity.PriceEntity;
import com.ecommerce.repository.PriceRepository;

@Service
public class PriceImpl implements PriceI {

	private PriceRepository priceRepository;
	private Common common;
	private ModelMapper modelMapper;

	public PriceImpl(PriceRepository priceRepository, Common common, ModelMapper modelMapper) {
		this.priceRepository = priceRepository;
		this.common = common;
		this.modelMapper = modelMapper; 
	}

	@Override
	public PriceDto getPriceInfo(String dateApplication, int productId, int brandId) {
		
		//Casting String Date parameter to LocalDateTime
		LocalDateTime date = common.convertStringToLocalDateTime(dateApplication);
		
		//Fetching information to data Bases according parameter
		List<PriceEntity> prices = priceRepository.findPrice(productId, brandId, date);
		
		//If not exist information an Exception is thrown, informing there is not information
		//in data base according current parameter
		if (prices.isEmpty())
			throw new ResourceNotFoundException(common.getMessage("msg.resource.not.found"));
		
		//If there is more that one fee, it is defined one fee in accordance with the highest priority. 
		Comparator<PriceEntity> comparator = Comparator.comparing(PriceEntity::getPriority);
		PriceEntity priceEntity = prices.stream().max(comparator).get();
		
		//The entity is casting to DTO
		return convertEntityToDto(priceEntity);
	}

	private PriceDto convertEntityToDto(PriceEntity priceEntity) {

		PriceDto priceDto = modelMapper.map(priceEntity, PriceDto.class);
		//Giving the right format to the date.
		priceDto.setStartDate(common.convertLocalDateTimeToString(priceEntity.getStartDate()));
		priceDto.setEndDate(common.convertLocalDateTimeToString(priceEntity.getEndDate()));

		return priceDto;
	}
}