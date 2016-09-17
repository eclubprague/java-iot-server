package cz.eclub.iot.services;

import cz.eclub.iot.model.DAO.HubDao;
import cz.eclub.iot.model.classes.HubEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;

@Path("hub")
public class Hub {
    private HubDao hubDao = new HubDao();

    @GET
    @Path("connected")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllHubs() {
        try {
            Collection<HubEntity> list = hubDao.getAllHubs();
            return Response.status(Response.Status.CREATED).entity(list).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }

    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerHub(HubEntity hubEntity) {
        try {
            hubDao.addNew(hubEntity);
            return Response.status(Response.Status.CREATED).entity(hubEntity).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }

    }

}
