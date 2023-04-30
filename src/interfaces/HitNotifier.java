package interfaces;

/**
 * interface interfaces.HitNotifier - notify to other obj that something happen.
 */
public interface HitNotifier {

    /**
     * addHitListener - Add hl as a listener to hit events.
     * @param hl - hit listener to add.
     */
    void addHitListener(HitListener hl);

    /**
     * removeHitListener - // Remove hl from the list of listeners to hit events.
     * @param hl -  remove this hit listener from obj.
     */
    void removeHitListener(HitListener hl);
}
