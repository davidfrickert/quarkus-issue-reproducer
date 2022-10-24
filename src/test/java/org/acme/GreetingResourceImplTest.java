package org.acme;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class GreetingResourceImplTest {

    @Test
    @TestSecurity(user = "user", roles = {"a"})
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(403);
    }

}