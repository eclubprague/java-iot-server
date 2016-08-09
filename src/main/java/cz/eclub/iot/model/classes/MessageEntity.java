package cz.eclub.iot.model.classes;

import javax.persistence.Entity;

/**
 * Created by Tom on 29.07.2016.
 */
@Entity
public class MessageEntity extends AbstractEntity{
    private String message;

    public MessageEntity(){};

    public MessageEntity(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "message='" + message + '\'' +
                '}';
    }
}
