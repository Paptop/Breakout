package com.example.user.breakout;

public class PlayerMoveRight implements Command{
   private PlayerPaddle player;
   private float acc = 0;

   public PlayerMoveRight(PlayerPaddle paddle){
       this.player = paddle;
   }

    @Override
    public void execute() {
       if( player.coord.x + player.width < Constants.SCREEN_WIDTH) {
           player.currentState = PlayerState.MOVING_RIGHT;
           player.coord.x += 20 + acc;
           acc += 10f;
       }
    }

    @Override
    public void refresh() {
        acc = 0f;
    }
}
