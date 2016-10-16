package cz.eclub.iot.model.classes;

import lombok.*;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Indexed
@ToString(exclude = "sensor")
public class SensorEntryEntity extends AbstractEntity implements Serializable {

    private String value;

    private String unit;

    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.YES)
    private long timestamp;

    @ManyToOne
    private SensorEntity sensor;


}
