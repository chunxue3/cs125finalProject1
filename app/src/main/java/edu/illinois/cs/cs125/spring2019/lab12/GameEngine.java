package edu.illinois.cs.cs125.spring2019.lab12;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine {
    BackgroundImage backgroundImage;
    Bird bird;
    static int gameState;
    ArrayList<Tube> tubes;
    Random random;
    int score; //current score
    int scoringTube;
    Paint scorePaint;

    public GameEngine(){
        backgroundImage = new BackgroundImage();
        bird = new Bird();
        //0 not start
        //1 playing
        //2 game over
        gameState = 0;
        tubes = new ArrayList<>();
        random = new Random();
        for (int i = 0; i < APPConstants.numberOfTubes; i++) {
            int tubeX = APPConstants.SCREEN_WIDTH + i * APPConstants.distanceBetweenTubes;
            //get topTubeOffsetY
            int topTubeOffsetY = APPConstants.minTubeOffsetY +
                    random.nextInt(APPConstants.maxTubeOffsetY - APPConstants.minTubeOffsetY + 1);
            //create tube object
            Tube tube = new Tube(tubeX, topTubeOffsetY);
            tubes.add(tube);
        }
        score = 0;
        scoringTube = 0;
        scorePaint = new Paint();
        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(100);
        scorePaint.setTextAlign(Paint.Align.LEFT);
    }

    public void updateAndDrawTubes(Canvas canvas) {
        if (gameState == 1) {
            if (tubes.get(scoringTube).getTubeX() < bird.getBirdX() + APPConstants.getBitmapBank().getBirdWidth()
                    && (tubes.get(scoringTube).getTopTubeOffsetY() > bird.getBirdY()
                    || tubes.get(scoringTube).getBottomTubeY() < bird.getBirdY() + APPConstants.getBitmapBank().getBirdHeight())
                    ) {
                //go to game over
                gameState = 2;
                //Log.d("Game", "Over");
                Context context = APPConstants.gameActivityContext;
                Intent intent = new Intent(context, GameOver.class);
                intent.putExtra("score", score);
                context.startActivity(intent);
                ((Activity) context).finish();
            } else if (tubes.get(scoringTube).getTubeX() < bird.getBirdX() - APPConstants.getBitmapBank().getTubeWidth()) {
                score ++;
                scoringTube ++;
                if (scoringTube > APPConstants.numberOfTubes - 1) {
                    scoringTube = 0;
                }
            }
            for (int i = 0; i < APPConstants.numberOfTubes; i++) {
                if (tubes.get(i).getTubeX() < -APPConstants.getBitmapBank().getTubeWidth()) {
                    tubes.get(i).setTubeX(tubes.get(i).getTubeX() +
                            APPConstants.numberOfTubes * APPConstants.distanceBetweenTubes);
                    int topTubeOffsetY = APPConstants.minTubeOffsetY +
                            random.nextInt(APPConstants.maxTubeOffsetY - APPConstants.minTubeOffsetY + 1);
                    tubes.get(i).setTopTubeOffsetY(topTubeOffsetY);
                }
                tubes.get(i).setTubeX(tubes.get(i).getTubeX() - APPConstants.tubeVelocity);
                canvas.drawBitmap(APPConstants.getBitmapBank().getTopTube(), tubes.get(i).getTubeX(), tubes.get(i).getTopTubeY(), null);
                canvas.drawBitmap(APPConstants.getBitmapBank().getBottomTube(), tubes.get(i).getTubeX(), tubes.get(i).getBottomTubeY(), null);
            }
            canvas.drawText("Score: " + score, 0, 110, scorePaint);
        }

    }

    public void updateAndDrawBackgroundImage(final Canvas canvas) {
        backgroundImage.setX(backgroundImage.getX() - backgroundImage.getVelocity());
        if (backgroundImage.getX() < -APPConstants.getBitmapBank().getBackgroundWidth()) {
            backgroundImage.setX(0);
        }
        canvas.drawBitmap(APPConstants.getBitmapBank().getBackground(), backgroundImage.getX(), backgroundImage.getY(), null);
        if (backgroundImage.getX() < -(APPConstants.getBitmapBank().getBackgroundWidth() - APPConstants.SCREEN_WIDTH)) {
            canvas.drawBitmap(APPConstants.getBitmapBank().getBackground(), backgroundImage.getX()
                    + APPConstants.getBitmapBank().getBackgroundWidth(), backgroundImage.getY(), null);
        }
    }

    public void updateAndDrawBird(final Canvas canvas) {
        if (gameState == 1) {
            if (bird.getBirdY() < (APPConstants.SCREEN_HEIGHT - APPConstants.getBitmapBank().getBirdHeight())
                    || bird.getVelocity() < 0) {
                bird.setVelocity(bird.getVelocity() + APPConstants.gravity);
                bird.setBirdY(bird.getBirdY() + bird.getVelocity());
            }
        }
        int currentFrame = bird.getCurrentFrame();
        canvas.drawBitmap(APPConstants.getBitmapBank().getBird(currentFrame),bird.getBirdX(),
                bird.getBirdY(), null);
        currentFrame++;
        //if exceeds max
        if (currentFrame > bird.maxFrame) {
            currentFrame = 0;
        }
        bird.setCurrentFrame(currentFrame);
    }
}
