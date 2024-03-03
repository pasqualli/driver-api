package br.com.tlf.tt.controllers;

import br.com.tlf.tt.models.Vehicle;
import br.com.tlf.tt.repositories.VehicleRepository;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Path("/vehicles")
@RequiredArgsConstructor
@Slf4j
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    @GET
    public Uni<List<Vehicle>> get() {
        return vehicleRepository.getAll();
    }

    @GET
    @Path("{id}")
    public Uni<Vehicle> getSingle(@PathParam("id") Integer id) {
        return vehicleRepository.getSingle(id);
    }

    @POST
    public Uni<Vehicle> create(Vehicle vehicle) {
        if (vehicle == null || vehicle.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        return vehicleRepository.create(vehicle);
    }

    @GET
    @Path("/driver/{driverId}")
    public Uni<Vehicle> getVehicleByDriverId(Integer driverId){
        return vehicleRepository.getVehicleByDriverId(driverId);
    }
}
