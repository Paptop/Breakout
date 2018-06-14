package com.example.user.breakout.level;

import com.example.user.breakout.gameobjects.Background;

public class LevelDescription {
   protected String name;
   protected Background background;
   protected char[] desc;
   protected int width;
   protected int height;

   public String getName() { return this.name; }

   public LevelDescription(String name,
                           char[] description,
                           String background,
                           int width,
                           int height){
       this.name = name;
       this.desc = description;
       this.width = width;
       this.height = height;
       this.background = new Background(background);
   }

}
