package com.duasatuwo.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepo userRepo;

    public User save(User user){
        return userRepo.save(user);
    }

    public User findOne(int id){
        return userRepo.findById(id).get();
    }

    public Iterable<User> findAll(){
        return userRepo.findAll();
    }

    public Iterable<User> findAuth(String email, String password){
        return userRepo.findUserAuth(email, password);
      }
}
