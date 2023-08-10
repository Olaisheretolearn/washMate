import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.pi4j.io.gpio.*;

public class WashingMachineNotifier {

    private static final String ACCOUNT_SID = "YOUR_TWILIO_ACCOUNT_SID";
    private static final String AUTH_TOKEN = "YOUR_TWILIO_AUTH_TOKEN";
    private static final String TWILIO_NUMBER = "YOUR_TWILIO_PHONE_NUMBER";
    private static final String DESTINATION_NUMBER = "YOUR_PHONE_NUMBER";

    private static final Pin VIBRATION_SENSOR_PIN = RaspiPin.GPIO_00; // This might change based on your connection
    private static long lastTime = 0;
    private static boolean lastState = false;
    private static final double TIME_THRESHOLD = 0.20;

    public static void main(String[] args) throws InterruptedException {
        // Initialize Twilio
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Setup GPIO
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalInput vibrationSensor = gpio.provisionDigitalInputPin(VIBRATION_SENSOR_PIN, PinPullResistance.PULL_DOWN);
        vibrationSensor.setShutdownOptions(true, PinState.LOW);
        vibrationSensor.addListener(new VibrationSensorListener());

        // Keep running
        while (true) {
            Thread.sleep(1000);
        }
    }

    static class VibrationSensorListener implements GpioPinListenerDigital {
        @Override
        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
            boolean currentState = event.getState().isHigh();

            if (currentState != lastState) {
                if (currentState) {
                    lastTime = System.currentTimeMillis();
                } else {
                    long stopTime = System.currentTimeMillis();
                    double elapsedTime = (stopTime - lastTime) / 1000.0;
                    if (elapsedTime > TIME_THRESHOLD) {
                        System.out.println("Vibration has stopped after " + elapsedTime + " seconds");
                        sendMessage();
                    }
                }
                lastState = currentState;
            }
        }
    }

    private static void sendMessage() {
        Message.creator(
                new PhoneNumber(DESTINATION_NUMBER),
                new PhoneNumber(TWILIO_NUMBER),
                "Ola vibration Stopped!"
        ).create();
        System.out.println("Message sent");
    }
}
