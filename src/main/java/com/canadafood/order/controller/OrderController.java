package com.canadafood.order.controller;

import com.canadafood.order.dto.OrderDto;
import com.canadafood.order.dto.StatusDto;
import com.canadafood.order.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderDto> getOrders() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable @NotNull Long id) {
        OrderDto orderDto = orderService.findById(id);
        return ResponseEntity.ok(orderDto);
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody @Valid OrderDto orderDto, UriComponentsBuilder uriBuilder) {
        OrderDto newOrderDto = orderService.create(orderDto);

        URI uri = uriBuilder.path("/orders/{id}").buildAndExpand(newOrderDto.getId()).toUri();

        return ResponseEntity.created(uri).body(newOrderDto);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long id, @RequestBody StatusDto status) {
        OrderDto orderDto = orderService.updateStatus(id, status);

        return ResponseEntity.ok(orderDto);
    }

    @PutMapping("/{id}/paid")
    public ResponseEntity<Void> approvePayment(@PathVariable @NotNull Long id) {
        orderService.approveOrderPayment(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/port")
    public String portReturn(@Value("${local.server.port}") String port){
        return String.format("Request responded by instance running on the port %s", port);
    }

}
