FROM openjdk:8-jdk-alpine
VOLUME /tmp

RUN apk update \
	&& apk upgrade \
	&& apk add git

RUN git clone https://github.com/sachincmathew/GroceryStorePOS.git \
	&& cd GroceryStorePOS \
	&& git fetch \
	&& git checkout fordocker \
	&& git status \
	&& ./gradlew build \
	&& cp build/libs/grocery-store-pos-1.0.0.jar .. \
	&& rm -rf /GroceryStorePOS

CMD ["java","-jar","grocery-store-pos-1.0.0.jar"]
