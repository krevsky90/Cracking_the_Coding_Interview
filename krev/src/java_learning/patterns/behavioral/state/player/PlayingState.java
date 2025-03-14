package java_learning.patterns.behavioral.state.player;

public class PlayingState extends AbstractState {
    protected PlayingState(Player player) {
        super(player);
    }

    @Override
    public void onPlay() {
        System.out.println("Go to 'Pausing' state:");
        context.setPlaying(false);
        context.changeState(new PauseState(context));
    }

    @Override
    public void onNext() {
        System.out.println("playing the next track...");
        context.nextTrack();
        context.playTrack();
    }

    @Override
    public void onPrevious() {
        System.out.println("playing the previous track...");
        context.previousTrack();
        context.playTrack();
    }
}
