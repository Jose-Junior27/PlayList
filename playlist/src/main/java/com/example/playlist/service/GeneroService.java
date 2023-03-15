package com.example.playlist.service;

import com.example.playlist.exception.NotFoundException;
import com.example.playlist.model.Genero;
import com.example.playlist.repository.GeneroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GeneroService {
    private GeneroRepository generoRepository;

    public List<Genero> list(){
        //return generoRepository.findAll();
        //return generoRepository.findSortedHQL();
        return generoRepository.findSortedNative();
    }

    public Genero findGeneroByUid(String uid) throws NotFoundException {
        List<Genero> generos = generoRepository.findByUid(uid);
        var optGenero = generos.stream().findFirst();
        return optGenero.orElseThrow(() -> new NotFoundException("UID: "+uid+" nao encontrado."));
    }
}