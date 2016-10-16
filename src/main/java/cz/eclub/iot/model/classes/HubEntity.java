package cz.eclub.iot.model.classes;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HubEntity extends AbstractEntity {

    private String uuid;

    private String name;

    private String location;

    @ManyToOne
    private UserEntity owner;


}
