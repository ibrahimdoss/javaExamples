package com.etiya.northwindSpring.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.northwindSpring.business.abstracts.OrderService;
import com.etiya.northwindSpring.core.utilities.results.DataResult;
import com.etiya.northwindSpring.entities.concretes.Order;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
	
	private OrderService orderService;
	
	public OrdersController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Order>> getAll(){
		return this.orderService.getAll();
	}

}
