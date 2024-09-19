package com.ecommerce.common;

/*
 * Component created with the objective of loading the Test data into the H2 database at the time of the Application start
 */

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ecommerce.model.entity.PriceEntity;
import com.ecommerce.repository.PriceRepository;

@Component
public class LoadData implements CommandLineRunner {

    private final PriceRepository priceRepository;

    public LoadData(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<PriceEntity> prices = List.of(

                new PriceEntity(1, Common.convertStringToLocalDateTime("2020-06-14 00:00:00"),
                        Common.convertStringToLocalDateTime("2020-12-31 23:59:59"), 1, 35455, 0, 35.50, "EUR"),

                new PriceEntity(1, Common.convertStringToLocalDateTime("2020-06-14 15:00:00"),
                        Common.convertStringToLocalDateTime("2020-06-14 18:30:00"), 2, 35455, 1, 25.45, "EUR"),

                new PriceEntity(1, Common.convertStringToLocalDateTime("2020-06-15 00:00:00"),
                        Common.convertStringToLocalDateTime("2020-06-15 11:00:00"), 3, 35455, 1, 30.50, "EUR"),

                new PriceEntity(1, Common.convertStringToLocalDateTime("2020-06-15 16:00:00"),
                        Common.convertStringToLocalDateTime("2020-12-31 23:59:59"), 4, 35455, 1, 38.95, "EUR")
        );

        priceRepository.saveAll(prices);
    }
}
