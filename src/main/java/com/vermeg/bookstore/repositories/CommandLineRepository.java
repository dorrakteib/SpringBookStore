package com.vermeg.bookstore.repositories;


import com.vermeg.bookstore.entities.CommandLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommandLineRepository extends JpaRepository<CommandLine, Long> {

    public List<CommandLine> findCommandLinesByCommandId(Long comId);

    public CommandLine findCommandLineByCommandIdAndBookId(Long comId, Long bookId);

    public void deleteCommandLinesByCommandId(Long id);
}
