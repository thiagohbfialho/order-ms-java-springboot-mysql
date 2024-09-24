package com.canadafood.order.repository;

import com.canadafood.order.model.Order;
import com.canadafood.order.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Order o set o.status = :status where o = :order")
    void updateStatus(Status status, Order order);

    @Query(value = "Select o from Order o LEFT JOIN FETCH o.items where o.id = :id")
    Order findByOrderIdWithItems(Long id);
}
