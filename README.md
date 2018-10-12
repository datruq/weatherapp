# weatherapp

Case study for weather average metrics using OpenWeatherMap API

### Prerequisites

* Java 8
* Gradle 4+
* Git
* IntelliJ IDE
* Lombok

### Installing

First we need to clone the project
````git clone https://github.com/datruq/weatherapp.git````

When you already have the project con your local machine, just open it with IntelliJ IDE and import all the dependencies with Gradle

**Note:** We need to enable annotation processing: Setting/Build, Execution, Deployment/Compiler/Annotation Processors check "Enable annotation processing"

![alt text](https://github.com/datruq/weatherapp/blob/master/img/lombok_enable_annotation.png)

### Building and running this application

1. Open a command line window or terminal.
2. Navigate to the root directory of the project, where the build.gradle resides.
3. Compile the project: ```gradle clean build ```.
4. Change into the target directory: cd build/libs
5. You should see the file name: weather-app-1.0.jar.
6. Execute the JAR: ``` java -jar weather-app-1.0.jar```.
7. The application should be available at http://localhost:8080/weather/data.


## Running the tests

1. Open a command line window or terminal.
2. Navigate to the root directory of the project, where the build.gradle resides.
3. Compile the project: ```gradle test --info ```.
4. Change into the target directory: cd build/reports/tests/test.
5. You should see the file name: index.html.
6. Open it with you favorite browser

### Break down into end to end tests


```

```

## Deployment

Add additional notes about how to deploy this on a live system

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

