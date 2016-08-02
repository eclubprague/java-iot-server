package cz.eclub.iot;

import cz.eclub.iot.Model.DAO.HubDAO;
import cz.eclub.iot.Model.classes.HubEntity;
import cz.eclub.iot.Model.classes.MessageEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("hub")
public class Hub {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MessageEntity getHubById(@PathParam("id") String id) {
        return new MessageEntity(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<MessageEntity> getAllHubs() {
        ArrayList<MessageEntity> list = new ArrayList<MessageEntity>();
        list.add(new MessageEntity("1"));
        list.add(new MessageEntity("2"));
        list.add(new MessageEntity("3"));

        HubDAO hubDAO = new HubDAO();

        HubEntity hubEntity = new HubEntity();
        hubEntity.setLocation("The Blox");
        hubEntity.setName("Intel Edison");
        hubEntity.setUuid(0L);

        hubDAO.addNew(hubEntity);

        return list;
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerHub(MessageEntity messageEntity) {

        HubDAO hubDAO = new HubDAO();

        HubEntity hubEntity = new HubEntity();
        hubEntity.setLocation("The Blox");
        hubEntity.setName("Intel Edison");
        hubEntity.setUuid(0L);

        hubDAO.addNew(hubEntity);

        return Response.status(201).build();
    }

}
