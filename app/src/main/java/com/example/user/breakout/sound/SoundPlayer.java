package com.example.user.breakout.sound;

import android.media.AudioManager;
import android.media.SoundPool;

import com.example.user.breakout.Constants;
import com.example.user.breakout.R;

public class SoundPlayer extends Thread {


    private static SoundPool soundPool;
    private static int playerhitsound;
    private static int paddlehitsound;
    private static int wallSound;
    private static int playermovesound;
    private static int levelpass;
    private static int gameover;
    private static int healthloss;


    public SoundPlayer(){
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        playerhitsound = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.playercol,1);
        playermovesound = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.playermove,1);
        paddlehitsound = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.paddlecol,1);
        wallSound = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.paddlecol2,1);
        levelpass  = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.levelpass,1);
        gameover = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.levellose,1);
        healthloss = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.healthloss, 1);

    }



    public static void playPlayerHitSound(){
        soundPool.play(playerhitsound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public static void playPaddleHitSound(){
        soundPool.play(paddlehitsound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public static void playPlayerMoveSound(){
        soundPool.play(playermovesound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public static void playLevelPass(){
        soundPool.play(levelpass, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public static void playGameOver(){
        soundPool.play(gameover, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public static void healthloss(){
        soundPool.play(healthloss, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public static void playWallSound(){
        soundPool.play(wallSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
