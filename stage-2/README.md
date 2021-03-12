# Stage-2

1. For Deployment, add label - version: v1

2. For services, add metadata
  labels:
    app: erpa
    service: erpa

3. Add Deployment erpb-v2 . Note only Deployment name is erpb-v2. But label is still app: erpb. 

Since Service erpb has selector app: erpb, k8s should start routing to both versions now (round robin).

4. Create gateway - ecomm-gateway.yaml  ; now accessible via gateway , but still round robin

5. Create destinatin rules - ecomm-destinationrules.yaml ; still round robin

6. Create all virtual services - ecomm-virtualservice.yaml

Now it should go to ERP-B (v1, and not v2) 

7. Now switch to ERP-B-v2; ecomm-virtualservice-erpb-v2.yaml

Now it should go to only v2

8. ecomm-virtualservice-erpb-v1-v2.yaml

Traffic split roughly 50-50 between ERP-B v1 and v2

Traffic split verified in Kiali

Also verified in Grafana -> istio Service Dashboard

[Go to Real Cool Heading section](screenshots/grafana-traffic-split.jpeg)


 