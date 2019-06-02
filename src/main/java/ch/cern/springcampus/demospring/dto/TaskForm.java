package ch.cern.springcampus.demospring.dto;

import java.io.Serializable;

public class TaskForm implements Serializable {
    private String id;
    private String text;
    private boolean done;

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
}
