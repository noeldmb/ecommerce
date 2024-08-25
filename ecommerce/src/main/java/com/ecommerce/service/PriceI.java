package com.ecommerce.service;
/**
 * Interface declaring the contract the will be implemented by the service/s.
 */
import com.ecommerce.model.dto.PriceDto;

public interface PriceI {

	PriceDto getPriceInfo(String dateApplication, int productId, int brandId);
}
