package com.duasatuwo.api.paket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duasatuwo.api.dto.ResponseData;

@RestController
@RequestMapping("/api/paket")
public class PaketController {
    
    @Autowired
    private PaketService paketService;

    @GetMapping
    public ResponseEntity<ResponseData<Paket>> fetchPaket() {
        ResponseData<Paket> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<Paket> value = (List<Paket>) paketService.findAll();
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Paket>> fetchPaketById(@PathVariable("id") int id) {
        ResponseData<Paket> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<Paket> value = new ArrayList<>();
            value.add(paketService.findOne(id));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
}
