package cz.eclub.iot.model.classes;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Tom on 29.07.2016.
 */
@Entity
@Table(name = "MESSAGE")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity extends AbstractEntity{

    @Basic
    @Column(name="message")
    private String message;

    @Override
    public String toString() {
        return "MessageEntity{" +
                "message='" + message + '\'' +
                '}';
    }
}
