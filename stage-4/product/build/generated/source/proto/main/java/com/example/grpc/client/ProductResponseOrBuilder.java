// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: product.proto

package com.example.grpc.client;

public interface ProductResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:erpc.ProductResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .erpc.Product products = 1;</code>
   */
  java.util.List<com.example.grpc.client.Product> 
      getProductsList();
  /**
   * <code>repeated .erpc.Product products = 1;</code>
   */
  com.example.grpc.client.Product getProducts(int index);
  /**
   * <code>repeated .erpc.Product products = 1;</code>
   */
  int getProductsCount();
  /**
   * <code>repeated .erpc.Product products = 1;</code>
   */
  java.util.List<? extends com.example.grpc.client.ProductOrBuilder> 
      getProductsOrBuilderList();
  /**
   * <code>repeated .erpc.Product products = 1;</code>
   */
  com.example.grpc.client.ProductOrBuilder getProductsOrBuilder(
      int index);
}
