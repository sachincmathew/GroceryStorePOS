# GroceryStorePOS
The application will expose a set of restful APIs that interface with any frontend GUI for a full fleged POS application for a grocery store.

## Steps to run
1. Create new network (subnet machine dependent)
	sudo docker network create --driver bridge --subnet 172.18.0.0/16 net1
2. Run MySQL (mydb)
	sudo docker run -d -p 3306:3306 --name=mydb --net net1 --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=grocery_store_pos" mysql
3. Execute table creation scripts
	sudo docker exec -i mydb mysql -uroot -proot grocery_store_pos < Database/script.sql
4. Create StoreAPI image
	sudo docker image build -t storeapi .
5. Run storeapi
	sudo docker container run --net net1 --name storeapi -p 8080:8080 -d storeapi

## Accessing the API
Swagger API documentation has been implemented. After starting the application, go to http://localhost:8080/swagger-ui.html#/