package com.example.playlist.config;

import com.example.playlist.model.Musica;
import com.example.playlist.repository.GeneroRepository;
import com.example.playlist.repository.MusicaRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
public class MusicaConfig {
    private MusicaRepository musicaRepository;
    private GeneroRepository generoRepository;

    @PostConstruct
    public void initDB() {
        Musica musica = new Musica();
        musica.setNome("Vida Boa");
        musica.setGenero(generoRepository.getReferenceById(Long.parseLong("1")));
        musica.setUid("c5c9dced-cd71-4dcd-b96d-86139a5728c0");
        musicaRepository.saveAndFlush(musica);

        Musica musica2 = new Musica();
        musica2.setNome("Agita Ai");
        musica2.setGenero(generoRepository.getReferenceById(Long.parseLong("1")));
        musica2.setUid("74deedcd-531e-4e2f-90c1-7ac83a703faa");
        musicaRepository.saveAndFlush(musica2);

        Musica musica3 = new Musica();
        musica3.setNome("Aleluia");
        musica3.setGenero(generoRepository.getReferenceById(Long.parseLong("4")));
        musica3.setUid("02742253-88f0-4d6f-830f-6791eae9b838");
        musicaRepository.saveAndFlush(musica3);
    }
}
