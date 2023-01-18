package org.acme;

import io.quarkus.security.UnauthorizedException;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class AuthenticationIdentity {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationIdentity.class);
    private final JsonWebToken jwt;

    AuthenticationIdentity() {
        this.jwt = null;
    }

    @Inject
    public AuthenticationIdentity(JsonWebToken jwt) {
        if (!jwt.containsClaim("language") || !jwt.containsClaim("country") ||
                !jwt.containsClaim("sub")) {
            UnauthorizedException exception = new UnauthorizedException();
            log.warn("JsonWebToken not valid");
            throw exception;
        }
        this.jwt = jwt;
    }

    public String getSubject() {
        return jwt.getSubject();
    }
}