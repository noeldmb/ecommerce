package com.ecommerce.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.model.entity.PriceEntity;

public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {

	@Query("SELECT p FROM PriceEntity p WHERE p.productId = ?1 AND p.brandId = ?2 And ?3 BETWEEN p.startDate AND p.endDate")
	List<PriceEntity> findPrice(int productId, int randId, LocalDateTime startDate);
}
