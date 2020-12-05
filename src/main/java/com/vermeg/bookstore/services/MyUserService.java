package com.vermeg.bookstore.services;

import com.vermeg.bookstore.entities.MyUserDetails;
import com.vermeg.bookstore.entities.User;
import com.vermeg.bookstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user= userRepository.getUserByUserName(userName);
        user.orElseThrow(()-> new UsernameNotFoundException("No user with the username "+userName));
        // return user.map(MyUserDetails ::new).get();
        return new MyUserDetails(user.get());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No " +
                "user found with the ID "+ id));
    }
}
