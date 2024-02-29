package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.OrderDetail;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>, OrderDetailRepositoryCustom {
    List<OrderDetail> findAllByOrderId(Integer orderId);

    @Query("select od from OrderDetail od inner join fetch od.product p inner join fetch od.order o inner join fetch o.user u")
    List<OrderDetail> findAllByOrderIdWithJPQLJoinFetch(@Param("orderId") Integer orderId);

    @EntityGraph("orderDetailWithOrderAndProduct")
    List<OrderDetail> getAllByOrderId(Integer orderId);

}
