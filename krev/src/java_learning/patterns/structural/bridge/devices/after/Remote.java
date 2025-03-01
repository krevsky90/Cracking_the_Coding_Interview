package java_learning.patterns.structural.bridge.devices.after;

public class Remote {
    protected Device device;

    public Remote(Device device) {
        this.device = device;
    }

    public void togglePower() {
        if (device.isEnabled()) {
            device.disable();
        } else {
            device.enable();
        }
    }

    public void volumeDown() {
        device.setVolume(device.getVolume() - 1);
    }

    public void volumeUp() {
        device.setVolume(device.getVolume() + 1);
    }

    public void previousChannel() {
        device.setChannel(device.getChannel() - 1);
    }

    public void nextChannel() {
        device.setChannel(device.getChannel() + 1);
    }
}
