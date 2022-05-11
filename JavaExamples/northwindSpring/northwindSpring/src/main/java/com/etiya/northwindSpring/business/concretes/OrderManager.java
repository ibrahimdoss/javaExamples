package com.etiya.northwindSpring.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.northwindSpring.business.abstracts.OrderService;
import com.etiya.northwindSpring.core.utilities.results.DataResult;
import com.etiya.northwindSpring.core.utilities.results.SuccessDataResult;
import com.etiya.northwindSpring.dataAccess.abstracts.OrderDao;
import com.etiya.northwindSpring.entities.concretes.Order;

@Service
public class OrderManager implements OrderService {
	
	private OrderDao orderDao;

	@Autowired
	public OrderManager(OrderDao orderDao) {
		super();
		this.orderDao = orderDao;
	}

	@Override
	public DataResult<List<Order>> getAll() {
		return new SuccessDataResult<List<Order>>(this.orderDao.findAll(), "siparişler listelendi");
	}
	
}
