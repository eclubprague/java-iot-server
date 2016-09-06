package cz.eclub.iot.model.classes;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "SENSOR")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SensorEntity extends AbstractEntity {

    @Basic
    @Column(name = "_uuid" )
    private String _UUID;

    @Basic
    @Column(name="description")
    private String description;

    @Basic
    @Column(name="location")
    private String location;

    @OneToMany(mappedBy = "sensor")
    private Collection<SensorEntryEntity> sensorEntries;

}
