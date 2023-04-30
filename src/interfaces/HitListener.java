package interfaces;

import characters.Ball;
import characters.Block;

/**
 * interfaces.HitListener - interface for classes that need to know when something happen.
 */
public interface HitListener {

    /**
     * hitEvent - This method is called whenever the beingHit object is hit. The hitter parameter is the characters.Ball
     * that's doing the hitting.
     * @param beingHit - the block.
     * @param hitter - the ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
