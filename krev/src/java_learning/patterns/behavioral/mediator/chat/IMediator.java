package java_learning.patterns.behavioral.mediator.chat;

/**
 * IF we expect that we might create several mediators for different types of interactions between these objects => create interface
 */
public interface IMediator {
    void notify(AbstractUser user, String message);
}
