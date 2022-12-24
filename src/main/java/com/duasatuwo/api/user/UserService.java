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

    public UserService(UserRepo _userRepo) {
        this.userRepo = _userRepo;
        // this.authentificationKey = _authentificationKey;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User save(User user) {
        String encodedPasssword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPasssword);

        return userRepo.save(user);
    }

    public User findOne(int id) {
        return userRepo.findById(id).get();
    }

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public Iterable<User> findAuth(String email, String password) {
        String passwordEncode = this.passwordEncoder.encode(password);
        if (passwordEncoder.matches(password, passwordEncode)) {
            return userRepo.findUserAuth(email, password);
        }

        return null;

    }
}
