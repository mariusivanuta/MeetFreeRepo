# amait

This project uses Quarkus somewhow similiar to Spring - it uses Spring Data Jpa for the repository layer.

Mapping to exposed DTO is handled by Mapstruct.

To test RBAC make sure that first generate the jwt needed token from localhost:8080/warehouse/secured/generateToken

Use ThirdPartyResource for a demo of RBAC

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

Swagger UI with no properties customized or any OpenApi documentation is available at /q/swagger-ui


> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Related Guides

- JDBC Driver - H2 ([guide](https://quarkus.io/guides/datasource)): Connect to the H2 database via JDBC
- Hibernate Validator ([guide](https://quarkus.io/guides/validation)): Validate object properties (field, getter) and
  method parameters for your beans (REST, CDI, JPA)
- RESTEasy Classic ([guide](https://quarkus.io/guides/resteasy)): REST endpoint framework implementing JAX-RS and more
- SmallRye JWT ([guide](https://quarkus.io/guides/security-jwt)): Secure your applications with JSON Web Token
- Quarkus Extension for Spring Data JPA API ([guide](https://quarkus.io/guides/spring-data-jpa)): Use Spring Data JPA
  annotations to create your data access layer
- SmallRye JWT Build ([guide](https://quarkus.io/guides/security-jwt-build)): Create JSON Web Token with SmallRye JWT
  Build API


