package com.example.user.breakout.hud;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.user.breakout.Constants;
import com.example.user.breakout.gameobjects.GObject;
import com.example.user.breakout.gameobjects.PlayerPaddle;
import com.example.user.breakout.graphics.AssetManager;
import com.example.user.breakout.level.Level;
import com.example.user.breakout.level.LevelDescription;

public class Hud implements GObject{

    private LevelDescription description;
    private Level level;
    private Paint bg = new Paint();
    private Paint levelPaint = new Paint();
    private Paint scorePaint = new Paint();
    private Paint paint = new Paint();
    private Bitmap currentHealth;

    public LevelDescription getDescription() { return description; }
    public void setDescription(LevelDescription description) { this.description = description; }

    private Bitmap health1;
    private Bitmap health2;
    private Bitmap health3;
    private Bitmap health4;
    private Bitmap background;

    public Hud(Level level){
        this.level = level;
        this.description = level.currentLevel;
        this.health1 = AssetManager.getInstance().getRecource("Health1");
        this.health2 = AssetManager.getInstance().getRecource("Health2");
        this.health3 = AssetManager.getInstance().getRecource("Health3");
        this.health4 = AssetManager.getInstance().getRecource("Health4");
        this.background = AssetManager.getInstance().getRecource("GuiBg");
        this.currentHealth = health4;

        bg.setARGB(255,0,255,0);
        levelPaint.setARGB(255,255,255,255);
        levelPaint.setTextSize(100);
        levelPaint.setTextAlign(Paint.Align.CENTER);

        scorePaint.setARGB(255,255,255,255);
        scorePaint.setTextSize(100);
        scorePaint.setTextAlign(Paint.Align.LEFT);

        paint.setARGB(255,255,255,255);
        //livePaint.setTextSize(100);
        //livePaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override
    public void draw(Canvas canvas) {
        //canvas.drawRect(0,0,Constants.SCREEN_WIDTH, Constants.GUI_OFFSET,bg);
        canvas.drawBitmap(background,0,0,paint);
        canvas.drawText(description.getName(),525,150, levelPaint);
        canvas.drawText("Score : 1000000",150,240, scorePaint);
        //canvas.drawText("Lives :",150,300, livePaint);
        canvas.drawBitmap(currentHealth,Constants.SCREEN_WIDTH / 2 - Constants.GUI_HIT_POINTS_WIDTH / 2,
                Constants.GUI_OFFSET - (Constants.GUI_OFFSET / 3 + 10) ,paint);
    }

    public void update(int hitpoints){
        switch(hitpoints){
            case 1:
                currentHealth = health1;
                break;
            case 2:
                currentHealth = health2;
                break;
            case 3:
                currentHealth = health3;
                break;
            case 4:
                currentHealth = health4;
                break;

        }
    }

    @Override
    public void tick() {

    }
}
