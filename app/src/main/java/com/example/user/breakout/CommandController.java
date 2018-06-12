package com.example.user.breakout;

public class CommandController {
    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }
    public Command getCommand() {return this.command; }
    public void pressButton(){
        command.execute();
    }
}
