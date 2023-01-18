package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.JwtSecurity;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceImplTest {

    @Test
    @TestSecurity(user = "testUser", roles = {"arole"})
    @JwtSecurity(
            claims = {
                    @Claim(key = "language", value = "pt"),
                    @Claim(key = "country", value = "PT"),
                    @Claim(key = "exp", value = "9999999999"),
                    @Claim(key = "iat", value = "0"),
                    @Claim(key = "azp", value = "test"),
                    @Claim(key = "sub", value = "testUser"),
                    @Claim(key = "realm_access", value = "{\"roles\":[\"arole\"]}")
    })
    public void testHelloEndpoint() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello testUser"));
    }

}