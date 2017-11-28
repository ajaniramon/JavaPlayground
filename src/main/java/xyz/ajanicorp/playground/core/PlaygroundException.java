package xyz.ajanicorp.playground.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ajani on 04/11/2017.
 */
public class PlaygroundException extends Exception {
    public List<PlaygroundMessage> playgroundMessageList;

    public List<PlaygroundMessage> getPlaygroundMessageList() {
        return playgroundMessageList;
    }

    public void setPlaygroundMessageList(List<PlaygroundMessage> playgroundMessageList) {
        this.playgroundMessageList = playgroundMessageList;
    }

    public PlaygroundException(String message, Throwable cause, List<PlaygroundMessage> playgroundMessageList) {
        super(message, cause);
        this.playgroundMessageList = playgroundMessageList;
    }

    public PlaygroundException(List<PlaygroundMessage> playgroundMessageList) {
        this.playgroundMessageList = playgroundMessageList;
    }

    public PlaygroundException(PlaygroundMessage playgroundMessage){
        this.playgroundMessageList = new ArrayList<>();

        this.playgroundMessageList.add(playgroundMessage);
    }
}
