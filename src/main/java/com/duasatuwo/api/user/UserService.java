package com.duasatuwo.api.user;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.duasatuwo.api.dto.AuthentificationKey;

// import at.favre.lib.crypto.bcrypt.BCrypt;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    UserRepo userRepo;
    PasswordEncoder passwordEncoder;
    AuthentificationKey authentificationKey;
    
    // @Autowired
    // private UserRepo userRepo;
    // private User user;

    public UserService(UserRepo _userRepo){
        this.userRepo = _userRepo;
        // this.authentificationKey = _authentificationKey;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User save(User user){
        String encodedPasssword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPasssword);

        // String bcryptHashString = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        // BCrypt.Result result = BCrypt.verifyer().verify(user.setPassword(bcryptHashString).toCharArray());
        // user.setPassword(bcryptHashString);
        return userRepo.save(user);
    }

    public User findOne(int id){
        return userRepo.findById(id).get();
    }

    public Iterable<User> findAll(){
        return userRepo.findAll();
    }

    public Iterable<User> findAuth(String email, String password){
        password = this.passwordEncoder.encode(password);
        // System.out.println(password);

        // String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
                // String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        // String bcryptHashString = user.getPassword();
        // BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
        // System.out.println(bcryptHashString);
        // if (result.verified == true){
        //     return userRepo.findUserAuth(email, password);
        // } return null;
        
        return userRepo.findUserAuth(email, password);

        // if (password == !authentificationKey.setPassword(encodedPasssword);){
        //     return userRepo.findUserAuth(email, password);
        // }
        // return null;
        
      }
}
