package cz.eclub.iot.model.classes;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "SENSOR")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SensorEntity extends AbstractEntity implements Serializable {

    @Basic
    @Column(name = "_uuid" )
    private String _UUID;

    @Basic
    @Column(name="description")
    private String description;

    @Basic
    @Column(name="location")
    private String location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sensor", orphanRemoval=true, fetch = FetchType.EAGER)
    private Collection<SensorEntryEntity> sensorEntries;

}
