# Minimum example of REST API using Jersey in Jakarta Namespace with Swagger Docs

As most of the examples online don't utilize jakarta instead of javax namespace, I put together an example that puts these libraries together using the new `jakarta` namespace and JDK17.

## Build/Usage

Project is built with Java 17 SDK using Maven.

To compile:
```mvn clean compile```

To run:
```mvn clean compile exec:java```

The `exec-maven-plugin` takes care of calling the correct main class.

### Where does it run?

The used port of the interface is set in `MainApplication`. In this case it is 8888.

* WADL is located at `http://localhost:8888/application.wadl` and should tell you about all remaining resources
* API is then located at `http://localhost:8888/example`, to quickly test it open `http://localhost:8888/example/result` in the browser.
* OpenAPI docs are located at `http://localhost:8888/openapi.json`
* Swagger UI is located at `http://localhost:8888/ui/`, per default opening the `openapi.json` configured in `/src/main/resources/webapp/ui/swagger-initializer.js`

## Setup hints

* 

## Sources

Example was built by putting together code from multiple examples and documentation (list may be incomplete):

