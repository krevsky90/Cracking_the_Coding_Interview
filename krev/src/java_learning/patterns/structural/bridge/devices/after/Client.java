package java_learning.patterns.structural.bridge.devices.after;

/**
 * NOTE: in this particular example we could create abstract class
 * with common fields and implementation of methods like isEnabled() that would implement Device interface
 */
public class Client {
    public static void main(String[] args) {
        Device device = new Radio();
        Remote remote = new AdvancedRemote(device);

        remote.nextChannel();
        remote.volumeDown();
    }
}
