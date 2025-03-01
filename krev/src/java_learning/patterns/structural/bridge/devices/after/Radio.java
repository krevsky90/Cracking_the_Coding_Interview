package java_learning.patterns.structural.bridge.devices.after;

public class Radio implements Device {
    private boolean isEnabled;
    private int volume;
    private int channel;

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public void enable() {
        System.out.println("enable Radio");
        isEnabled = true;
    }

    @Override
    public void disable() {
        System.out.println("disable Radio");
        isEnabled = false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int volume) {
        System.out.println("set volume " + volume + " to Radio");
        this.volume = volume;
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        System.out.println("set channel " + channel + " to Radio");
        this.channel = channel;
    }
}
