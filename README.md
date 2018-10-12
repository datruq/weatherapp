# WeatherApp

Case study for weather average metrics using OpenWeatherMap API

## Prerequisites

* Java 8
* Gradle 4+
* Git
* IntelliJ IDE
* Lombok

## Installing

First we need to clone the project

````git clone https://github.com/datruq/weatherapp.git````

When it finished clonning and have it in your local machine, just open it with IntelliJ IDE and import all the dependencies with Gradle

**Note:** We need to enable annotation processing: Setting/Build, Execution, Deployment/Compiler/Annotation Processors check "Enable annotation processing"

![alt text](https://github.com/datruq/weatherapp/blob/master/img/lombok_enable_annotation.png)

## Building and running this application

1. Open a command line window or terminal.
2. Navigate to the root directory of the project, where the build.gradle resides.
3. Compile the project: ```gradle clean build ```.
4. Change into the target directory: cd build/libs
5. You should see the file name: weather-app-1.0.jar.
6. Execute the JAR: ``` java -jar weather-app-1.0.jar```.
7. The application should be available at http://localhost:8080/weather/data.

## REST API overviiew

With this WeatherApp we can get:
* Average of daily (6h-18h) temperature, in Celsius, for the following 3 days.
* Average of nightly (18h-6h) temperature, in Celsius, for the following 3 days.
* Average of pressure for the following 3 days.

**Get by default Medellín forcast weather.**

````curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/weather/data````

**Get any city forcast weather.**

````curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/weather/data/{city}````

### Targeting the REST API with Postman
Alternatively to curl, you can use Postman to target the REST API.


**Postman with any city {city}**

![alt text](https://github.com/datruq/weatherapp/blob/master/img/postman_anycity.jpg)

**Postman with default city**

![alt text](https://github.com/datruq/weatherapp/blob/master/img/postman_defaultcity.jpg)


## Running the tests

1. Open a command line window or terminal.
2. Navigate to the root directory of the project, where the build.gradle resides.
3. Compile the project: ```gradle test --info ```.
4. Change into the target directory: cd build/reports/tests/test.
5. You should see the file name: index.html.
6. Open it with you favorite browser

## Built With

* [Spring](https://spring.io/) - The web framework used
* [Gradle](https://gradle.org/) - Dependency Management
* [Swagger](https://swagger.io/) - API usage docs
* [Lombok](https://projectlombok.org/) - Java library to simplify the development 
* [Jackson](https://github.com/FasterXML/jackson) Java json parser
* [Junit](http://junit.org/junit4/) Java unit testing framework
* [Mockito](https://site.mockito.org/) Java Mocking framework


## Authors

* **David Trujillo** - *Initial work* - [datruq](https://github.com/datruq)

