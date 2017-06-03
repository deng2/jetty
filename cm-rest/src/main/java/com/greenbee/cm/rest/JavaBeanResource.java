package com.greenbee.cm.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/javabean")
public class JavaBeanResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JavaBean getJavaBean() {
        return new JavaBean();
    }
}
