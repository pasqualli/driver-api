package br.com.tlf.tt.repositories;


import br.com.tlf.tt.models.Driver;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;

@RequiredArgsConstructor
@ApplicationScoped
public class DriverRepository {


    private final Mutiny.SessionFactory sf;

    public Uni<List<Driver>> getAll() {
        return sf.withTransaction((s,t) -> s
                .createNamedQuery("DRIVERS.findAll", Driver.class)
                .getResultList()
        );
    }

    public Uni<Driver> getSingle(Integer id) {
        return sf.withTransaction((s,t) -> s.find(Driver.class, id));
    }

    public Uni<Driver> create(Driver driver) {
        return sf.withTransaction((s,t) -> s.persist(driver))
                .replaceWith(() -> driver);
    }
}
