package net.example;

/**
 * Keep reading
 */
public class Pins {
    private int standingPins = 10;

    public int numberOfStandingPins() {
        return standingPins;
    }

    public void subtractStandingPins(int pinsHit){
        standingPins = numberOfStandingPins() - pinsHit;
    }
}
