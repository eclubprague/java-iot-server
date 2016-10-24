package cz.eclub.iot.model.classes;

import lombok.*;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Collection;

@Entity
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SensorEntity extends AbstractEntity implements Serializable {

    @Field(index = Index.YES, analyze = Analyze.NO, store = Store.YES)
    private String _UUID;

    private String description;

    private String location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sensor", fetch = FetchType.EAGER)
    private Collection<SensorEntryEntity> sensorEntries;

}
