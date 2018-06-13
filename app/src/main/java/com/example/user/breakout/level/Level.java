package com.example.user.breakout.level;

import android.graphics.Canvas;

import com.example.user.breakout.Constants;
import com.example.user.breakout.gameobjects.EmptyPaddle;
import com.example.user.breakout.gameobjects.GObject;
import com.example.user.breakout.gameobjects.Paddle;
import com.example.user.breakout.graphics.AssetManager;

import java.util.ArrayList;
import java.util.Random;

public class Level implements GObject {
    protected ArrayList<Paddle> paddles;
    protected int width, height;

    public Random rand;
    public ArrayList<Paddle> getLevel() { return paddles;}

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

    public void generateLevel(LevelDescription description) {
        // Clear the old level
        paddles.clear();
        char[] array = description.desc;
        for (char c : array) {
            switch (c) {
                case 'r':
                    paddles.add(new Paddle(this, 0, 0,
                            "PRed"));
                    break;
                case 'b':
                    paddles.add(new Paddle(this, 0, 0,
                            "PBlue"));
                    break;
                case 'g':
                    paddles.add(new Paddle(this, 0, 0,
                            "PGreen"));
                    break;
                case 'o':
                    paddles.add(new Paddle(this, 0, 0,
                            "PGold"));
                    break;
                case 'p':
                    paddles.add(new Paddle(this, 0, 0,
                            "PPurple"));
                    break;

                case 'R':
                    paddles.add(new Paddle(this, 0, 0,
                            "PaddleRed"));
                    break;
                case 'B':
                    paddles.add(new Paddle(this, 0, 0,
                            "PaddleBlue"));
                    break;
                case 'G':
                    paddles.add(new Paddle(this, 0, 0,
                            "PaddleGreen"));
                    break;
                case 'O':
                    paddles.add(new Paddle(this, 0, 0,
                            "PaddleGold"));
                    break;
                case 'P':
                    paddles.add(new Paddle(this, 0, 0,
                            "PaddlePurple"));
                    break;
                case '#':
                    paddles.add(new EmptyPaddle(this));
                    break;
            }
        }
        paddles = bindCoordinates(paddles,description.width,description.height);

    }

    public ArrayList<Paddle> bindCoordinates(ArrayList<Paddle> level, int width, int height){
        for(int i = 0 ; i < height; i ++) {
            for (int j = 0; j < width; j++) {
                int index = i * width + j;
                Paddle p = level.get(index);
                level.get(index).getCoord().x = j * (Constants.DEFAULT_PADDLE_WIDTH + 5) + Constants.DEFAULT_PADDLE_WIDTH / 2;
                level.get(index).getCoord().y = i * (Constants.DEFAULT_PADDLE_HEIGHT + 5);
            }
        }
        for(int i = level.size() - 1; i >= 0; i--){
            if (EmptyPaddle.class.isInstance(level.get(i)) ) {
                System.out.println(" REMOVING ");
                level.remove(i);
            }
        }
        return level;
    }

    public Level(){
        this.width = Constants.SCREEN_WIDTH / Constants.DEFAULT_PADDLE_WIDTH - 1;
        this.height = Constants.DEFAULT_LEVEL_HEIGHT + 5;
        this.rand = new Random();
        //this.height = Constants.SCREEN_HEIGHT / Constants.DEFAULT_PADDLE_HEIGHT;
        this.paddles = new ArrayList<>();
        generateLevel(new LevelDescription("Level : Mars ",Level1,9, 18));
        /*
        for(int i = 0; i < height; i++) {
            if(i > Constants.DEFAULT_LEVEL_HEIGHT){
                for(int j = 0; j < width; j += 3){
                    paddles.add(new Paddle(this, j * (Constants.DEFAULT_PADDLE_WIDTH + 10) + Constants.DEFAULT_PADDLE_WIDTH / 2,
                            i * (Constants.DEFAULT_PADDLE_HEIGHT + 10), 255, rand.nextInt(255), rand.nextInt(255),
                            rand.nextInt(255)));
                }
            }else {
                for (int j = 0; j < width; j++) {
                    paddles.add(new Paddle(this, j * (Constants.DEFAULT_PADDLE_WIDTH + 10) + Constants.DEFAULT_PADDLE_WIDTH / 2,
                            i * (Constants.DEFAULT_PADDLE_HEIGHT + 10), 255, rand.nextInt(255), rand.nextInt(255),
                            rand.nextInt(255)));
                }
            }
        }
        */
    }

    @Override
    public void draw(Canvas canvas) {
        for(Paddle p : paddles){
            p.draw(canvas);
        }
    }

    @Override
    public void tick() {

    }
}
