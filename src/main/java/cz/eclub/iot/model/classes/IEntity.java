package cz.eclub.iot.model.classes;

import java.io.Serializable;

public interface IEntity extends Serializable {

    public String getId();

    public void setId(String id);
}
