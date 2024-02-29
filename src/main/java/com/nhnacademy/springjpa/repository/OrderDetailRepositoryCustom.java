package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.OrderDetail;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface OrderDetailRepositoryCustom {
    List<OrderDetail> findAllByOrderIdWithQuerydslFetchJoin(Integer orderId);
}
