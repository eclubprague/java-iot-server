package cz.eclub.iot.services;

import cz.eclub.iot.model.DAO.SensorDao;
import cz.eclub.iot.model.classes.SensorEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("sensor")
public class SensorService {
    private SensorDao sensorDao = new SensorDao();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newSensor(SensorEntity sensor) {
        System.out.println(sensor);
        if (sensorDao.addNew(sensor)) {
            return Response.status(200).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<SensorEntity> getAllSensors() {
        ArrayList<SensorEntity> list = (ArrayList<SensorEntity>) sensorDao.getAll();
        return list;
    }


    @DELETE
    @Path("{UUID}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response removeSensor(@PathParam("UUID") String uuid) {
        SensorEntity sensorEntity = sensorDao.getByUUID(uuid);
        if(sensorEntity != null) {
            if(sensorDao.delete(sensorEntity)) {
                return Response.status(200).build();
            }else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        }

        return Response.status(Response.Status.FORBIDDEN).build();
    }
    
    @GET
    @Path("{UUID}/{LIMIT}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<SensorEntity> getSensorById(@PathParam("UUID") String uuid, @PathParam("LIMIT") String limit) {

        Integer limitResults = Integer.parseInt(limit);

        ArrayList<SensorEntity> list = (ArrayList<SensorEntity>) sensorDao.getByUUIDLimit(uuid, limitResults);
        return list;
    }

}
