package java_learning.patterns.behavioral.state.nested_classes;

public class Client {
    public static void main(String[] args) {
        Player player = new Player();
        Button playButton = new Button(() -> player.onPlay());   //will start or stop playing depending on state
        Button nextButton = new Button(() -> player.onNext());
        Button prevButton = new Button(() -> player.onPrevious());
        //just for debugging (print state)
        DecoratedButton playButton1 = new DecoratedButton(playButton, player);
        DecoratedButton nextButton1 = new DecoratedButton(nextButton, player);
        DecoratedButton prevButton1 = new DecoratedButton(prevButton, player);

        //test
        playButton1.onPress();   //stop -> playing
        System.out.println("---");
        playButton1.onPress();   //playing -> stop
        System.out.println("---");
        nextButton1.onPress();   //does nothing in stopped state
        System.out.println("---");
        playButton1.onPress();   //stop -> playing
        System.out.println("---");
        nextButton1.onPress();   //go to next track
        System.out.println("---");
        nextButton1.onPress();   //go to next track
        System.out.println("---");
        prevButton1.onPress();   //go to prev track
        System.out.println("---");
        playButton1.onPress();   //playing -> stop
    }
}
