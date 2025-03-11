package java_learning.patterns.behavioral.observer;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person();
        Person p2 = new Person();
        Alien a1 = new Alien();

        NewYorkTimes newYorkTimes = new NewYorkTimes();
        WeatherForecast weatherForecast = new WeatherForecast();

        newYorkTimes.subscribe(p1);
        newYorkTimes.subscribe(p2);
        newYorkTimes.subscribe(a1);
        weatherForecast.subscribe(p1);

        newYorkTimes.publish();
        weatherForecast.updateTemperature();

        newYorkTimes.unsubscribe(p1);
        newYorkTimes.publish();
    }
}
