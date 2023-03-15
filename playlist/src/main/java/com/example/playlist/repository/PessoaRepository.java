package com.example.playlist.repository;
import com.example.playlist.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByEmail(String email);
    @Query("SELECT p FROM Pessoa p WHERE p.uid = :uid")
    List<Pessoa> findByUid(@Param("uid") String uid);
}
