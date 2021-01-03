package com.vermeg.bookstore.controllers;

import com.vermeg.bookstore.entities.MyUserDetails;
import com.vermeg.bookstore.entities.User;
import com.vermeg.bookstore.security.jwt.JwtUtils;
import com.vermeg.bookstore.security.jwt.response.JwtResponse;
import com.vermeg.bookstore.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserRestController {


    @Autowired
    MyUserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user, BindingResult result) throws Exception {
        if (result.hasErrors())
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/admin/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> addAdmin(@RequestBody User user, BindingResult result) throws Exception {
        if (result.hasErrors())
            System.err.println(result.getAllErrors());
        return new ResponseEntity<>(userService.addAdmin(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, new User(userDetails.getMyUser().getId(),
                userDetails.getMyUser().getFirstName(),userDetails.getMyUser().getLastName(),
                userDetails.getMyUser().getEmail(),  userDetails.getMyUser().getTel(),
                userDetails.getUsername(), userDetails.getPassword(),
                userDetails.getMyUser().getRoles(), userDetails.getMyUser().isActive())
                ));
    }
}
