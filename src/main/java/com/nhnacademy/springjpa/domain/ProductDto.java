package com.nhnacademy.springjpa.domain;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public interface ProductDto {
    @Value("#{target.productId}")
    Integer getId();

    @Value("#{target.productName}")
    String getName();

    @Value("#{target.productUnitCost}")
    BigDecimal getCost();

    @Value("#{target.productShortDesc}")
    String getDesc();

    @Value("#{target.thumbnailImageUrl}")
    String getThumb();
}
