package ch.cern.springcampus.demospring.bean;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Task implements Serializable {

    @Id
    private String id;
    private String text;
    private boolean done;
    @CreationTimestamp
    private Timestamp createdOn;
    private User createdBy;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(final boolean done) {
        this.done = done;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(final Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final User createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
