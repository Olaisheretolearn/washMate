## WashMate Notifier 🌀

Turn your washing machine into a smart device with **WashMate Notifier**!

### Description

With **WashMate Notifier**, you'll never miss the end of a wash cycle. Using a Raspberry Pi and an SW-420 vibration sensor, this IoT solution determines when your washing machine completes its cycle. The moment vibrations cease, you get an SMS on your phone, thanks to the Twilio API.

#### Features

- 🌐 **IoT-Enabled**: Convert your ordinary washing machine into a smart device.
- 📲 **Real-time Alerts**: Receive instant SMS notifications.
- 💡 **Simple Hardware Setup**: Raspberry Pi and SW-420 sensor.
- 🔒 **Twilio Security**: Reliable and secure notifications.

### Setup and Installation

#### Prerequisites

1. Raspberry Pi with Java installed.
2. SW-420 vibration sensor.
3. Twilio Account.

#### Hardware Setup

1. Connect the SW-420 vibration sensor to your Raspberry Pi:
    - **VCC** to 3.3V on the Pi.
    - **GND** to Ground on the Pi.
    - **DO** (Digital Output) to a GPIO pin, for example GPIO_00.

#### Software Steps

1. **Clone the Repository**:
    ```
    git clone [repository-url]
    ```

2. **Configure Twilio**:
    - Create an account on Twilio.
    - Obtain your `ACCOUNT_SID` and `AUTH_TOKEN` from the Twilio dashboard.
    - Update the `WashingMachineNotifier.java` file with your Twilio credentials and phone numbers.

3. **Compile and Run**:
    ```
    javac -cp ".:path-to-twilio-lib:path-to-pi4j-lib" WashingMachineNotifier.java
    java -cp ".:path-to-twilio-lib:path-to-pi4j-lib" WashingMachineNotifier
    ```

### Usage

After setup, run the Java application. Securely tape or fix the Raspberry Pi and the SW-420 sensor to your washing machine, ensuring that the SW-420 has good contact to detect vibrations. Wait for your laundry, and receive an SMS once it's complete.

### Contribution

Open to improvements! Fork this repository and submit your enhancements via pull requests.

### License

MIT License

