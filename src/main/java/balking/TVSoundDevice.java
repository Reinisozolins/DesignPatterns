package balking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

// TV Sound Device class

public class TVSoundDevice {
    private static final Logger LOGGER = LoggerFactory.getLogger(TVSoundDevice.class);
    private final DelayProvider delayProvider;
    private static TVSoundDeviceStatus tvSoundDeviceStatus;

    // Create new instance of TV Sound Device

    public TVSoundDevice() {
        this((interval, timeUnit, task) -> {
            try {
                Thread.sleep(timeUnit.toMillis(interval));
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            task.run();
        });
    }

    /**
     * Creates a new instance of TVSoundDevice using provided delayProvider.
     * This constructor is used only for
     * unit testing purposes.
     */

    public TVSoundDevice(DelayProvider delayProvider) {
        this.delayProvider = delayProvider;
        this.tvSoundDeviceStatus = TVSoundDeviceStatus.MUTE;

    }

    public TVSoundDeviceStatus getTVSoundDeviceStatus() {
        return tvSoundDeviceStatus;
    }


    /**
     * Method responsible for make TV louder
     * if the object is in appropriate state
     */
    public void sound() {
        synchronized (this) {
            LOGGER.info("{}: Actual sound status: {}", Thread.currentThread().getName(), getTVSoundDeviceStatus());
            if (tvSoundDeviceStatus == TVSoundDeviceStatus.MAXVOLUME) {
                LOGGER.error("ERROR: Can not get louder if there is MAXVOLUME already");
                return;
            }
            tvSoundDeviceStatus = TVSoundDeviceStatus.MAXVOLUME;
        }
        LOGGER.info("{}: Switch to MAXVOLUME", Thread.currentThread().getName());
        this.delayProvider.executeAfterDelay(50, TimeUnit.MILLISECONDS, this::turnOfTV);
    }

    /**
     * Method responsible for turn of tv when successfully i times you push "+ on tv pult"
     */

    public synchronized void turnOfTV() {
        tvSoundDeviceStatus = TVSoundDeviceStatus.MUTE;
        LOGGER.info("{} TV turn of successfully");
    }
}

