package com.example.playlist.repository;

import com.example.playlist.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

    //Query HQL
    @Query(value = "SELECT g FROM Genero g ORDER BY g.nome")
    List<Genero> findSortedHQL();

    //Query Nativa
    @Query(value = "SELECT * FROM Genero ORDER BY nome", nativeQuery = true)
    List<Genero> findSortedNative();

    @Query("SELECT g FROM Genero g WHERE g.uid = :uid")
    List<Genero> findByUid(@Param("uid") String uid);
}
