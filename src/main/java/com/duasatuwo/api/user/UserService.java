package com.duasatuwo.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.duasatuwo.api.dto.AuthentificationKey;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

    UserRepo userRepo;
    PasswordEncoder passwordEncoder;
    AuthentificationKey authentificationKey;
    
    // @Autowired
    // private UserRepo userRepo;

    public UserService(UserRepo _userRepo){
        this.userRepo = _userRepo;
        // this.authentificationKey = _authentificationKey;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User save(User user){
        String encodedPasssword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPasssword);
        return userRepo.save(user);
    }

    public User findOne(int id){
        return userRepo.findById(id).get();
    }

    public Iterable<User> findAll(){
        return userRepo.findAll();
    }

    public Iterable<User> findAuth(String email, String password){
        String encodedPasssword = this.passwordEncoder.encode(authentificationKey.getPassword());
        authentificationKey.setPassword(encodedPasssword);
        return userRepo.findUserAuth(email, password);


        // if (password == !authentificationKey.setPassword(encodedPasssword);){
        //     return userRepo.findUserAuth(email, password);
        // }
        // return null;
        
      }
}
