package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * class KeyPressStoppableAnimation - implements Animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * KeyPressStoppableAnimation - constructor to class.
     * @param sensor - sensor to indicate what pressed.
     * @param key - what the sensor expecting.
     * @param animation - animation to display.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.sensor.isPressed(key) && !isAlreadyPressed) {
            this.stop = true;
        }
        if (key.equals(" ") && this.sensor.isPressed(KeyboardSensor.SPACE_KEY) && !isAlreadyPressed) {
            this.stop = true;
        }
        if (!this.sensor.isPressed(key) && !(key.equals(" ") && this.sensor.isPressed(KeyboardSensor.SPACE_KEY))) {
            isAlreadyPressed = false;
        }
        animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}