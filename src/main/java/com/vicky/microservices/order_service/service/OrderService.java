package com.vicky.microservices.order_service.service;

import com.vicky.microservices.order_service.client.InventoryClient;
import com.vicky.microservices.order_service.dto.OrderRequest;
import com.vicky.microservices.order_service.model.Order;
import com.vicky.microservices.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final InventoryClient inventoryClient;
    private final OrderRepository orderRepository;
    public void placeOrder(OrderRequest orderRequest)
    {
        // using Mockito to mock the inventoryClient but it does not actually http call instead it returns the value that we have defined in the mock.
        // using Wiremock to mock the inventoryClient it actually http does the call and returns the value that we have defined in the mock.
        var isInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        //map orderRequest to order object

        if(isInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            order.setSkuCode(orderRequest.skuCode());

            //save order to OrderRepository
            orderRepository.save(order);
        }else{
            throw new RuntimeException("Product with skuCode "+orderRequest.skuCode() + " is not in stock");
        }


    }
}
