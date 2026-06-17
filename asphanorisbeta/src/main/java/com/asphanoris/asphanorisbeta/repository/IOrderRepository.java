package com.asphanoris.asphanorisbeta.repository;

import com.asphanoris.asphanorisbeta.domain.RealTripOrder;
import java.util.List;

public interface IOrderRepository {
    RealTripOrder addOrder(RealTripOrder order);
    RealTripOrder modifyOrder(RealTripOrder order);
    void deleteOrder(RealTripOrder order);
    RealTripOrder getOrder(Long orderId);
    List<RealTripOrder> getAllOrders();
}