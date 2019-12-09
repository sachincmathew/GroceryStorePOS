# GroceryStorePOS
The application will expose a set of restful APIs that interface with any frontend GUI for a full fleged POS application for a grocery store.

# Running the application on laptop
./gradlew build && java -jar build/libs/grocery-store-pos-1.0.0.jar

# Creating Docker Container
$ docker build --build-arg JAR_FILE=build/libs/*.jar -t grocery_store_pos .

# Running the GroceryStorePOS container
sudo docker run -p 8080:8080 -t grocery_store_pos

# Accessing the API
 Swagger API documentation has been implemented. After starting the application, go to http://localhost:8080/swagger-ui.html#/

