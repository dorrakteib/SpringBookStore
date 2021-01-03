package com.vermeg.bookstore.security.jwt.response;

import com.vermeg.bookstore.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private User user;

    public JwtResponse(String token, User user){
        this.token = token;
        this.user = user;
    }

}
