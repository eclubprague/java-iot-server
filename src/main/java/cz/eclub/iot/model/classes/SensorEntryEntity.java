package cz.eclub.iot.model.classes;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SENSOR_ENTRY")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorEntryEntity extends AbstractEntity {

    @Basic
    @Column(name="value")
    private String value;

    @Basic
    @Column(name="unit")
    private String unit;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dtCreated")
    private Date dtCreated;

    @ManyToOne
    @JoinColumn(name = "sensorID", referencedColumnName = "ID", nullable = false)
    private SensorEntity sensor;
}
