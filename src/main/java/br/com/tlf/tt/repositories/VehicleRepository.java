package br.com.tlf.tt.repositories;

import br.com.tlf.tt.models.Vehicle;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;

@RequiredArgsConstructor
@ApplicationScoped
public class VehicleRepository {

    private final Mutiny.SessionFactory sf;

    public Uni<List<Vehicle>> getAll() {
        return sf.withTransaction((s,t) -> s
                .createNamedQuery("VEHICLES.findAll", Vehicle.class)
                .getResultList()
        );
    }

    public Uni<Vehicle> getVehicleByDriverId(Integer driverId) {
        return sf.withTransaction((s,t) -> s
                .createNamedQuery("VEHICLES.findByDriverId", Vehicle.class)
                .setParameter(1, driverId)
                .getSingleResult()
        );
    }

    public Uni<Vehicle> getSingle(Integer id) {
        return sf.withTransaction((s,t) -> s.find(Vehicle.class, id));
    }

    public Uni<Vehicle> create(Vehicle vehicle) {
        return sf.withTransaction((s,t) -> s.persist(vehicle))
                .replaceWith(() -> vehicle);
    }
}
