# Stage-1



## Pre-Requisite

JDK (used 1.8)
Gradle (used 6.7.1 )
Install Docker-Desktop. Enable Kubernetes (Adjust settings) 


## To run locally

Use script deploy-docker-local.bat

API accessible @ http://localhost:8080/product

Test using data.json - 

curl -x POST -H "Content-Type: applcation/json" -d @data.json http://localhost:8080/product

  
## To deploy+run on k8s

kubectl apply -f ecomm.yaml

Use port-forwarding , or NodePort to test

