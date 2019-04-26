package edu.illinois.cs.cs125.spring2019.lab12;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {
    private Bitmap background;
    private Bitmap[] bird;
    private Bitmap topTube, bottomTube;

    public BitmapBank(final Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.scroll);
        background = scaleImage(background);
        bird = new Bitmap[2];
        bird[0] = BitmapFactory.decodeResource(res, R.drawable.frame_1);
        bird[1] = BitmapFactory.decodeResource(res, R.drawable.frame_2);
        topTube = BitmapFactory.decodeResource(res, R.drawable.toptube);
        bottomTube = BitmapFactory.decodeResource(res, R.drawable.bottomtube);
    }

    //get top tube
    public Bitmap getTopTube() {
        return topTube;
    }

    //get bottom tube
    public Bitmap getBottomTube() {
        return bottomTube;
    }

    //get tube width
    public int getTubeWidth() {
        return topTube.getWidth();
    }

    //get tube height
    public int getTubeHeight() {
        return topTube.getHeight();
    }

    //return bird bitmap
    public Bitmap getBird(final int index) {
        return bird[index];
    }

    //return birdWidth
    public int getBirdWidth() {
        return bird[0].getWidth();
    }

    //return birdHeight
    public int getBirdHeight() {
        return bird[0].getHeight();
    }


    //Return background bitmap
    public Bitmap getBackground() {
        return background;
    }

    //Return background width
    public int getBackgroundWidth() {
        return background.getWidth();
    }

    //Return background height
    public int getBackgroundHeight() {
        return background.getHeight();
    }

    public Bitmap scaleImage(final Bitmap bitmap) {
        float widthHeightRatio = getBackgroundWidth() / getBackgroundHeight();
        /*
        We'll multiply widthHeightRatio with screenHeight to get scaled width of the bitmap.
        Then call createScaledBitmap() to create a new bitmap, scaled from an existing bitmap, when possible.
         */
        int backgroundScaledWidth = (int) widthHeightRatio * APPConstants.SCREEN_HEIGHT;
        return Bitmap.createScaledBitmap(bitmap, backgroundScaledWidth, APPConstants.SCREEN_HEIGHT, false);
    }
}
