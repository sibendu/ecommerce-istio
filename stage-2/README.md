# Stage-2

## Objective

- Enable Istio 

- Add v2 of service for ERP-B. This supports more product codes - B0001 to B0006  (v1 had only B0001-B0003)

## Steps

1. For Deployment, add label - version: v1

2. For services, add metadata
  labels:
    app: erpa
    service: erpa

3. Add Deployment erpb-v2 . Note only Deployment name is erpb-v2. But label is still app: erpb. 

Since Service erpb has selector app: erpb, k8s should start routing to both versions now (round robin).

4. Create gateway - ecomm-gateway.yaml  ; now accessible via gateway , but still round robin

5. Create destination rules - ecomm-destinationrules.yaml ; still round robin

6. Create all virtual services - ecomm-virtualservice.yaml

Now it should go to ERP-B (v1, and not v2) 

7. ecomm-virtualservice-erpb-v1-v2.yaml

Traffic should be split roughly 50-50 between ERP-B v1 and v2 (verified thru Kiali and Grafana, screenshots below)

8. Now let us retire v1 and switch completely to ERP-B-v2; ecomm-virtualservice-erpb-v2.yaml

Now all traffic should go to only v2


### Verification of Step 7

After Step 7, traffic should be split roughly 50-50 between ERP-B v1 and v2

Traffic split verified in Kiali

![Traffic Split in Kiali](screenshots/kiali-traffic-split.jpeg)

Also same can be verified in Grafana -> istio Service Dashboard

![Traffic Split in Grafana](screenshots/grafana-traffic-split.jpeg)


 
