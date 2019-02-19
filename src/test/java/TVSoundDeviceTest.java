import balking.DelayProvider;
import balking.TVSoundDevice;
import balking.TVSoundDeviceStatus;
import org.junit.jupiter.api.Test;


import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TVSoundDeviceTest {
    private FakeDelayProvider fakeDelayProvider = new FakeDelayProvider();

    @Test
    public void sound() {
        TVSoundDevice tvSoundDevice = new TVSoundDevice(fakeDelayProvider);
        tvSoundDevice.sound();
        tvSoundDevice.sound();

        TVSoundDeviceStatus tvStatus = tvSoundDevice.getTVSoundDeviceStatus();
        fakeDelayProvider.task.run();

        assertEquals(TVSoundDeviceStatus.MUTE, tvStatus);
        assertEquals(TVSoundDeviceStatus.MAXVOLUME, tvSoundDevice.getTVSoundDeviceStatus());

    }

    @Test
    public void turnOFtv() {
        TVSoundDevice tvSoundDevice = new TVSoundDevice();
        tvSoundDevice.sound();
        assertEquals(TVSoundDeviceStatus.MAXVOLUME, tvSoundDevice.getTVSoundDeviceStatus());
    }

    private class FakeDelayProvider implements DelayProvider{
    private Runnable task;

    @Override
    public void executeAfterDelay(long interval, TimeUnit timeUnit, Runnable task) {
        this.task = task;
    }
}


}











