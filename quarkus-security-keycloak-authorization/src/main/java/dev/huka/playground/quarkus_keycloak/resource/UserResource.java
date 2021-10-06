/*
 * Copyright 2022 huka.dev
 */
package dev.huka.playground.quarkus_keycloak.resource;

import io.quarkus.security.identity.SecurityIdentity;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.jboss.resteasy.reactive.NoCache;

@Path("/api/users")
public class UserResource {
  @Inject SecurityIdentity identity;

  @GET
  @Path("/me")
  @NoCache
  public User me() {
    return new User(identity);
  }

  public static class User {

    private final String userName;

    User(SecurityIdentity identity) {
      this.userName = identity.getPrincipal().getName();
    }

    public String getUserName() {
      return userName;
    }
  }
}
