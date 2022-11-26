package com.udacity.jwdnd.bettysavio.course1.cloudstorage.services;

import com.udacity.jwdnd.bettysavio.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/*** AuthenticationService.java is created to provide hook to SpringSecurity ***/



@Service
public class AuthenticationService implements AuthenticationProvider { //AuthenticationProvider is an interface from SpringSecurity
    private final UserMapper userMapper;   // used to check if the user exists in the DB
    private final HashService hashService; //

    // Step 1: dependency injection
    public AuthenticationService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }
    // Step 2: this method is used by SpringSecurity to check credentials it receives from its special login form
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
// Step 2a: Check if a user with this username is in the database
        User user = userMapper.getUserByName(username);
        if (user != null) { // if user exists in db
            String encodedSalt = user.getSalt(); // get users salt from db
            String hashedPassword = hashService.getHashedValue(password, encodedSalt); // hash the user entered password using HashService
            if (user.getPassword().equals(hashedPassword)) {
                //UsernamePasswordAuthenticationToken - get this value from below support() method indicates successful authentication
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            }
        }
        return null;
    }
    //Step 3: To tell Spring which authentication schema to use
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

