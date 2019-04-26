package edu.illinois.cs.cs125.spring2019.lab12;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class APPConstants {
    static BitmapBank bitmapBank; // Bitmap object reference
    static GameEngine gameEngine; // GameEngine object reference
    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int gravity;
    static int velocity_when_jumped;
    static int gap_between_tubes;
    static int numberOfTubes;
    static int tubeVelocity;
    static int minTubeOffsetY;
    static int maxTubeOffsetY;
    static int distanceBetweenTubes;
    static Context gameActivityContext;

    public static void initialization(Context context){
        setScreenSize(context);
        bitmapBank = new BitmapBank(context.getResources());
        setGameConstants();
        gameEngine = new GameEngine();
    }

    public static void setGameConstants() {
        APPConstants.gravity = 3;
        APPConstants.velocity_when_jumped = -40;
        APPConstants.gap_between_tubes = 600;
        APPConstants.numberOfTubes = 2;
        APPConstants.tubeVelocity = 12;
        APPConstants.minTubeOffsetY = (int) (APPConstants.gap_between_tubes / 2.0);
        APPConstants.maxTubeOffsetY = APPConstants.SCREEN_HEIGHT - APPConstants.minTubeOffsetY -
                APPConstants.gap_between_tubes;
        APPConstants.distanceBetweenTubes = APPConstants.SCREEN_WIDTH * 3 / 4;
    }

    // Return BitmapBank instance
    public static BitmapBank getBitmapBank(){
        return bitmapBank;
    }

    // Return GameEngine instance
    public static GameEngine getGameEngine(){
        return gameEngine;
    }

    private static void setScreenSize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        APPConstants.SCREEN_WIDTH = width;
        APPConstants.SCREEN_HEIGHT = height;
    }
}
