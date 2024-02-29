package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class OrderDetailRepositoryImpl extends QuerydslRepositorySupport implements OrderDetailRepositoryCustom {
    public OrderDetailRepositoryImpl() {
        super(OrderDetail.class);
    }

    @Override
    public List<OrderDetail> findAllByOrderIdWithQuerydslFetchJoin(Integer orderId) {
        QOrderDetail orderDetail = QOrderDetail.orderDetail;
        QProduct product = QProduct.product;
        QOrder order = QOrder.order;
        QUser user = QUser.user;

        return from(orderDetail)
                .innerJoin(orderDetail.product, product).fetchJoin()
                .innerJoin(orderDetail.order, order).fetchJoin()
                .innerJoin(order.user, user).fetchJoin()
                .fetch();
    }
}
