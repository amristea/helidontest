/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.helidon.examples.quickstart.mp;

import java.net.URI;
import java.net.URISyntaxException;
import javax.enterprise.context.RequestScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.ClientBuilder;

/**
 * A simple JAX-RS resource to greet you. Examples:
 *
 * Get default greeting message:
 * curl -X GET http://localhost:8080/greet
 *
 * Get greeting message for Joe:
 * curl -X GET http://localhost:8080/greet/Joe
 *
 * Change greeting
 * curl -X PUT http://localhost:8080/greet/greeting/Hola
 *
 * The message is returned as a JSON object.
 */

@Path("/sonarqube-techdebt")
@RequestScoped
public class SonarResource {

    /**
     * The greeting message provider.
     */
    private final SonarProvider sonarProvider;

    /**
     * Using constructor injection to get a configuration property.
     * By default this gets the value from META-INF/microprofile-config
     *
     * @param sonarConfig the configured greeting message
     */
    @Inject
    public SonarResource(SonarProvider sonarConfig) {
        this.sonarProvider = sonarConfig;
    }

    @SuppressWarnings("checkstyle:designforextension")
    @Path("/test")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getMessage(@PathParam("sonarqube-techdebt") String sonarTechdebt) {
        return createResponse(sonarTechdebt);
    }

    private JsonObject createResponse(String who) {
        String msg = String.format("%s", sonarProvider.getMessage());
        return Json.createObjectBuilder()
                .add("message", msg)
                .build();
    }

    @SuppressWarnings("checkstyle:designforextension")
    @Path("/get")
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getSonarMessage(@PathParam("sonarqube-techdebt") String sonarTechdebt) {
        return createSonarResponse(sonarTechdebt);
    }

    private JsonObject createSonarResponse(String who) {
        String msg = String.format("%s", sonarProvider.getSonarMessage());
        return Json.createObjectBuilder()
                .add("message", msg)
                .build();
    }

}