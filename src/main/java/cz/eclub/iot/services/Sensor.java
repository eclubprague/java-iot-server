package cz.eclub.iot.services;

import cz.eclub.iot.model.DAO.SensorDao;
import cz.eclub.iot.model.classes.SensorEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("message")
public class Sensor {
    private SensorDao sensorDao = new SensorDao();

    /**
     * Consumes sensor entity and saves it into DB
     *
     * @param sensor
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerHub(SensorEntity sensor) {
        if (sensorDao.addNew(sensor)) {
            return Response.status(200).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    /**
     * returns records for all sensors
     *
     * @return
     */
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<SensorEntity> getAllSensors() {
        ArrayList<SensorEntity> list = (ArrayList<SensorEntity>) sensorDao.getAll();
        return list;
    }

    /**
     * returns records for sensor specified by uuid
     *
     * @param uuid
     * @return
     */
    @GET
    @Path("{UUID}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<SensorEntity> getSensorById(@PathParam("UUID") String uuid) {
        ArrayList<SensorEntity> list = (ArrayList<SensorEntity>) sensorDao.getByUUID(uuid);
        return list;
    }
}
