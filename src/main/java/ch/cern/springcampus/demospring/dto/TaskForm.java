package ch.cern.springcampus.demospring.dto;

import java.io.Serializable;

public class TaskForm implements Serializable {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }
}
