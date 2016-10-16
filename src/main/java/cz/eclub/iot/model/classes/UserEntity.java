package cz.eclub.iot.model.classes;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity extends AbstractEntity {

    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy = "assignedTo")
    private Collection<APIKeysEntity> apiKeyEntities;

    @OneToMany(mappedBy = "owner")
    private Collection<HubEntity> hubs;

}
