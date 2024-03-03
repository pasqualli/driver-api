package br.com.tlf.tt.controllers;

import br.com.tlf.tt.models.Driver;
import br.com.tlf.tt.repositories.DriverRepository;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Path("/drivers")
@RequiredArgsConstructor
@Slf4j
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DriverController {

    private final DriverRepository driverRepository;

    @GET
    public Uni<List<Driver>> get() {
        return driverRepository.getAll();
    }

    @GET
    @Path("{id}")
    public Uni<Driver> getSingle(@PathParam("id") Integer id) {
        return driverRepository.getSingle(id);
    }

    @POST
    public Uni<Driver> create(Driver driver) {
        if (driver == null || driver.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
       return driverRepository.create(driver);
    }
}
