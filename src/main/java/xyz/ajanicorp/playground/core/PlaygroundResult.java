package xyz.ajanicorp.playground.core;

import org.springframework.hateoas.ResourceSupport;
import xyz.ajanicorp.playground.constants.PlaygroundStatusCodes;

/**
 * Created by Ajani on 04/11/2017.
 */
public class PlaygroundResult<T> extends ResourceSupport{
    private PlaygroundStatusCodes statusCode;

    private T result;

    public PlaygroundStatusCodes getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(PlaygroundStatusCodes statusCode) {
        this.statusCode = statusCode;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public PlaygroundResult(PlaygroundStatusCodes statusCode, T result) {
        this.statusCode = statusCode;
        this.result = result;
    }

    public PlaygroundResult(PlaygroundStatusCodes statusCode) {
        this.statusCode = statusCode;
    }
}
