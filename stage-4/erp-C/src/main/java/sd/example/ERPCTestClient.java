package sd.example;

import com.google.common.annotations.VisibleForTesting;
import com.google.protobuf.Message;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import com.example.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Sample client code that makes gRPC calls to the server.
 */
public class ERPCTestClient {
	private final ManagedChannel channel;
	private final ERPServiceGrpc.ERPServiceBlockingStub blockingStub;
	private final ERPServiceGrpc.ERPServiceStub asyncStub;

	public static void main(String[] args) throws InterruptedException {

		ERPCTestClient client = new ERPCTestClient("localhost", 8093); 
		try {
			ProductQuery query = ProductQuery.newBuilder().addCodes("D00001").addCodes("D00005").build();
			ProductResponse response = client.getProducts(query);
			System.out.println("Response = "+response.toString());
		} finally {
			client.shutdown();
		}
	}
	/** Construct client for accessing RouteGuide server at {@code host:port}. */
	public ERPCTestClient(String host, int port) {
		this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
	}

	/**
	 * Construct client for accessing RouteGuide server using the existing channel.
	 */
	public ERPCTestClient(ManagedChannelBuilder<?> channelBuilder) {
		channel = channelBuilder.build();
		blockingStub = ERPServiceGrpc.newBlockingStub(channel);
		asyncStub = ERPServiceGrpc.newStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
	
	public ProductResponse getProducts(ProductQuery query) {
		System.out.println("Invoked getProducts = "+query.getCodesCount());
		ProductResponse resp = null;
		try {
			resp = blockingStub.getProduct(query);
		} catch (StatusRuntimeException e) {
			System.out.println("RPC failed: " + e.getStatus());
			return null;
		}
		return resp;
	}
}
