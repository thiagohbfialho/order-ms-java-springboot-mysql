package com.canadafood.order.amqp;

import com.canadafood.order.dto.PaymentDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

    @RabbitListener(queues = "payment.completed")
    public void receivedMessage(PaymentDto paymentDto) {
        String message = """
                Payment data: %s
                Order number: %s
                Amount CAD$: %s
                Status: %s
                """.formatted(paymentDto.getId(),
                paymentDto.getOrderId(),
                paymentDto.getValue(),
                paymentDto.getStatus());
        System.out.println("Received message: " + message);
    }

}
