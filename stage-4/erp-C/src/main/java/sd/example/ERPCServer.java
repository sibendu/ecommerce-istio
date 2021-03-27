package sd.example;

import com.example.*;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.*;

public class ERPCServer {

	private final int port;
	private final Server server;

	public static void main(String[] args) throws Exception {
		ERPCServer server = new ERPCServer(8093);
		server.start();
		server.blockUntilShutdown();
	}

	public ERPCServer(int port) throws IOException {
		this(ServerBuilder.forPort(port), port);
	}

	public ERPCServer(ServerBuilder<?> serverBuilder, int port) {
		this.port = port;
		server = serverBuilder.addService(new ERPCServiceImpl()).build();
	}

	/** Start serving requests. */
	public void start() throws IOException {
		server.start();
		System.out.println("Server started, listening on " + port);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// Use stderr here since the logger may has been reset by its JVM shutdown hook.
				System.err.println("*** shutting down gRPC server since JVM is shutting down");
				ERPCServer.this.stop();
				System.err.println("*** server shut down");
			}
		});
	}

	/** Stop serving requests and shutdown resources. */
	public void stop() {
		if (server != null) {
			server.shutdown();
		}
	}

	/**
	 * Await termination on the main thread since the grpc library uses daemon
	 * threads.
	 */
	private void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}
}