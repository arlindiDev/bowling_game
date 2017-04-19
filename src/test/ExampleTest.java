package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import net.example.*;

public class ExampleTest {

    private Game bowlingGame;

    @Before
    public void init() {
        bowlingGame = new Game();
    }

    @After
    public void cleanUp(){
        //bowlingGame.endGame();
        //assertThat();
    }

    @Test
    public void testGameClass() {
        Game game = new Game();
        assertNotNull(game);
    }

    @Test
    public void testStartGameScoreIsZero() {
        Game game = new Game();
        assertThat(game.score(), is(Score.START_GAME_SCORE));
    }

    @Test
    public void testThatGameHasTenFrames() {
        Game game = new Game();
        assertThat(game.numberOfFrames(), is(Game.NUMBER_OF_FRAMES));
    }

    @Test
    public void testThatFrameHasInitialTwoThrows() {
        Frame frame = new Frame();
        assertThat(frame.numberOfThrows(), is(Frame.INITIAL_NR_OF_THROWS));
    }

    @Test
    public void testThatGameHasPlayer() {
        Game game = new Game();
        assertFalse(game.hasPlayer());
    }

    @Test
    public void testRollIncreasesScoreByNumberOfRolls() {
        Game game = new Game();
        game.rolls(2);
        assertThat(game.score(), is(2));
    }

    @Test
    public void testThatFrameHasInitialScoreZero() {
        Frame frame = new Frame();
        assertThat(frame.score(), is(Score.START_GAME_SCORE));
    }

    @Test
    public void testThatFrameScoreIsIncreased() {
        Frame frame = new Frame();
        frame.increaseScoreBy(5);
        frame.increaseScoreBy(3);
        assertThat(frame.score(), is(8));
    }

    @Test
    public void testThatGameScoreIsEqualToTheSumOfScoreOfAllFrames() {
        Game game = new Game();
        game.rolls(2);
        game.rolls(5);
        game.rolls(5);

        assertThat(game.score(), is(12));
    }

    @Test
    public void testThatNumberOfThrowsIsZero() {
        Frame frame = new Frame();

        assertThat(frame.getThrow(), is(0));
    }

    @Test
    public void testThatNumberOfThrowIsIncreased() {
        Frame frame = new Frame();
        frame.moveToNextThrow();
        assertThat(frame.getThrow(), is(1));

        frame.moveToNextThrow();
        assertThat(frame.getThrow(), is(2));

        frame.moveToNextThrow();
        assertThat(frame.getThrow(), is(3));
    }

    @Test
    public void testThatFrameIsBeingShifted() {
        Game game = new Game();
        game.shiftToNextFrame();
        assertThat(game.getIndexOfCurrentFrame(), is(1));

        game.shiftToNextFrame();
        assertThat(game.getIndexOfCurrentFrame(), is(2));

        game.shiftToNextFrame();
        assertThat(game.getIndexOfCurrentFrame(), is(3));
    }

    @Test
    public void isFrameBeingShiftedAfterRolling() {
        Game game = new Game();

        game.rolls(5);
        assertThat(game.getIndexOfCurrentFrame(), is(0));

        game.rolls(5);
        assertThat(game.getIndexOfCurrentFrame(), is(0));

        game.rolls(5);
        assertThat(game.getIndexOfCurrentFrame(), is(1));

        game.rolls(5);
        assertThat(game.getIndexOfCurrentFrame(), is(1));

        game.rolls(5);
        assertThat(game.getIndexOfCurrentFrame(), is(2));

        game.rolls(5);
        assertThat(game.getIndexOfCurrentFrame(), is(2));

        game.rolls(5);
        assertThat(game.getIndexOfCurrentFrame(), is(3));
    }

    @Test
    public void testRollsShouldNotBeThrownAfterGameFinished() {
        Game game = new Game();

        for (int i = 0; i < 100000; i++) {
            game.rolls(5);
        }
        assertThat(game.getIndexOfCurrentFrame(), is(Game.NUMBER_OF_FRAMES));
    }

    @Test
    public void isNumberOfPinsTenInAFrame(){
        Frame frame = new Frame();

        assertThat(frame.numberOfStandingPins(), is(10));
    }

    @Test
    public void numberOfPinsLeftAfterRolling(){
        Game game = new Game();

        game.rolls(5);
        game.rolls(5);

        assertThat(game.getCurrentFrame().numberOfStandingPins(), is(0));
    }

    @Test
    public void testWhenFrameChangesAfterStrike(){
        Game game = new Game();

        game.rolls(10);

        assertThat(game.getIndexOfCurrentFrame(), is(1));
    }

    @Test
    public void testFrameWithStrikeIfItGetsPointsOfNextTwoThrows(){
        Game game = new Game();

        game.rolls(10);
        game.rolls(2);
        game.rolls(5);

        assertThat(game.getFrameByIndex(0).score(), is(17));
    }

    @Test
    public void testAfterTwoStrikesInARowIsFirstFrameIncreased(){
        Game game = new Game();

        game.rolls(10);

        game.rolls(10);

        game.rolls(5);
        game.rolls(4);

        game.rolls(3);
        game.rolls(3);

        game.rolls(10);

        game.rolls(10);

        game.rolls(10);

        game.rolls(10);

        game.rolls(10);

        assertThat(game.getFrameByIndex(0).score(), is(25));
        assertThat(game.getFrameByIndex(1).score(), is(19));
        assertThat(game.getFrameByIndex(2).score(), is(9));
        assertThat(game.getFrameByIndex(3).score(), is(6));
        assertThat(game.getFrameByIndex(4).score(), is(30));
        assertThat(game.getFrameByIndex(5).score(), is(30));
        assertThat(game.getFrameByIndex(6).score(), is(30));
        assertThat(game.getFrameByIndex(7).score(), is(20));
        assertThat(game.getFrameByIndex(8).score(), is(10));
    }

    @Test
    public void testThatFrameIsSpareAfterTwoRollsThatHitTen(){
        Game game = new Game();

        game.rolls(7);
        game.rolls(3);

        game.rolls(1);
        game.rolls(5);

        assertTrue(game.getFrameByIndex(0).isSpare());
        assertFalse(game.getFrameByIndex(1).isSpare());
    }

    @Test
    public void testSpareIfLastFrameScoreIsIncreased(){
        Game game = new Game();

        game.rolls(3);
        game.rolls(7);

        game.rolls(9);
        game.rolls(1);

        game.rolls(8);
        game.rolls(1);

        game.rolls(10);

        game.rolls(5);
        game.rolls(5);

        game.rolls(10);

        game.rolls(3);
        game.rolls(7);

        game.rolls(4);
        game.rolls(4);


        assertThat(game.getFrameByIndex(0).score(), is(19));
        assertThat(game.getFrameByIndex(1).score(), is(18));
        assertThat(game.getFrameByIndex(2).score(), is(9));
        assertThat(game.getFrameByIndex(3).score(), is(20));
        assertThat(game.getFrameByIndex(4).score(), is(20));
        assertThat(game.getFrameByIndex(5).score(), is(20));
        assertThat(game.getFrameByIndex(6).score(), is(14));
        assertThat(game.getFrameByIndex(7).score(), is(8));
    }

    @Test
    public void testThatTenthFrameStrikeHasThreeThrows(){

        Game game = new Game();

        game.rolls(3);
        game.rolls(3);

        game.rolls(3);
        game.rolls(3);

        game.rolls(3);
        game.rolls(3);

        game.rolls(3);
        game.rolls(3);

        game.rolls(3);
        game.rolls(3);

        game.rolls(3);
        game.rolls(3);

        game.rolls(3);
        game.rolls(3);

        game.rolls(3);
        game.rolls(3);

        game.rolls(3);
        game.rolls(3);

        game.rolls(10);
        game.rolls(3);


        assertThat(game.score(), is(67));
    }

}
