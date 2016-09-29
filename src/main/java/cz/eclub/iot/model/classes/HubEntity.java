package cz.eclub.iot.model.classes;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "hub")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HubEntity extends AbstractEntity{

    @Basic
    @Column(name="uuid")
    private String uuid;

    @Basic
    @Column(name="name")
    private String name;

    @Basic
    @Column(name="location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "ID", nullable = false)
    private UserEntity owner;


}
