package cz.eclub.iot.model.classes;

import lombok.*;

import javax.persistence.Entity;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorEntity extends AbstractEntity {
    private String _UUID, description, location;
}
