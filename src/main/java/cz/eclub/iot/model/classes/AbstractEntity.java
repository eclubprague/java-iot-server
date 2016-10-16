package cz.eclub.iot.model.classes;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.DocumentId;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity implements IEntity {

    @Id
    @DocumentId
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String Id;

    @Override
    public String getId() {
        return Id;
    }

    @Override
    public void setId(String Id) {
        this.Id = Id;
    }

}
