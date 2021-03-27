# Stage-1

## Objective

Product API fetching product details from two different ERP kernels - ERP-A and ERP-B

Request: Collection of products; sample request data in data.json 

Response: Collection of products; with details popuated

### Implementaiton 

ERP-A: product codes A0001, A0002  ( .\erp-A\src\main\java\com\example\ProductDB.java)

ERP-B: product codes B0001, B0002, B0003  ( .\erp-B\src\main\java\com\example\ProductDB.java) 

## Pre-Requisite

1. JDK (used 1.8)
2. Gradle (used 6.7.1 )
3. Docker-Desktop. Enabled Kubernetes (Adjust settings) 


## To run locally

Use script deploy-docker-local.bat

API accessible @ http://localhost:8080/product

Test using data.json - 

curl -x POST -H "Content-Type: applcation/json" -d @data.json http://localhost:8080/product

  
## To deploy+run on k8s

kubectl apply -f ecomm.yaml

Use port-forwarding (or NodePort) for product service to test

