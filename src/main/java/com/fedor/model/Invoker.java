package com.fedor.model;

import java.util.ArrayList;
import java.util.List;

public class Invoker {
    private final List<Command> commands;

    public Invoker() {
        this.commands = new ArrayList<>();
    }

    public void addCommand(Command command) {
        this.commands.add(command);
    }

    public void executeCommands() {
        this.commands.forEach(Command::execute);
    }
}
