package sd.example;

import java.util.ArrayList;
import java.util.Collection;

import com.example.ERPServiceGrpc;
import com.example.Product;
import com.example.ProductQuery;
import com.example.ProductResponse;

import io.grpc.stub.StreamObserver;

public class ERPCServiceImpl extends ERPServiceGrpc.ERPServiceImplBase {
	
	final static Collection<Product> products = new ArrayList<Product>();
	static {

		products.add(Product.newBuilder().setCode("D00001").setDescription("Item D0001").setQuantity(0).setPrice(3000)
				.setDiscount(7).setAvailableQuantity(100).build());
		products.add(Product.newBuilder().setCode("D00002").setDescription("Item D0002").setQuantity(0).setPrice(4000)
				.setDiscount(8).setAvailableQuantity(100).build());
		products.add(Product.newBuilder().setCode("D00003").setDescription("Item D0003").setQuantity(0).setPrice(5000)
				.setDiscount(9).setAvailableQuantity(100).build());
		products.add(Product.newBuilder().setCode("D00004").setDescription("Item D0004").setQuantity(0).setPrice(6000)
				.setDiscount(10).setAvailableQuantity(100).build());
		products.add(Product.newBuilder().setCode("D00005").setDescription("Item D0005").setQuantity(0).setPrice(7000)
				.setDiscount(11).setAvailableQuantity(100).build());
		products.add(Product.newBuilder().setCode("D00006").setDescription("Item D0006").setQuantity(0).setPrice(8000)
				.setDiscount(12).setAvailableQuantity(100).build());
		products.add(Product.newBuilder().setCode("D00007").setDescription("Item D0007").setQuantity(0).setPrice(9000)
				.setDiscount(11).setAvailableQuantity(100).build());
		products.add(Product.newBuilder().setCode("D00008").setDescription("Item D0008").setQuantity(0).setPrice(10000)
				.setDiscount(10).setAvailableQuantity(100).build());
		products.add(Product.newBuilder().setCode("D00009").setDescription("Item D0009").setQuantity(0).setPrice(11000)
				.setDiscount(9).setAvailableQuantity(100).build());
		products.add(Product.newBuilder().setCode("D000010").setDescription("Item D00010").setQuantity(0)
				.setPrice(12000).setDiscount(8).setAvailableQuantity(100).build());
	}

	public void getProduct(ProductQuery request, StreamObserver<ProductResponse> responseObserver) {
		responseObserver.onNext(checkProduct(request));
		responseObserver.onCompleted();
	}

	private ProductResponse checkProduct(ProductQuery request) {
		System.out.println("Invoked getProducts() over gRPC = "+request.getCodesCount());
		
		ArrayList<Product> matchingProducts = new ArrayList<>();
		
		for (int k = 0; k < request.getCodesCount(); k++) {
			
			boolean found = false;
			
			//System.out.println("Matching for code = "+ request.getCodes(k));
			for (Product p : this.products) {
				//System.out.println(p.getCode());
				if (p.getCode().equals(request.getCodes(k))) {
					//System.out.println("Found match");
					matchingProducts.add(p);
					found = true;
				}
			}
			
			if(!found) {
				matchingProducts.add(Product.newBuilder().setCode(request.getCodes(k)).setDescription("NOT_FOUND").build());
			}
		}
		ProductResponse response = ProductResponse.newBuilder().addAllProducts(matchingProducts).build();
		System.out.println("No of match = "+response.getProductsCount());
		return response;
	}

}
