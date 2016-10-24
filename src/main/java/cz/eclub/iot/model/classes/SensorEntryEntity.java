package cz.eclub.iot.model.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "sensor")
public class SensorEntryEntity extends AbstractEntity implements Serializable {

    private String value;

    private String unit;

    private long timestamp;

    @JsonIgnore
    @ManyToOne
    private SensorEntity sensor;


}
