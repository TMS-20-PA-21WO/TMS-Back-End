package com.duasatuwo.api.pemesanan;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PemesananRepo extends CrudRepository<Pemesanan, Integer> {
    
    @Query(value = "SELECT a.* FROM pemesanan a WHERE a.id_user = :id_user", nativeQuery = true)
    public Iterable<Pemesanan> findPemesananUser(@Param("id_user") int id_user );

    @Query(value = "SELECT a.* FROM pemesanan a WHERE a.id_user = :id_user AND a.id_package = :id_package AND a.date = :date", nativeQuery = true)
    public Iterable<Pemesanan> doValidation(@Param("id_user") int id_user, @Param("id_package") int id_package, @Param("date") String date);
}
