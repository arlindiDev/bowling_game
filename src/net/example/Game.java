package net.example;

import java.util.ArrayList;

/**
 * Keep reading
 */
public class Game {

    private int score = Score.START_GAME_SCORE;
    public static final int NUMBER_OF_FRAMES = 10;
    private int numberOfFrames = NUMBER_OF_FRAMES;

    ArrayList<Frame> frames = new ArrayList<Frame>(numberOfFrames);
    ArrayList<Player> players = new ArrayList<Player>();

    private int indexOfCurrentFrame = 0;

    public Game() {
        for (int i = 0; i < numberOfFrames; i++) {
            frames.add(new Frame());
        }

        players.add(new Player());
    }

    public int score() {
        for (int i = 0; i < numberOfFrames; i++) {
            score += frames.get(i).score();
        }
        return score;
    }

    public int numberOfFrames() {
        return frames.size();
    }

    public boolean hasPlayer() {
        return players.isEmpty();
    }

    public void rolls(int pinsHit) {

        if (indexOfCurrentFrame >= NUMBER_OF_FRAMES) {
            return;
        }

        if (getCurrentFrame().getThrow() == 2) {
            shiftToNextFrame();
        }

        if (indexOfCurrentFrame > 3){
            String asd = "Aasd";
        }

        if (indexOfCurrentFrame >= NUMBER_OF_FRAMES) {
            return;
        }

        getCurrentFrame().pinsHit(pinsHit);

        if (getLastFrame() != null && getLastFrame().isSpare() && getCurrentFrame().getThrow() == 1) {
            getLastFrame().increaseScoreBy(pinsHit);
        }

        if (getLastFrame() != null && getLastFrame().isStrike()) {
            getLastFrame().increaseScoreBy(pinsHit);

            if (getLastLastFrame()!= null && getLastLastFrame().isStrike() && getCurrentFrame().getThrow() == 1) {
                getLastLastFrame().increaseScoreBy(pinsHit);
            }
        }


        if (getCurrentFrame().isStrike()) {
            shiftToNextFrame();
        }
    }


    public void shiftToNextFrame() {
        indexOfCurrentFrame++;
    }

    public int getIndexOfCurrentFrame() {
        return indexOfCurrentFrame;
    }

    public Frame getCurrentFrame() {
        return frames.get(indexOfCurrentFrame);
    }

    public Frame getFrameByIndex(int index) {
        return frames.get(index);
    }

    public Frame getLastFrame() {
        if (indexOfCurrentFrame == 0) {
            return null;
        }
        return frames.get(indexOfCurrentFrame - 1);
    }

    public Frame getLastLastFrame() {
        if (indexOfCurrentFrame < 2) {
            return null;
        }
        return frames.get(indexOfCurrentFrame - 2);
    }
}
