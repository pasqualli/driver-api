package br.com.tlf.tt.models;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "TBL_DRIVERS")
@NamedQuery(name = "DRIVERS.findAll", query = "SELECT d FROM Driver d")
@RegisterForReflection
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_id_seq")
    @SequenceGenerator(name = "driver_id_seq",
            sequenceName = "driver_seq",
            allocationSize = 1)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public Driver(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
