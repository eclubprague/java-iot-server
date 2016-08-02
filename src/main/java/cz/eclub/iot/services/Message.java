package cz.eclub.iot.services;

import cz.eclub.iot.model.DAO.MessageDao;
import cz.eclub.iot.model.classes.MessageEntity;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("message")
public class Message {
    private MessageDao messageDao = new MessageDao();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerHub(MessageEntity messageEntity) {
        if(messageDao.addNew(messageEntity)){
            return Response.status(200).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();


    }
}
