package com.canadafood.order.dto;

import com.canadafood.order.model.OrderItem;
import com.canadafood.order.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private LocalDateTime orderDate;
    private Status status;
    private List<OrderItemDto> items = new ArrayList<>();

}
