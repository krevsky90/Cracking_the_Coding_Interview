package java_learning.patterns.structural.bridge.devices.after;

/**
 * optional class for this example
 */
public class AdvancedRemote extends Remote {
    public AdvancedRemote(Device device) {
        super(device);
    }

    public void mute() {
        device.setVolume(0);
    }
}
