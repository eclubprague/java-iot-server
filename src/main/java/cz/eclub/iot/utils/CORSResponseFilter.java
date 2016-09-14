package cz.eclub.iot.utils;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;

public class CORSResponseFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {

        containerResponseContext.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
        containerResponseContext.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        containerResponseContext.getHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        containerResponseContext.getHeaders().putSingle("Access-Control-Allow-Headers", "Content-Type, Accept");
    }
}
