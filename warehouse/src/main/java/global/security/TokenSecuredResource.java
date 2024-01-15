package global.security;


import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;

@Path("/secured")
@RequestScoped
public class TokenSecuredResource {

    private static final String ISSUER = "https://amait.ro";

    @Inject
    JsonWebToken jwtToken;


    @GET
    @Path("generateToken")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String generateToken(@Context SecurityContext ctx) {

        JwtClaimsBuilder builderAdmin = Jwt.claims();
        builderAdmin.issuedAt(Instant.now().plus(-1, ChronoUnit.DAYS));
        builderAdmin.issuer(ISSUER);
        builderAdmin.upn("admin");
        builderAdmin.groups(new HashSet<>(Arrays.asList("User", "PowerUser", "Admin")));
        builderAdmin.expiresAt(Instant.now().plus(365, ChronoUnit.DAYS));

        JwtClaimsBuilder builderUser = Jwt.claims();
        builderUser.issuedAt(Instant.now().plus(-1, ChronoUnit.DAYS));
        builderUser.issuer(ISSUER);
        builderUser.upn("user");
        builderUser.groups(new HashSet<>(Arrays.asList("User")));
        builderUser.expiresAt(Instant.now().plus(365, ChronoUnit.DAYS));

        JwtClaimsBuilder builderPowerUser = Jwt.claims();
        builderPowerUser.issuedAt(Instant.now().plus(-1, ChronoUnit.DAYS));
        builderPowerUser.issuer(ISSUER);
        builderPowerUser.upn("poweruser");
        builderPowerUser.groups(new HashSet<>(Arrays.asList("PowerUser")));
        builderPowerUser.expiresAt(Instant.now().plus(365, ChronoUnit.DAYS));

        return "User \n" + builderUser.jws().innerSign().encrypt() + "\n" +
                "PowerUser \n" + builderPowerUser.jws().innerSign().encrypt() + "\n" +
                "Admin \n" + builderAdmin.jws().innerSign().encrypt();

    }
}