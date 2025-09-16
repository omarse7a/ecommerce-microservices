package com.konecta.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cart-service", url = "http://localhost:5147")
public interface CartClient {
    @RequestMapping(method = RequestMethod.PUT, value = "api/v1/carts/{userId}")
    String RefreshCartTtl(@PathVariable("userId") Long userId, @RequestParam("ttl") int TtlInDays);
}
