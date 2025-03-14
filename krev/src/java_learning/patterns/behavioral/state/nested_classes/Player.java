package java_learning.patterns.behavioral.state.nested_classes;

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

        state = new PauseState();
    }

    public void changeState(IState state) {
        this.state = state;
    }

    public IState getState() {
        return state;
    }

    //internal methods that are helpers for business logic
    //NOTE: since all states are implemented as nested classes,
    //we can mark internal methods and fields as private!
    private boolean isPlaying() {
        return playing;
    }

    private void setPlaying(boolean playing) {
        this.playing = playing;
    }

    private void nextTrack() {
        currentTrack++;
        currentTrack = currentTrack % playlist.size();
    }

    private void previousTrack() {
        currentTrack--;
        currentTrack = (currentTrack + playlist.size()) % playlist.size();
    }

    private void playTrack() {
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

    private interface IState {
        void onPlay();
        void onNext();
        void onPrevious();
    }

    private class PauseState implements IState {

        @Override
        public void onPlay() {
            System.out.println("Go to 'Playing' state:");
            setPlaying(true);
            playTrack();
            changeState(new PlayingState());
        }

        @Override
        public void onNext() {
            System.out.println("Do nothing");
        }

        @Override
        public void onPrevious() {
            System.out.println("Do nothing");
        }
    }

    private class PlayingState implements IState {
        @Override
        public void onPlay() {
            System.out.println("Go to 'Pausing' state:");
            setPlaying(false);
            changeState(new PauseState());
        }

        @Override
        public void onNext() {
            System.out.println("playing the next track...");
            nextTrack();
            playTrack();
        }

        @Override
        public void onPrevious() {
            System.out.println("playing the previous track...");
            previousTrack();
            playTrack();
        }
    }
}
