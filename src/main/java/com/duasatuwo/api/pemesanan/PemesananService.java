package com.duasatuwo.api.pemesanan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PemesananService {
    
    @Autowired
    private PemesananRepo pemesananRepo;

    public Pemesanan save(Pemesanan pemesanan){
        return pemesananRepo.save(pemesanan);
    }

    public Pemesanan findOne(int id){
        return pemesananRepo.findById(id).get();
    }    

    public Iterable<Pemesanan> findAll(){
        return pemesananRepo.findAll();
    }

    public void removeOne(int id){
        pemesananRepo.deleteById(id);
    }
}
