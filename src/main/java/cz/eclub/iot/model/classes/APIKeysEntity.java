package cz.eclub.iot.model.classes;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "API_KEYS")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIKeysEntity extends AbstractEntity {

    @Basic
    @Column(name="apikey")
    private String apikey;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dtUpdated")
    private Date dtUpdated;

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "ID", nullable = false)
    private UserEntity assignedTo;

}
