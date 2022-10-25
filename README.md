# Minimum example of REST API using Jersey in Jakarta Namespace with Swagger Docs

As most of the examples online don't utilize jakarta instead of javax namespace, I put together an example that puts these libraries together using the new `jakarta` namespace and JDK17.

## Build/Usage

Project is built with Java 17 SDK using Maven.

To compile:
```mvn clean compile```

To run:
```mvn clean compile exec:java```

The `exec-maven-plugin` takes care of calling the correct main class.

### Where do I find what?

The used port of the interface is set in `example.MainApplication`. In this case it is 8888.

* **WADL** is located at `http://localhost:8888/application.wadl` and should tell you about all remaining resources
* **API** is then located at `http://localhost:8888/example`, to quickly test it open `http://localhost:8888/example/result` in the browser.
* **OpenAPI docs** are located at `http://localhost:8888/openapi.json`
* **Swagger UI** is located at `http://localhost:8888/ui/`, per default opening the `openapi.json` configured in `/src/main/resources/webapp/ui/swagger-initializer.js`

## Setup hints

* The version of most packages are set in the `<properties>` in the beginning of the POM file
* When changing package names (or adding additional packages), make sure to update usages like in `MainApplication` in the init parameters of the ServletHolder as well
* Swagger offers separate artifacts for `jakarta` and `javax` namespaces, make sure to use the correct ones throughout

## Sources

This Example was built by putting together code from multiple examples and documentation (list may be incomplete):

Official Jersey documentation and examples:
* <https://eclipse-ee4j.github.io/jersey.github.io/documentation/latest/getting-started.html>
* <https://eclipse-ee4j.github.io/jersey.github.io/documentation/latest/async.html>
* <https://github.com/eclipse-ee4j/jersey/tree/2.36/examples>

Swagger documentation:
* <https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Getting-started>
* <https://swagger.io/docs/open-source-tools/swagger-ui/usage/installation/> section "Plain old HTML/CSS/JS (Standalone)"

Setting up Swagger as Jetty Servlet:
* <https://gist.github.com/nosmokingpistol/302c4c3ef30f183cf70e>