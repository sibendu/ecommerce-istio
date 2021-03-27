package com.example;

import java.util.*;

public class ProductDB {
	
	private static Product[] products = new Product[] {
			new Product("B0001","Sample Product B0001", null, new Long(5000), new Double(1525.50), new Double(0)),
			new Product("B0002","Sample Product B0002", null, new Long(2000), new Double(2000), new Double(5)),			
			new Product("B0003","Sample Product B0003", null, new Long(1000), new Double(300), new Double(12))			
	};
	
	public static Product findProduct(Product p) {
		Product product = null;
		for (int i = 0; i < products.length; i++) {
			product = products[i];
			if(product.getCode().equals(p.getCode())) {
				return product;
			}
		}
		return null;
	}
	
}