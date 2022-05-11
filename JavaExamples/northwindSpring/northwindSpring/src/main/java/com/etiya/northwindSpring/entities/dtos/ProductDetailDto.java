package com.etiya.northwindSpring.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDto {
	
	private int id;
	private String productName;
	private String categoryName;
	private double unitPrice;
	
}
