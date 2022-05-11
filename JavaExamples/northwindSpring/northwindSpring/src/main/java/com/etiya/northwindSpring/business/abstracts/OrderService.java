package com.etiya.northwindSpring.business.abstracts;

import java.util.List;

import com.etiya.northwindSpring.core.utilities.results.DataResult;
import com.etiya.northwindSpring.entities.concretes.Order;

public interface OrderService {
	
	DataResult<List<Order>> getAll();

}
