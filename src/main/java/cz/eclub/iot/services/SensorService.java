package cz.eclub.iot.services;

import cz.eclub.iot.model.DAO.SensorDao;
import cz.eclub.iot.model.classes.SensorEntity;
import cz.eclub.iot.utils.Utils;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("sensor")
public class SensorService {
    private SensorDao sensorDao = new SensorDao();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newSensor(SensorEntity sensor) {
        try {
            System.out.println(sensor);
            sensor.setUUID(Utils.escape(sensor.getUUID()));
            sensor.setDescription(Utils.escape(sensor.getDescription()));
            sensor.setLocation(Utils.escape(sensor.getLocation()));
            sensorDao.addNew(sensor);
            return Response.status(Response.Status.CREATED).entity(sensor).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllSensors() {
        try {
            List<SensorEntity> list = (List<SensorEntity>) sensorDao.getAll();
            GenericEntity<List<SensorEntity>> entity = new GenericEntity<List<SensorEntity>>(list) {
            };
            return Response.status(Response.Status.OK).entity(entity).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }


    @DELETE
    @Path("{UUID}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response removeSensor(@PathParam("UUID") String uuid) {
        try {
            SensorEntity sensorEntity = sensorDao.getByUUID(uuid);
            if (sensorEntity != null) {
                sensorDao.delete(sensorEntity);
                return Response.status(Response.Status.OK).build();

            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

    }

    @GET
    @Path("{UUID}/{LIMIT}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensorById(@PathParam("UUID") String uuid, @PathParam("LIMIT") String limit) {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
//        try {
//            SensorEntity sensorEntity = sensorDao.getByUUID(uuid);
//            if (sensorEntity == null) { // <-- toto se mi nelibi
//                return Response.status(Response.Status.NOT_FOUND).build();
//            }
//
//            Integer limitResults = Integer.parseInt(limit);
//            Collection<SensorEntity> list = sensorDao.getByUUIDLimit(uuid, limitResults);
//            GenericEntity<Collection<SensorEntity>> entity = new GenericEntity<Collection<SensorEntity>>(list) {
//            };
//            return Response.status(Response.Status.OK).entity(entity).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
//        }

    }

}
