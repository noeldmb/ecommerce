package com.ecommerce.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * A POJO class that will contain the structure and date will be returned like respond by the "getFee()" Endpoint.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PriceDto {

    private Integer productId;
    private Integer brandId;
    private Integer priceList;
    private String startDate;
    private String endDate;
    private double price;

}
