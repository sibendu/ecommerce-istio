package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class ProductDB {
	
	private static Product[] products = new Product[] {
			new Product("A0001","Sample Product A0001", null, new Long(5000), new Double(1525.50), new Double(0)),
			new Product("A0002","Sample Product A0002", null, new Long(2000), new Double(2000), new Double(10))			
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