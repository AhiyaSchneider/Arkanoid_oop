package score;

import characters.Ball;
import characters.Block;
import counter.Counter;
import interfaces.HitListener;

/**
 * class score.ScoreTrackingListener - implements interfaces.HitListener ,score tracking class.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * score.ScoreTrackingListener - constructor to class.
     * @param scoreCounter - a counter to initialize the local as.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hitEvent - what to do when there is a hit. to add to counter 5 points.
     * @param beingHit - the block.
     * @param hitter - the ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       beingHit.removeHitListener(this);
       currentScore.increase(5);
    }

    /**
     * toString - return int to string.
     * @return string.
     */
    public String toString() {
        return currentScore.toString();
    }
}
