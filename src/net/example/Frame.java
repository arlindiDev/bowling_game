package net.example;

import static net.example.Score.START_GAME_SCORE;

/**
 * Keep reading
 */
public class Frame {
    public final static int INITIAL_NR_OF_THROWS = 2;
    private int numberOfThrows = INITIAL_NR_OF_THROWS;

    private int score = START_GAME_SCORE;
    private int numberOfCurrentThrow = 0;
    private final net.example.Pins pins = new net.example.Pins();

    public int numberOfThrows() {
        return numberOfThrows;
    }

    public int getThrow() {
        return numberOfCurrentThrow;
    }

    public int score() {
        return score;
    }

    public void increaseScoreBy(int value) {
        score += value;
    }

    public void moveToNextThrow() {
        numberOfCurrentThrow++;
    }

    public int numberOfStandingPins() {
        return pins.numberOfStandingPins();
    }

    public void subtractStandingPins(int pinsHit) {
        pins.subtractStandingPins(pinsHit);
    }

    public void pinsHit(int pinsHit) {
        subtractStandingPins(pinsHit);
        increaseScoreBy(pinsHit);
        moveToNextThrow();
    }

    public boolean isStrike(){
        return pins.numberOfStandingPins() == 0 && getThrow() == 1;
    }

    public boolean isSpare() {
        return pins.numberOfStandingPins() == 0 && getThrow() == 2;
    }
}
