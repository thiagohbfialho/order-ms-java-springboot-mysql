package com.canadafood.order.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

    @RabbitListener(queues = "payment.completed")
    public void receivedMessage(Message message) {
        System.out.println("Received message: " + message.toString());
    }

}
