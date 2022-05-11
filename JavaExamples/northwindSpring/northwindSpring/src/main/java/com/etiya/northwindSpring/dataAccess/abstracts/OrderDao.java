package com.etiya.northwindSpring.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.northwindSpring.entities.concretes.Order;

public interface OrderDao extends JpaRepository<Order, Integer> {

}
