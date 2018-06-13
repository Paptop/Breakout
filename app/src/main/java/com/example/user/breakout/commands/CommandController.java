package com.example.user.breakout.commands;

import com.example.user.breakout.commands.Command;

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
