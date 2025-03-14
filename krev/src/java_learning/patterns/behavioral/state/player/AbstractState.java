package java_learning.patterns.behavioral.state.player;

public abstract class AbstractState implements IState {
    //need to keep it protected to use in subclasses
    protected Player context;

    protected AbstractState(Player player) {
        this.context = player;
    }

}
