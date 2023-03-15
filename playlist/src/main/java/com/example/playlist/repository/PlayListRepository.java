package com.example.playlist.repository;

import com.example.playlist.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayListRepository extends JpaRepository<PlayList, Long> {

    //@Query("SELECT p FROM PlayList p JOIN FETCH p.musicas WHERE p.uid = :uid")
    @Query("SELECT p FROM PlayList p WHERE p.uid = :uid")
    List<PlayList> findByUid(@Param("uid") String uid);
}
