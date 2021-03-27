set PATH=%PATH%;C:\Incub\gradle-6.7.1\bin;

set JAVA_HOME=C:\Incub\jdk1.8.0_271

echo removing docker images

docker stop mysqldberpb && docker rm mysqldberpb
docker stop erpa && docker rm erpa 
docker stop erpb && docker rm erpb
docker stop erpbv2 && docker rm erpbv2
docker stop product  && docker rm product
docker stop erpc && docker rm erpc

echo building docker images

cd erp-A
docker build -t sibendudas/erpa .  
cd ../erp-B
docker build -t sibendudas/erpb .  
cd ../erp-B-v2
docker build -t sibendudas/erpbv2 .  
cd ../product
docker build -t sibendudas/product .  
cd ../mysqldb-erp-B
docker build -t sibendudas/mysqldberpb .  
cd ../erp-C
docker build -t sibendudas/erpc .

cd ..

echo docker images built; running now

docker run --name mysqldberpb -d -p 3306:3306  sibendudas/mysqldberpb

docker run --name erpa -d -p 8091:8091  sibendudas/erpa

docker run --name erpbv2 -d --env MYSQL_HOST=host.docker.internal -p 8092:8092  sibendudas/erpbv2

docker run --name erpc -d -p 8093:8093  sibendudas/erpc

docker run --name product -d --env ERP_A_URL=http://host.docker.internal:8091/product/ --env ERP_B_URL=http://host.docker.internal:8092/product/  --env ERP_C_HOST=host.docker.internal  --env ERP_C_PORT=8093 -p 8080:8080  sibendudas/product