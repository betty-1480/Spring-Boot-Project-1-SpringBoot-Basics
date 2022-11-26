package com.udacity.jwdnd.bettysavio.course1.cloudstorage.services;

import com.udacity.jwdnd.bettysavio.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.bettysavio.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private UserMapper userMapper;
    private HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService){
        this.userMapper=userMapper;
        this.hashService=hashService;
    }

    public boolean isUserNameAvailable(String userName){
        return userMapper.getUserByName(userName)!=null;
    }

    public User getUser(String userName){
        return userMapper.getUserByName(userName);
    }

    public int createUser(User user){
    // Step1: get a salt
        SecureRandom secureRandom=new SecureRandom(); // This class provides a cryptographically strong random number generator
        byte[] salt=new byte[16]; // a byte array to hold salt
        secureRandom.nextBytes(salt); // salt is filled in
        String encodedSalt = Base64.getEncoder().encodeToString(salt); //converted that to 64byte

    // Step2: hash the password using the salt & HashService
        String hashedPassword = hashService.getHashedValue(user.getPassword(),encodedSalt);

    // Step3: insert User in the database using UserMapper
        //return userMapper.createUser(user); //java.lang.NullPointerException: Cannot invoke "String.getBytes()" because "salt" is null
        return userMapper.createUser(new User(null,user.getUsername(),encodedSalt,hashedPassword,user.getFirstname(),user.getLastname()));
    }

}
