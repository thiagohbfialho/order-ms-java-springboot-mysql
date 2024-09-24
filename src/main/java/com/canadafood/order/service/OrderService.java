package com.canadafood.order.service;

import com.canadafood.order.dto.OrderDto;
import com.canadafood.order.dto.StatusDto;
import com.canadafood.order.model.Order;
import com.canadafood.order.model.Status;
import com.canadafood.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;


    public OrderDto findById(Long id) {
        var order = orderRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(order, OrderDto.class);
    }

    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream()
                .map(p -> modelMapper.map(p, OrderDto.class))
                .collect(Collectors.toList());
    }

    public OrderDto create(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);

        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Status.DONE);
        order.getItems().forEach(item -> item.setOrder(order));
        Order orderSaved = orderRepository.save(order);

        return modelMapper.map(orderSaved, OrderDto.class);
    }

    public OrderDto updateStatus(Long id, StatusDto statusDto) {
        var order = orderRepository.findByOrderIdWithItems(id);
        if (order == null) {
            throw new EntityNotFoundException();
        }
        order.setStatus(statusDto.getStatus());
        orderRepository.updateStatus(statusDto.getStatus(), order);
        return modelMapper.map(order, OrderDto.class);
    }

    public void approveOrderPayment(Long id) {
        var order = orderRepository.findByOrderIdWithItems(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setStatus(Status.PAID);
        orderRepository.updateStatus(Status.PAID, order);
    }

}
