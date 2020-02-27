# Carpo Case Management Framework

![Master build](https://github.com/6point6/carpo/workflows/Master%20build/badge.svg?branch=master)
[![Documentation Status](https://readthedocs.org/projects/carpo/badge/?version=latest)](https://carpo.readthedocs.io/en/latest/?badge=latest)

## What is Carpo?

Case management is common requirement that teams have to build again and again. Carpo is here to provide some easy to use tools to speed up the development of the Case Management applications.

At the moment this is a work in progress. If you want to help please read the [Contributing Docs](CONTRIBUTING.md)

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

```shell script
./mvnw clean install
```

### Testing

Carpo splits its test runs into unit and functional tests. 

For unit tests only

```shell script
./mvnw clean test
```

For all tests

```shell script
./mvnw clean test -Pit
```

## Docs

The docs are stored in the [docs](docs) directory and can be built with [mkdocs](https://docs.readthedocs.io/en/stable/intro/getting-started-with-mkdocs.html). The will be deployed to read the docs as part of the pipeline.

To install the tools run

```shell script
pip install mkdocs
```

To serve the docs locally run `mkdocs serve` from the root directory.
