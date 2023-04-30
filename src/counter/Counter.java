package counter;

/**
 * class counter.Counter - as it sound.
 */
public class Counter {
    private int counter;

    /**
     * counter.Counter - constructor.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * counter.Counter - counter with a num to initialize the counter with.
     * @param counter - the param to initialize the counter with.
     */
    public Counter(int counter) {
        this.counter = counter;
    }

    /**
     * increase - add number to current count.
     * @param number - increase the counter with a given num
     */
    public void increase(int number) {
        counter = counter + number;
    }

    /**
     * decrease - subtract number from current count.
     * @param number - the number to subtract from counter.
     */
    public void decrease(int number) {
        counter = counter - number;
    }

    /**
     * getValue - get current count.
     * @return the value of the counter.
     */
    public int getValue() {
        return counter;
    }

    /**
     * toString - return string.
     * @return int to string.
     */
    public String toString() {
        return Integer.toString(counter);
    }
}
