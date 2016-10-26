package cz.eclub.iot.services;

import cz.eclub.iot.model.DAO.SensorDao;
import cz.eclub.iot.model.DAO.SensorEntryDao;
import cz.eclub.iot.model.classes.SensorEntity;
import cz.eclub.iot.model.classes.SensorEntryEntity;
import cz.eclub.iot.utils.Utils;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("sensor_entry")
public class SensorEntryService {
    private SensorEntryDao sensorEntryDao = new SensorEntryDao();
    private SensorDao sensorDao = new SensorDao();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllSensorEntries() {
        try {
            Collection<SensorEntryEntity> list = sensorEntryDao.getAll();
            return Response.status(Response.Status.OK).entity(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("{UUID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response newSensor(@PathParam("UUID") String uuid, Collection<SensorEntryEntity> sensorEntryCollection) {
        try {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Recieved message: " + uuid + "!");
            SensorEntity sensor = sensorDao.getByUUID(uuid);
            if (sensor != null) {
                for (SensorEntryEntity sensorEntry : sensorEntryCollection) {
                    sensorEntry.setUnit(Utils.escape(sensorEntry.getUnit()));
                    sensorEntry.setValue(Utils.escape(sensorEntry.getValue()));
                    sensorEntry.setSensor(sensor);
                    sensorEntryDao.addNew(sensorEntry);
                }
                return Response.ok().build();
            }
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{uuid}/{limit}/{unit}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByUnit(@PathParam("uuid") String uuid, @PathParam("limit") String limit, @PathParam("unit") String unit) {
        try {
            SensorEntity sensorEntity = sensorDao.getByUUID(uuid);
            if (sensorEntity != null) {
                Integer limitResults = Integer.parseInt(limit);
                Collection<SensorEntryEntity> sensorEntryCollection = sensorEntryDao.getByIdUnitLimit(sensorEntity.getId(), unit, limitResults);
                return Response.status(Response.Status.OK).entity(sensorEntryCollection).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{uuid}/{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByUnit(@PathParam("uuid") String uuid, @PathParam("limit") String limit) {
        try {
            SensorEntity sensorEntity = sensorDao.getByUUID(uuid);
            if (sensorEntity != null) {
                Integer limitResults = Integer.parseInt(limit);
                Collection<SensorEntryEntity> sensorEntryCollection = sensorEntryDao.getByIdLimit(sensorEntity.getId(), limitResults);
                return Response.status(Response.Status.OK).entity(sensorEntryCollection).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByUnit(@PathParam("uuid") String uuid) {
        try {
            SensorEntity sensorEntity = sensorDao.getByUUID(uuid);
            if (sensorEntity != null) {
                return Response.status(Response.Status.OK).entity(sensorEntity.getSensorEntries()).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }


}
