package java_learning.patterns.behavioral.state.player;

public interface IState {
    void onPlay();
    void onNext();
    void onPrevious();
}
