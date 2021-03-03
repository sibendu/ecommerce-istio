# ecomm_productservice       

Built SpringBoot

Expose REST API endpoint for product mapping to ERP backend 

POST http://localhost:8082/product/

1> Build 
docker build -t sibendudas/productservice .

2> Run the container with host network
docker run -p 8082:8082  sibendudas/productservice --name productservice

The endpoint is : POST http://localhost:8080/product/

3> Deploy to k8s: kubectl apply -f deploy_productservice.yaml