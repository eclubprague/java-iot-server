package cz.eclub.iot.model.classes;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends AbstractEntity {

    @Basic
    @Column(name="name")
    private String name;

    @Basic
    @Column(name="email")
    private String email;

    @Basic
    @Column(name="password")
    private String password;


    @OneToMany(mappedBy = "assignedTo")
    private Collection<APIKeysEntity> apiKeyEntities;

    @OneToMany(mappedBy = "owner")
    private Collection<HubEntity> hubs;

}
