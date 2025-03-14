package java_learning.patterns.behavioral.state.player;

public class PauseState extends AbstractState {
    protected PauseState(Player player) {
        super(player);
    }

    @Override
    public void onPlay() {
        System.out.println("Go to 'Playing' state:");
        context.setPlaying(true);
        context.playTrack();
        context.changeState(new PlayingState(context));
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
