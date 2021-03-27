package com.example;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.*;

import com.example.grpc.client.ProductQuery;
import com.example.grpc.client.ProductResponse;
import com.google.gson.Gson;

import java.util.*;

@RestController
@RequestMapping("/product")
public class ProductService {
	
	//@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/health")
	public String getMessage() {
		return "live";
	}
	
	@PostMapping("/")
	@ResponseBody
	public Product[] searchProductDetails(@RequestBody Product[] products) throws Exception {
	    
		String url = "";
		ArrayList<Product> erpA = new ArrayList<>();
		ArrayList<Product> erpB = new ArrayList<>();
		ArrayList<Product> erpC = new ArrayList<>();
		ArrayList<Product> noErp = new ArrayList<>();
		
		for (int i = 0; i < products.length; i++) {
			if(products[i].getCode().startsWith("A")) {
				erpA.add(products[i]);
			}else if(products[i].getCode().startsWith("B") || products[i].getCode().startsWith("C")) {
				erpB.add(products[i]);
			}else if(products[i].getCode().startsWith("D")) {
				erpC.add(products[i]);
			}else {
				products[i].setDescription("NOT_FOUND");
				noErp.add(products[i]);
			}
		}
		
		Product[] erpAproducts = new Product[erpA.size()];
		Product[] erpBproducts = new Product[erpB.size()];
		
		for (int i = 0; i < erpAproducts.length; i++) {
			erpAproducts[i] =  erpA.get(i);
		}
		for (int i = 0; i < erpBproducts.length; i++) {
			erpBproducts[i] =  erpB.get(i);
		}
		
		
		ArrayList<Product> allProducts = new ArrayList<>();
		
		//System.out.println(erpAproducts.length);
		if(erpAproducts.length > 0 ) {
			//System.out.println("Calling ERP A");
			url = System.getenv("ERP_A_URL");
			if(url == null) {
				url = "http://localhost:8091/product/";
			}
			Product[] productsErpA = getProductERP(url, erpAproducts);
			
			for (int i = 0; i < productsErpA.length; i++) {
				allProducts.add(productsErpA[i]);
			}
			
		}
		
		//System.out.println(erpBproducts.length);
		Product[] productsErpB = null;
		if(erpBproducts.length > 0 ) {
			url = System.getenv("ERP_B_URL");
			if(url == null) {
				url = "http://localhost:8092/product/";	
			}
			productsErpB = getProductERP(url, erpBproducts);
			
			for (int i = 0; i < productsErpB.length; i++) {
				allProducts.add(productsErpB[i]);
			}
		}
		
		
		//Start: Calling ERP-C service using GRPC
		
		Product[] productsErpC = null;
		
		ArrayList<String> product_codes = new ArrayList<String>();
		for (Product p: erpC) {
			product_codes.add(p.getCode());
		}
		
		if(product_codes.size() > 0 ) {
			
			url = System.getenv("ERP_C_HOST") != null? System.getenv("ERP_C_HOST"): "localhost";	
			
			int port = System.getenv("ERP_C_PORT") != null? Integer.parseInt(System.getenv("ERP_C_PORT")): 8093;
			
			productsErpC = findProductsFromERPC(url, port, product_codes);
			
			for (int i = 0; i < productsErpC.length; i++) {
				allProducts.add(productsErpC[i]);
			}
		}
		//End: Calling ERP-C service using GRPC
		
		
		for (int i = 0; i < noErp.size(); i++) {
			allProducts.add(noErp.get(i));
		}
		
		products = new Product[allProducts.size()];
		for (int i = 0; i < products.length; i++) {
			products[i] =  allProducts.get(i);
		}
		
	    return products;
	}
	
	public Product[] getProductERP(String url, Product[] products) throws Exception {

		Gson gson = new Gson();
		String strRequest = gson.toJson(products);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>(strRequest, headers);

		restTemplate = new RestTemplate();
		
		Object[] prods = restTemplate.postForObject(url, request, Product[].class);
		System.out.println(products);
		
		products = new Product[prods.length];
		for (int i = 0; i < prods.length; i++) {
			products[i] = (Product)prods[i];
		}	
		
		return products;
	}
	
	public static Product[] findProductsFromERPC(String HOST, int PORT, ArrayList<String> product_codes) throws Exception {
		Product[] result = null;
		
		ERPC_Client client = new ERPC_Client(HOST, PORT); 
		try {
			ProductQuery query = ProductQuery.newBuilder().addAllCodes(product_codes).build();
			ProductResponse response = client.getProducts(query);
			
			result = new Product[response.getProductsCount()];
			for (int i = 0; i < response.getProductsCount(); i++) {
				
				com.example.grpc.client.Product erpCProd = response.getProducts(i);
				
				result[i] = new Product(erpCProd.getCode(), erpCProd.getDescription(), new Long(erpCProd.getQuantity()), new Long(erpCProd.getAvailableQuantity()), new Double(erpCProd.getPrice()), new Double(erpCProd.getDiscount()));
			}
			
		} finally {
			client.shutdown();
		}
		return result;
	}
	
}