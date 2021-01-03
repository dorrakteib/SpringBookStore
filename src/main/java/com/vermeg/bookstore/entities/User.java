package com.vermeg.bookstore.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"email", "userName"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "The first name can't be empty") @Size(min=3, message = "the first name's " +
            "length must be at least 3")
    private String firstName;
    @NotNull(message = "The last name can't be empty") @Size(min=3, message = "last name's length" +
            "must be at least 3")
    private String lastName;
    @NotNull(message = "The email is required") @Email(message = "Invalid email")
    private String email;
    @Size(min=8,max = 8, message = "The phone number must have exctly 8 digits") @Pattern(regexp
            = "[0-9]{8}", message = "The phone number must have exctly 8 digits")
    private String tel;
    @NotNull(message = "The username can't be empty") @Size(min=3, message = "the title's length " +
            "must be at least 3")
    private String userName;
    @NotNull(message = "The password can't be empty") @Size(min=4, message = "the password's " +
            "length " +
            "must be at least 8")
    private String password;
    @NotNull
    private String roles;
    private boolean active;


    public User(Long id) {
        this.id = id;
    }

    public User(String userName, String password, String roles, boolean active) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.active = active;
    }
}
