package com.duasatuwo.api.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duasatuwo.api.dto.AuthentificationKey;
import com.duasatuwo.api.dto.GetEmail;
import com.duasatuwo.api.dto.ResponseData;
import com.duasatuwo.api.helper.MyHelpers;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.validation.Valid;

import com.duasatuwo.api.user.User;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ResponseData<User>> postUser(@Valid @RequestBody User user, Errors errors) {

        ResponseData<User> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setResult(false);
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        List<User> storeData = (List<User>) userService.findEmail(user.getEmail());

        if (storeData.isEmpty()) {
            responseData.setResult(true);
            List<User> value = new ArrayList<>();
            value.add(userService.save(user));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } else {
            responseData.setResult(false);
            responseData.getMessage().add("Email Sudah Terdaftar");
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseData<User>> fetchUser() {
        ResponseData<User> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<User> value = (List<User>) userService.findAll();
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<User>> fetchUserById(@PathVariable("id") int id) {
        ResponseData<User> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<User> value = new ArrayList<>();
            value.add(userService.findOne(id));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    // @PostMapping("/auth")
    // public ResponseEntity<ResponseData<User>> getStudentAuth(@RequestBody
    // AuthentificationKey authentificationKey) {
    // ResponseData<User> responseData = new ResponseData<>();

    // try {
    // Iterable<User> values = userService.findAuth(authentificationKey.getEmail(),
    // authentificationKey.getPassword());
    // responseData.setResult(true);
    // responseData.getMessage();
    // responseData.setData(values);
    // return ResponseEntity.ok(responseData);

    // } catch (Exception e) {
    // List<String> message = new ArrayList<>();
    // message.add(e.getMessage());
    // responseData.setMessage(message);
    // responseData.setData(null);
    // responseData.setResult(false);
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    // }
    // }

    @PostMapping("/auth")
    public ResponseEntity<Map<String, String>> getStudentAuth(@RequestBody String payload) {
        JsonObject jobj = new Gson().fromJson(payload, JsonObject.class);
        String email = jobj.get("email").getAsString();
        String password = jobj.get("password").getAsString();

        try {
            Iterable<User> values = userService.findAuth(email, password);

            Algorithm algorithm = Algorithm.HMAC256(new MyHelpers().PRIVATE_KEY);
            String access_token = JWT.create()
                    .withSubject(values.toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 10 + 120 * 1000)) // expired 1 minutes
                    .withIssuer("auth0")
                    .sign(algorithm);

            String refresh_token = JWT.create()
                    .withSubject(values.toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 30 + 60 * 1000)) // expired 3 minutes
                    .withIssuer("auth0")
                    .sign(algorithm);

            Map<String, String> results = new HashMap<>();
            results.put("access_token", access_token);
            results.put("refresh_token", refresh_token);

            return ResponseEntity.ok(results);

        } catch (Exception e) {
            List<String> message = new ArrayList<>();
            message.add(e.getMessage());
            Map<String, String> response = new HashMap<>();
            response.put("result", "false");
            response.put("data", "");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/findEmail")
    public ResponseEntity<ResponseData<User>> getEmail(@RequestBody GetEmail getEmail) {
        ResponseData<User> responseData = new ResponseData<>();

        try {
            Iterable<User> values = userService.findEmail(getEmail.getEmail());
            responseData.setResult(true);
            responseData.getMessage();
            responseData.setData(values);
            return ResponseEntity.ok(responseData);

        } catch (Exception e) {
            List<String> message = new ArrayList<>();
            message.add(e.getMessage());
            responseData.setMessage(message);
            responseData.setData(null);
            responseData.setResult(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
}