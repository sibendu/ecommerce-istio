set PATH=%PATH%;C:\Incub\gradle-6.7.1\bin;

set JAVA_HOME=C:\Incub\jdk1.8.0_271

echo removing docker images

docker stop erpa && docker rm erpa 
docker stop erpb && docker rm erpb
docker stop erpbv2 && docker rm erpbv2
docker stop product  && docker rm product

echo building docker images

cd erp-A
docker build -t sibendudas/erpa .  
cd ../erp-B
docker build -t sibendudas/erpb .  
cd erp-B-v2
docker build -t sibendudas/erpbv2 .  
cd ../product
docker build -t sibendudas/product .  

cd ..

echo docker images built; running now

docker run --name erpa -d -p 8091:8091  sibendudas/erpa

docker run --name erpbv2 -d -p 8092:8092  sibendudas/erpbv2

docker run --name product -d --env ERP_A_URL=http://host.docker.internal:8091/product/ --env ERP_B_URL=http://host.docker.internal:8092/product/  -p 8080:8080  sibendudas/product