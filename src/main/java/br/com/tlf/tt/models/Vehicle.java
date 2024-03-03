package br.com.tlf.tt.models;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "TBL_VEHICLES")
@NamedQueries(
        {
                @NamedQuery(name = "VEHICLES.findAll", query = "SELECT v from Vehicle v"),
                @NamedQuery(name = "VEHICLES.findByDriverId", query = "SELECT v from Vehicle v where v.driverId=?1")
        }
)
@RegisterForReflection
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_id_seq")
    @SequenceGenerator(name = "vehicle_id_seq",
            sequenceName = "vehicle_seq",
            allocationSize = 1)
    private Integer id;
    public String licencePlate;
    public String brand;
    public String model;
    public int driverId;

}
