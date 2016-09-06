package cz.eclub.iot.model.classes;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorEntryEntity extends AbstractEntity {
    private String _UUID, description, location;

    @ManyToOne
    private SensorEntity sensor;
}
