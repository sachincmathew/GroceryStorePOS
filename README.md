# GroceryStorePOS
The application will expose a set of restful APIs that interface with any frontend GUI for a full fleged POS application for a grocery store.

## Docker Related
Was able to create containers for the MySQL and the application. However the network configuration pending.
Currently unable to deploy using Docker.

## Steps to run
1. Clone the repository.
2. Execute the script at GroceryStorePOS\Database\script.sql in MySQL
3. Run this command to execute the application: gradlew build && java -jar build/libs/grocery-store-pos-1.0.0.jar

## Accessing the API
Swagger API documentation has been implemented. After starting the application, go to http://localhost:8080/swagger-ui.html#/