package com.example.user.breakout.level;

import android.graphics.Canvas;
import android.os.Vibrator;

import com.example.user.breakout.Constants;
import com.example.user.breakout.gameobjects.Ball;
import com.example.user.breakout.gameobjects.GObject;
import com.example.user.breakout.gameobjects.Paddle;
import com.example.user.breakout.gameobjects.PlayerPaddle;
import com.example.user.breakout.hud.Hud;
import com.example.user.breakout.sound.SoundPlayer;

import java.util.ArrayList;
import java.util.Random;

import static android.support.v4.content.ContextCompat.getSystemService;

public class Level implements GObject {

// All game objects in current level
    protected Ball ball;
    protected PlayerPaddle player;
    protected ArrayList<GObject> levelObjects;

// HUD
    protected Hud hud;

// Level stuff
    protected ArrayList<Paddle> paddles;
    protected int width, height;
    public Random rand;
    public ArrayList<Paddle> getLevel() { return paddles;}
    public LevelDescription currentLevel;

// Sound



    public PlayerPaddle getPlayer() { return player; }
    public Ball getBall() { return ball; }

    private char[] Level1 = {
        'g', 'g', 'g', 'g' , 'g', 'g', 'g', 'g','g',
        'r', 'b', 'r', 'b' , 'r', 'b', 'r', 'b','r',
        '#', 'o', '#', 'o' , '#', 'o', '#', 'o', '#',
        'o', '#', 'o', '#' , 'o', '#', 'o', '#', 'o',
        'r', 'r', 'r', 'r' , 'r', 'r', 'r', 'r', 'r',
        '#', 'r', 'r', 'r', 'r' , 'r', 'r', 'r', '#',
        '#', '#', 'r', 'r', 'r' , 'r', 'r', '#', '#',
        '#', '#', '#', 'r', 'r' , 'r', '#', '#', '#',
        '#', '#', '#', '#', 'r' , '#', '#', '#', '#',
        'g', 'g', 'g', 'g' , 'g', 'g', 'g', 'g','g',
        'b', 'b', 'b', 'b' , 'b', 'b', 'b', 'b','b',
        'p', 'p', 'p', 'p' , 'p', 'p', 'p', 'p','p',
        '#', '#', 'r', 'r', 'r' , 'r', 'r', '#', '#',
        '#', 'r', 'r', 'r', 'r' , 'r', 'r', 'r', '#',
        'r', 'r', 'r', 'r' , 'r', 'r', 'r', 'r', 'r',
        '#', 'r', 'r', 'r', 'r' , 'r', 'r', 'r', '#',
        '#', '#', 'r', 'r', 'r' , 'r', 'r', '#', '#',
        '#', '#', '#', 'r', 'r' , 'r', '#', '#', '#',
    };


    private char[] Level2 = {

       '#', '#', '#', '#', 'r' , '#', '#', '#', '#',
       '#', '#', '#', 'r', 'r' , 'r', '#', '#', '#',
       '#', '#', 'r', 'r', 'b' , 'r', 'r', '#', '#',
       '#', 'r', 'r', 'b', 'o' , 'b', 'r', 'r', '#',
       'r', 'r', 'b', 'o', 'o',  'o', 'b', 'r', 'r',
       '#', 'r', 'r', 'b', 'o' , 'b', 'r', 'r', '#',
       '#', '#', 'r', 'r', 'b' , 'r', 'r', '#', '#',
       '#', '#', '#', 'r', 'r' , 'r', '#', '#', '#',
       '#', '#', '#', '#', 'r' , '#', '#', '#', '#',

    };

    private char[] DebugLevel = {
        '#', 'r', '#', '#', '#' , '#', '#', '#', '#',
    };

    private LevelDescription level1Mars;
    private LevelDescription debugLevel;
    private LevelDescription level2Waterfall;

    private ArrayList<LevelDescription> levelQueue;

    public LevelDescription generateLevel(LevelDescription description) {
        // Clear the old level
        paddles.clear();
        initGameObjects();

        char[] array = description.desc;
        int height = description.height;
        int width = description.width;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int index = i * width + j;
                int x = j * (Constants.DEFAULT_PADDLE_WIDTH + 5) + Constants.DEFAULT_PADDLE_WIDTH / 2;
                int y = i * (Constants.DEFAULT_PADDLE_HEIGHT + 5) + Constants.GUI_OFFSET;
                switch (array[index]) {
                    case 'r':
                        paddles.add(new Paddle(this, x, y,
                                "PRed"));
                        break;
                    case 'b':
                        paddles.add(new Paddle(this, x, y,
                                "PBlue"));
                        break;
                    case 'g':
                        paddles.add(new Paddle(this, x, y,
                                "PGreen"));
                        break;
                    case 'o':
                        paddles.add(new Paddle(this, x, y,
                                "PGold"));
                        break;
                    case 'p':
                        paddles.add(new Paddle(this, x, y,
                                "PPurple"));
                        break;

                    case 'R':
                        paddles.add(new Paddle(this, x, y,
                                "PaddleRed"));
                        break;
                    case 'B':
                        paddles.add(new Paddle(this, x, y,
                                "PaddleBlue"));
                        break;
                    case 'G':
                        paddles.add(new Paddle(this, x, y,
                                "PaddleGreen"));
                        break;
                    case 'O':
                        paddles.add(new Paddle(this, x, y,
                                "PaddleGold"));
                        break;
                    case 'P':
                        paddles.add(new Paddle(this, x, y,
                                "PaddlePurple"));
                        break;
                    case '#':
                        //paddles.add(new EmptyPaddle(this));
                        break;
                }
            }
        }

        return description;

    }

    public void damageToPlayer(){
        try {
            SoundPlayer.healthloss();
            Thread.sleep(2000);
            if(player.damagePlayer()){
                SoundPlayer.playGameOver();
                // Goto state end;
            }else{
                hud.update(player.getHitpoints());
            }
        }catch(InterruptedException exc){exc.printStackTrace(); }
        initGameObjects();
    }

    public void initGameObjects(){
       levelObjects.clear();
       ball.coord.x = 200; ball.coord.y = 1200;
       levelObjects.add(ball);
       levelObjects.add(player);
    }



    public Level(){
        Constants.LEVEL_WIDTH = Constants.SCREEN_WIDTH;
        Constants.LEVEL_HEIGHT = Constants.SCREEN_HEIGHT - Constants.GUI_OFFSET;

        this.paddles = new ArrayList<>();
        this.levelQueue = new ArrayList<>();
        this.player = new PlayerPaddle(this);
        this.ball = new Ball(this, 200, 1200,player);
        this.levelObjects = new ArrayList<>();

        // Check how many bricks in a row and a col screen can handle
        this.width = Constants.SCREEN_WIDTH / Constants.DEFAULT_PADDLE_WIDTH - 1;
        this.height = Constants.DEFAULT_LEVEL_HEIGHT + 5;

        // Init level queue
        level1Mars = new LevelDescription("Level : Mars", Level1, "MarsBg", 9, 18);
        debugLevel = new LevelDescription(" Level : Debug ", DebugLevel, "MarsBg", 9, 1);
        // Set level1 as default
        currentLevel = generateLevel(debugLevel);
        this.hud = new Hud(this);

        level2Waterfall = new LevelDescription("Level : WaterFall", Level2, "MarsBg", 9, 9);
        //levelQueue.add(level1Mars);
        levelQueue.add(level2Waterfall);
        levelQueue.add(level1Mars);
    }


    public void switchLevel(){
        levelQueue.add(currentLevel);
        currentLevel = levelQueue.remove(0);
        hud.setDescription(currentLevel);
        try {
            Thread.sleep(3000);
        }catch(InterruptedException exc){exc.printStackTrace(); }
    }


    @Override
    public void draw(Canvas canvas) {
        currentLevel.background.draw(canvas);
        hud.draw(canvas);
        for(Paddle p : paddles){
            p.draw(canvas);
        }
        for(GObject o : levelObjects){
            o.draw(canvas);
        }
    }

    @Override
    public void tick() {
        hud.tick();
        if(paddles.size() == 0){
            switchLevel();
            generateLevel(currentLevel);
            return;
        }
        for (int i = 0; i < levelObjects.size(); i++) {
            levelObjects.get(i).tick();
        }

    }
}
