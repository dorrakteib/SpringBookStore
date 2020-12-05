package com.vermeg.bookstore.services;

import com.vermeg.bookstore.entities.Book;
import com.vermeg.bookstore.entities.Command;
import com.vermeg.bookstore.entities.CommandLine;
import com.vermeg.bookstore.repositories.CommandLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandLineService {

    @Autowired
    BookService bookService;

    @Autowired
    CommandLineRepository commandLineRepository;

    @Autowired
    MyUserService userService;

    @Autowired
    CommandService commandService;

    public List<CommandLine> getAllCommandLines(){
        return commandLineRepository.findAll();
    }

    public CommandLine getCommandLineById(Long id) {
        return commandLineRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(
                "No command line with this id"));
    }

    public CommandLine addCommandLine(Long bookId,  CommandLine commandLine,
                                      Long userId) {
        Book b = bookService.getBookById(bookId);
        Optional<Command> c = commandService.getUserActiveCommand(userId);
        // if the user still has a command which is not winded up
        if (c.isPresent()){ commandLine.setCommand(c.get());
            System.err.println(true);}

        else {
            System.err.println(false);
            // else create a new command
            Command cc =new Command();
            cc.setUser(userService.getUserById(userId));
            commandLine.setCommand(commandService.createCommand(cc));
        }
        commandLine.setBook(b);
        return commandLineRepository.save(commandLine);
    }

    public void deleteCommandLine(Long clId) throws Exception {
        CommandLine c = getCommandLineById(clId);
        if (commandService.getCmdById(c.getCommand().getId()).isWindedUp()) {
            throw new Exception("This command is " +
                    "winded you can't modify its lines");
        }
        if (commandLineRepository.findCommandLinesByCommandId(c.getCommand().getId()).size()==1) commandService.deleteCommand(c.getCommand().getId());
        commandLineRepository.deleteById(clId);
    }

    public CommandLine updateCom(Long coId, Long bId, CommandLine c) throws Exception {
        if (commandService.getCmdById(coId).isWindedUp()) {
            throw new Exception("This command is " +
                    "winded you can't modify its lines");
        }
        CommandLine cl = commandLineRepository.findCommandLineByCommandIdAndBookId(coId, bId);
        cl.setQuantity(c.getQuantity());
        return commandLineRepository.save(cl);
    }

}