package com.example.user.breakout.level;

public class LevelDescription {
   protected String name;
   protected char[] desc;
   protected int width;
   protected int height;

   public LevelDescription(String name, char[] description, int width, int height){
       this.name = name;
       this.desc = description;
       this.width = width;
       this.height = height;
   }

}
