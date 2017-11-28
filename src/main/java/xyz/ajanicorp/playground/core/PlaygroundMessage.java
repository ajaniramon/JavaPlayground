package xyz.ajanicorp.playground.core;

/**
 * Created by Ajani on 04/11/2017.
 */
public class PlaygroundMessage {
    private String field;

    private String message;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PlaygroundMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
