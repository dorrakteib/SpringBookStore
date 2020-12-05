package com.vermeg.bookstore.controllers;

import com.vermeg.bookstore.entities.CommandLine;
import com.vermeg.bookstore.services.CommandLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/command-line/")
public class CommandLineController {

    @Autowired
    CommandLineService commandLineService;

    @GetMapping("")
    public List<CommandLine> getAllLines() {
        return commandLineService.getAllCommandLines();
    }

    @GetMapping("{id}")
    public CommandLine getLine(@PathVariable Long id){
        return commandLineService.getCommandLineById(id);
    }

    @PostMapping("add/user/{userId}/book/{bookId}")
    public CommandLine addLine(@PathVariable Long bookId,@PathVariable Long userId,
                               @RequestBody @Validated CommandLine c, BindingResult result) {
        if (result.hasErrors())
            System.err.println(result.getAllErrors());
        return commandLineService.addCommandLine(bookId, c, userId);
    }

    @DeleteMapping("{id}")
    public void deleteCommandLine(@PathVariable Long id) throws Exception {
        commandLineService.deleteCommandLine(id);
    }

    @PutMapping("modify/command/{cmdId}/book/{bookId}")
    public CommandLine updateCom(@PathVariable Long cmdId, @PathVariable Long bookId,
                                 @RequestBody @Validated CommandLine c, BindingResult result) throws Exception {
        if (result.hasErrors())
            System.err.println(result.getAllErrors());
        return commandLineService.updateCom(cmdId, bookId, c);
    }
}
