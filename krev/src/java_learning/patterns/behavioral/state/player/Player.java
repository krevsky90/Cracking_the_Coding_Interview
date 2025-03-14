package java_learning.patterns.behavioral.state.player;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private IState state;

    //real internal state of player
    private boolean playing = false;
    private int currentTrack = 0;

    private List<String> playlist = new ArrayList<>();


    public Player() {
        playing = true;
        for (int i = 0; i < 10; i++) {
            playlist.add("Track # " + i);
        }

        state = new PauseState(this);
    }

    public void changeState(IState state) {
        this.state = state;
    }

    public IState getState() {
        return state;
    }

    //internal methods that are helpers for business logic
    //unfortunately we have to keep these methods public, otherwise state classes won't be able to use these methods
    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public void nextTrack() {
        currentTrack++;
        currentTrack = currentTrack % playlist.size();
    }

    public void previousTrack() {
        currentTrack--;
        currentTrack = (currentTrack + playlist.size()) % playlist.size();
    }

    public void playTrack() {
        System.out.println("Play current song: " + playlist.get(currentTrack));
    }

    //business logic
    public void onPlay() {
        state.onPlay();
    }

    public void onNext() {
        state.onNext();
    }

    public void onPrevious() {
        state.onPrevious();
    }
}
