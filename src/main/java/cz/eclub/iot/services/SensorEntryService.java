package cz.eclub.iot.services;

import cz.eclub.iot.model.DAO.SensorDao;
import cz.eclub.iot.model.DAO.SensorEntryDao;
import cz.eclub.iot.model.classes.SensorEntity;
import cz.eclub.iot.model.classes.SensorEntryEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;

@Path("sensor")
public class SensorEntryService {
    private SensorEntryDao sensorEntryDao = new SensorEntryDao();
    private SensorDao sensorDao = new SensorDao();

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<SensorEntryEntity> getAllSensors() {
        ArrayList<SensorEntryEntity> list = (ArrayList<SensorEntryEntity>) sensorEntryDao.getAll();
        return list;
    }

    @POST
    @Path("{UUID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newSensor(@PathParam("UUID") String uuid, SensorEntryEntity sensorEntry) {
        System.out.println(uuid);
        System.out.println(sensorEntry);

        SensorEntity sensor = sensorDao.getByUUID(uuid);

        if (sensor != null) {
            sensorEntry.setSensor(sensor);
            sensorEntryDao.addNew(sensorEntry);
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @POST
    @Path("getByUnit/{unit}/{limit}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<SensorEntryEntity> getByUnit(@PathParam("unit") String unit, @PathParam("limit") String limit) {
        System.out.println(unit);
        System.out.println(limit);

        Integer limitResults = Integer.parseInt(limit);
        Collection<SensorEntryEntity> sensorEntryCollection = sensorEntryDao.getByUUIDLimit(unit, limitResults);
        return sensorEntryCollection;
    }


}
