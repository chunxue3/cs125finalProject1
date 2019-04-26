package edu.illinois.cs.cs125.spring2019.lab12;

import java.util.Random;

public class Tube {
    private int tubeX, topTubeOffsetY; // x-coordinate of tube and bottom position of top tube
    private Random random;
    public Tube (final int tubeX, final int topTubeOffsetY) {
        this.tubeX = tubeX;
        this.topTubeOffsetY = topTubeOffsetY;
        random = new Random();
    }

    //get top tube offset Y
    public int getTopTubeOffsetY() {
        return topTubeOffsetY;
    }

    //get tube x
    public int getTubeX() {
        return tubeX;
    }

    public int getTopTubeY() {
        return topTubeOffsetY - APPConstants.getBitmapBank().getTubeHeight();
    }

    public int getBottomTubeY() {
        return topTubeOffsetY + APPConstants.gap_between_tubes;
    }

    public void setTubeX(final int tubeX) {
        this.tubeX = tubeX;
    }

    public void setTopTubeOffsetY(final int topTubeOffsetY) {
        this.topTubeOffsetY = topTubeOffsetY;
    }
}
