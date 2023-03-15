package com.example.playlist.repository;

import com.example.playlist.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface MusicaRepository extends JpaRepository<Musica, Long> {

    Optional<Musica> findByNome(String nome);

    @Query("SELECT m FROM Musica m JOIN FETCH m.genero a WHERE m.uid = :uid")
    List<Musica> findByUid(@Param("uid") String uid);

}
