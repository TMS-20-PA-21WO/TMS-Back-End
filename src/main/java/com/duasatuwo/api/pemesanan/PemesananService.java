package com.duasatuwo.api.pemesanan;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Iterable<Pemesanan> findPemesananUser(int id_user){
        return pemesananRepo.findPemesananUser(id_user);
    }

    public Iterable<Pemesanan> doValidation(int id_user, int id_package, String date){
        return pemesananRepo.doValidation(id_user, id_package, date);
    }
}
