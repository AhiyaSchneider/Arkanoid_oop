package remover;

import characters.Ball;
import characters.Block;
import counter.Counter;
import game.GameLevel;
import interfaces.HitListener;

/**
 * class remover.BlockRemover - implements interfaces.HitListener
 * a remover.BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * remover.BlockRemover - constructor to class.
     * @param game - given game.
     * @param removedBlocks - block to remove from game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * hitEvent - Blocks that are hit should be removed from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     * @param beingHit - block being hit.
     * @param hitter - hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
    }
}