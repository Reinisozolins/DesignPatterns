package balking;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * In Balking Design Pattern if an object’s method is invoked when it is in an inappropriate state,
 * then the method will return without doing anything. Objects that use this pattern are generally only in a
 * state that is prone to balking temporarily but for an unknown amount of time
 *
 * Imagine situation you have TV remote control system. In beginning status is "Mute" but when you push " + " button  its made
 * already max volume on your TV sound device. System inform you about it. When you push " + " button "i" times TV turn off.
 */

public class App {

    public static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String... args) {
       BasicConfigurator.configure();

        final TVSoundDevice tvSoundDevice = new TVSoundDevice();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(tvSoundDevice::sound);
        }
         executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        }catch  (InterruptedException ie){
            LOGGER.error("ERROR: Waiting on executor service turn off");
        }
    }
}
