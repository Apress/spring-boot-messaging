package com.apress.messaging.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

import com.apress.messaging.domain.Invoice;
import com.apress.messaging.domain.InvoiceWithTax;
import com.apress.messaging.domain.Item;
import com.apress.messaging.domain.Order;

//@Component
@RabbitListener(id="multi", queues = "${apress.amqp.queue}")
public class MultiListenerService {

	@RabbitHandler
    @SendTo("${apress.amqp.reply-exchange-queue}")
    public Order processInvoice(Invoice invoice) {
        Order order = new Order();
        
        //Process Invoice here...
        
        order.setInvoice(invoice);
      	return order;
    }

    @RabbitHandler
    public Order processInvoiceWithTax(InvoiceWithTax invoiceWithTax) {
    		Order order = new Order();
        
        //Process Invoice with Tax here...
    		    		    		
    		return order;
    }

    @RabbitHandler
    public String itemProcess(@Header("amqp_receivedRoutingKey") String routingKey, @Payload Item item) {
        //Some Process here...
    		return "{\"message\": \"OK\"}"; 
    }

}
