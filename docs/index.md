# Carpo Case Management

Welcome to the Carpo Case Management documentation. Carpo is a free and open source set of case management libraries. 

!!!note "Note"

	Carpo is an open source project this is released under the MIT Licence. We are always looking for help to improve the code, suggest new features or improve the docs. 
	
	Submit an issue or pull request on our [Github Repository](https://github.com/6point6/carpo) to help out.
	
## Module Structure

Since case management is such a large area the code is split into modules that allow flexibility on their use. While that can add complexity we do no intend to support every use case for every module from the start. If there is a use case for a module that is not available it should be simple matter to provide one.

If we wanted to create a finance module the structure would be:

Module name          | Description
---------------------|--------------------
carpo-finance-core   | Key interfaces and code of finance. 
carpo-finance-jdbi   | JDBI implementation of the persistence interfaces
carpo-finance-spring | Spring bean configuration to use the finance core with spring
carpo-finance-guice  | Guice configuration to use finance with guice
carpo-finance-rest   | Module containing the OpenAPI spec for the rest api and the DTO classes
carpo-finance-spring-rest | Spring controllers to expose finance with a rest api
carpo-finance-rest-sdk | Module consumer code for the rest api
carpo-finance-grpc   | Module with `.proto` files and generated implementation classes
carpo-finance-spring-grpc | Spring controllers to expose the grpc api
carpo-finance-camunda | Module to provide a Camunda external worker to be used with Camunda

When a new module is added the only requirement is the `core` library. Everything else can be added when needed.

### Alternative API's

The API standards will be defined later and will be enforced for all core API's. If an community API is built that does not conform to the standard we cannot import it but we will provide a list of links to community API's.

## Building

Carpo uses Java 11 and is built with Maven

```sh
$ ./mvnw clean install
```