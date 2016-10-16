package cz.eclub.iot.model.classes;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class APIKeysEntity extends AbstractEntity {

    private String apikey;

    private Date dtUpdated;

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "ID", nullable = false)
    private UserEntity assignedTo;

}
