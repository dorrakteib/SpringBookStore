package com.vermeg.bookstore.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Command {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( updatable = false)
    private Date creationDate = new Date();
    private boolean windedUp = false;
    @ManyToOne
    private User user;

}
