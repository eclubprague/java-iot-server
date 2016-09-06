package cz.eclub.iot.model.classes;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    @Column(name = "value")
    private String value;

    @Basic
    @Column(name = "unit")
    private String unit;

    @Column(name = "timestamp")
    private long timestamp;

    @ManyToOne
    @JoinColumn(name = "sensorID", referencedColumnName = "ID", nullable = false)
    private SensorEntity sensor;

    @Override
    public String toString() {
        return "SensorEntryEntity{" +
                "value='" + value + '\'' +
                ", unit='" + unit + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
