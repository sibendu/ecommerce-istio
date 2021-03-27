package com.example;

import java.util.*;

import org.springframework.http.*;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductService {
	
	@GetMapping("/health")
	public String getMessage() {
		return "live";
	}
	
	@PostMapping("/")
	@ResponseBody
	public Product[] searchProductDetails(@RequestBody Product[] products) {
		Product temp = null;
		for (int i = 0; i < products.length; i++) {		
			temp = ProductDB.findProduct(products[i]);
			if(temp != null) {
				products[i] = temp;
			}else {
				products[i].setDescription("NOT_FOUND");
			}
		}
		
	    return products;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product[]> findAllProducts() {

		ResponseEntity<Product[]> response = null;
		
		Product[] products = new Product[1];
		products[0] = new Product("A0001", "Sample Product 001", null, new Long(1230), new Double(1000.50), new Double(0));
		
		response = new ResponseEntity<Product[]>(products, HttpStatus.OK);
		
		return response;
	}

}