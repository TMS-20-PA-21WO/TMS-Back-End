package com.duasatuwo.api.pemesanan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duasatuwo.api.dto.PemesananByUser;
import com.duasatuwo.api.dto.ResponseData;
import com.duasatuwo.api.dto.Validation;
import com.duasatuwo.api.helper.MyHelpers;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/pemesanan")
public class PemesananController {

    @Autowired
    private PemesananService pemesananService;

    @PostMapping
    public ResponseEntity<ResponseData<Pemesanan>> postPemesanan(@Valid @RequestBody Pemesanan pemesanan, Errors errors,
    @RequestHeader(value = "Authorization", required = false) String Authorization) {

        ResponseData<Pemesanan> responseData = new ResponseData<>();

        if (Authorization != null){
            Map<String,Object> decoded = new MyHelpers().DecodeJWT(Authorization);
            boolean result = (boolean) decoded.get("result");
            if (!result){
                List<String> message = new ArrayList<>();
                message.add(decoded.get("message").toString());
                responseData.setMessage(message);
                responseData.setData(null);
                responseData.setResult(false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            } else {
                if (errors.hasErrors()) {
                    for (ObjectError error : errors.getAllErrors()) {
                        responseData.getMessage().add(error.getDefaultMessage());
                    }
        
                    responseData.setResult(false);
        
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
                }
        
                List<Pemesanan> storeData = (List<Pemesanan>) pemesananService.doValidation(pemesanan.getUser().getId(),
                        pemesanan.getDate());
        
                if (storeData.isEmpty()) {
                    responseData.setResult(true);
                    List<Pemesanan> value = new ArrayList<>();
                    value.add(pemesananService.save(pemesanan));
                    responseData.setData(value);
        
                    return ResponseEntity.ok(responseData);
                } else {
                    responseData.setResult(false);
                    responseData.getMessage().add("Anda Sudah Memesan Tanggal Ini");
                    responseData.setData(null);
        
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
                }
            }
        } else {
            List<String> message = new ArrayList<>();
            message.add("Required request header 'Authorization'");
            responseData.setMessage(message);
            responseData.setData(null);
            responseData.setResult(false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }
        
    }

    @GetMapping
    public ResponseEntity<ResponseData<Pemesanan>> fetchPemesanan(@RequestHeader(value = "Authorization", required = false) String Authorization) {
        
        ResponseData<Pemesanan> responseData = new ResponseData<>();

        if (Authorization != null){
            Map<String,Object> decoded = new MyHelpers().DecodeJWT(Authorization);
            boolean result = (boolean) decoded.get("result");
            if (!result){
                List<String> message = new ArrayList<>();
                message.add(decoded.get("message").toString());
                responseData.setMessage(message);
                responseData.setData(null);
                responseData.setResult(false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            } else {
                try {
                    responseData.setResult(true);
                    List<Pemesanan> value = (List<Pemesanan>) pemesananService.findAll();
                    responseData.setData(value);

                    return ResponseEntity.ok(responseData);
                } catch (Exception ex) {
                    responseData.setResult(false);
                    responseData.getMessage().add(ex.getMessage());

                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
                }
            }
        } else {
            List<String> message = new ArrayList<>();
            message.add("Required request header 'Authorization'");
            responseData.setMessage(message);
            responseData.setData(null);
            responseData.setResult(false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Pemesanan>> fetchPemesananById(@PathVariable("id") int id, @RequestHeader(value = "Authorization", required = false) String Authorization) {
        
        ResponseData<Pemesanan> responseData = new ResponseData<>();

        if (Authorization != null){
            Map<String,Object> decoded = new MyHelpers().DecodeJWT(Authorization);
            boolean result = (boolean) decoded.get("result");
            if (!result){
                List<String> message = new ArrayList<>();
                message.add(decoded.get("message").toString());
                responseData.setMessage(message);
                responseData.setData(null);
                responseData.setResult(false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            } else {
                try {
                    responseData.setResult(true);
                    List<Pemesanan> value = new ArrayList<>();
                    value.add(pemesananService.findOne(id));
                    responseData.setData(value);

                    return ResponseEntity.ok(responseData);
                } catch (Exception ex) {
                    responseData.setResult(false);
                    responseData.getMessage().add(ex.getMessage());

                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
                }
            }
        } else {
            List<String> message = new ArrayList<>();
            message.add("Required request header 'Authorization'");
            responseData.setMessage(message);
            responseData.setData(null);
            responseData.setResult(false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseData<Pemesanan>> updatePemesanan(@Valid @RequestBody Pemesanan pemesanan, Errors errors,
    @RequestHeader(value = "Authorization", required = false) String Authorization) {

        ResponseData<Pemesanan> responseData = new ResponseData<>();

        if (Authorization != null){
            Map<String,Object> decoded = new MyHelpers().DecodeJWT(Authorization);
            boolean result = (boolean) decoded.get("result");
            if (!result){
                List<String> message = new ArrayList<>();
                message.add(decoded.get("message").toString());
                responseData.setMessage(message);
                responseData.setData(null);
                responseData.setResult(false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            } else {
                if (pemesanan.getId() != 0) {
                    if (errors.hasErrors()) {
                        for (ObjectError error : errors.getAllErrors()) {
                            responseData.getMessage().add(error.getDefaultMessage());
                        }
                        responseData.setResult(false);
                        responseData.setData(null);

                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
                    }
                    List<Pemesanan> storeData = (List<Pemesanan>) pemesananService.doValidation(pemesanan.getUser().getId(),
                            pemesanan.getDate());

                    if (storeData.isEmpty() || storeData.get(0).getId() == pemesanan.getId()) {
                        responseData.setResult(true);
                        List<Pemesanan> value = new ArrayList<>();
                        value.add(pemesananService.save(pemesanan));
                        responseData.setData(value);

                        return ResponseEntity.ok(responseData);
                    } else {
                        responseData.setResult(false);
                        responseData.getMessage().add("Anda Sudah Memesan Tanggal Ini");
                        responseData.setData(null);

                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
                    }
                } else {
                    responseData.getMessage().add("ID is Required");
                    responseData.setResult(false);

                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
                }
            }
        } else {
            List<String> message = new ArrayList<>();
            message.add("Required request header 'Authorization'");
            responseData.setMessage(message);
            responseData.setData(null);
            responseData.setResult(false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deletePemesananById(@PathVariable("id") int id,
    @RequestHeader(value = "Authorization", required = false) String Authorization) {

        ResponseData<Void> responseData = new ResponseData<>();

        if (Authorization != null){
            Map<String,Object> decoded = new MyHelpers().DecodeJWT(Authorization);
            boolean result = (boolean) decoded.get("result");
            if (!result){
                List<String> message = new ArrayList<>();
                message.add(decoded.get("message").toString());
                responseData.setMessage(message);
                responseData.setData(null);
                responseData.setResult(false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            } else {
                try {
                    pemesananService.removeOne(id);
                    responseData.setResult(true);
                    responseData.getMessage().add("Successfully Remove");

                    return ResponseEntity.ok(responseData);
                } catch (Exception ex) {
                    responseData.setResult(false);
                    responseData.getMessage().add(ex.getMessage());

                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
                }
            }
        } else {
            List<String> message = new ArrayList<>();
            message.add("Required request header 'Authorization'");
            responseData.setMessage(message);
            responseData.setData(null);
            responseData.setResult(false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }
    }

    @PostMapping("/pemesananUser")
    public ResponseEntity<ResponseData<Pemesanan>> getPemesananUser(@RequestBody PemesananByUser pemesananByUser,
    @RequestHeader(value = "Authorization", required = false) String Authorization) {

        ResponseData<Pemesanan> responseData = new ResponseData<>();

        if (Authorization != null){
            Map<String,Object> decoded = new MyHelpers().DecodeJWT(Authorization);
            boolean result = (boolean) decoded.get("result");
            if (!result){
                List<String> message = new ArrayList<>();
                message.add(decoded.get("message").toString());
                responseData.setMessage(message);
                responseData.setData(null);
                responseData.setResult(false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            } else {
                try {
                    Iterable<Pemesanan> values = pemesananService.findPemesananUser(pemesananByUser.getId_user());
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
        } else {
            List<String> message = new ArrayList<>();
            message.add("Required request header 'Authorization'");
            responseData.setMessage(message);
            responseData.setData(null);
            responseData.setResult(false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }
    }

    @PostMapping("/validation")
    public ResponseEntity<ResponseData<Pemesanan>> getStudentAuth(@RequestBody Validation validation,
    @RequestHeader(value = "Authorization", required = false) String Authorization) {

        ResponseData<Pemesanan> responseData = new ResponseData<>();

        if (Authorization != null){
            Map<String,Object> decoded = new MyHelpers().DecodeJWT(Authorization);
            boolean result = (boolean) decoded.get("result");
            if (!result){
                List<String> message = new ArrayList<>();
                message.add(decoded.get("message").toString());
                responseData.setMessage(message);
                responseData.setData(null);
                responseData.setResult(false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            } else {
                try {
                    Iterable<Pemesanan> values = pemesananService.doValidation(validation.getId_user(),
                            validation.getDate());
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
        } else {
            List<String> message = new ArrayList<>();
            message.add("Required request header 'Authorization'");
            responseData.setMessage(message);
            responseData.setData(null);
            responseData.setResult(false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }
    }
}