package com.duasatuwo.api.paket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaketService {

    @Autowired
    private PaketRepo paketRepo;

    public Paket findOne(int id){
        return paketRepo.findById(id).get();
    }

    public Iterable<Paket> findAll(){
        return paketRepo.findAll();
    }
}
