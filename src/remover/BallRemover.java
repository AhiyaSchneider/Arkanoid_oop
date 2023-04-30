package remover;

import characters.Ball;
import characters.Block;
import counter.Counter;
import game.GameLevel;
import interfaces.HitListener;

/**
 * class remover.BallRemover - implements interfaces.HitListener when ball hit this block remove ball from game.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * remover.BallRemover - constructor to class.
     * @param game - given game.
     * @param removedBalls - the ball to remove.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * hitEvent - Blocks that are hit should be removed from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     * @param beingHit - block that being hit.
     * @param hitter - hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
