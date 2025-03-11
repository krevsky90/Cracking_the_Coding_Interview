package java_learning.patterns.behavioral.observer;

import java.util.Random;

public class WeatherForecast extends AbstractPublisher {
    private int temperature = 20;
    private Random r = new Random();

    public void updateTemperature() {
        temperature += r.nextInt(5, 25);
        notify(temperature);
    }

    public void notify(int temperature) {
        for (ISubscriber subscriber : getSubscriberList()) {
            subscriber.update("temperature is " + temperature);
        }
    }

}
