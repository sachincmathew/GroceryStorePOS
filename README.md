# GroceryStorePOS
The application will expose a set of restful APIs that interface with any frontend GUI for a full fleged POS application for a grocery store.

# Running the application on laptop
./gradlew build && java -jar build/libs/grocery_store_pos-1.0.0.jar

# Creating Docker Container
$ docker build --build-arg JAR_FILE=build/libs/*.jar -t grocery_store_pos .

# Running the GroceryStorePOS container
sudo docker run -p 8080:8080 -t grocery_store_pos

# Accessing the API
http://localhost:8080/

