package com.vicky.microservices.order_service.dto;

import java.math.BigDecimal;

public record OrderRequest(Long id , String orderNumber , String skuCode , Integer quantity , BigDecimal price) {
}
