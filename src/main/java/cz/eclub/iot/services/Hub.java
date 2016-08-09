package cz.eclub.iot.services;

import cz.eclub.iot.model.DAO.HubDao;
import cz.eclub.iot.model.classes.HubEntity;
import cz.eclub.iot.model.classes.MessageEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("hub")
public class Hub {
    private HubDao hubDao = new HubDao();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MessageEntity getHubById(@PathParam("id") String id) {
        return new MessageEntity(id);
    }

    @GET
    @Path("connected")
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<HubEntity> getAllHubs() {
        ArrayList<HubEntity> list = (ArrayList<HubEntity>) hubDao.getAllHubs();
        return list;
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerHub(HubEntity hubEntity) {
        if(hubDao.addNew(hubEntity)){
            return Response.status(201).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

}
