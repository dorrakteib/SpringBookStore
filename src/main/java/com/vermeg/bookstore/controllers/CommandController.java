package com.vermeg.bookstore.controllers;

import com.vermeg.bookstore.entities.Command;
import com.vermeg.bookstore.services.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/command/")
public class CommandController {

    @Autowired
    CommandService commandService;

    @GetMapping("all")
    public List<Command> getAllCommand(){
        return commandService.getAllCommand();
    }

    @GetMapping("{id}")
    public Command getCommandById(@PathVariable Long id) {
        return commandService.getCmdById(id);
    }

    @GetMapping("user/{id}")
    public List<Command> getCommandByUser(@PathVariable Long id) {
        return commandService.getUserCommands(id);
    }

    @PostMapping("new")
    public Command createCommand(@RequestBody @Validated Command c) {
        return commandService.createCommand(c);
    }

    @DeleteMapping("{id}")
    public Command deleteCommand(@PathVariable Long id) {
        return commandService.deleteCommand(id);
    }

    @GetMapping("{id}/total")
    public double windUpCommand(@PathVariable Long id){
        return commandService.windUpCommand(id);
    }
}
