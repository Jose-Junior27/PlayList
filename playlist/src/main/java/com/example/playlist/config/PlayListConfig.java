package com.example.playlist.config;

import com.example.playlist.model.PlayList;
import com.example.playlist.repository.MusicaRepository;
import com.example.playlist.repository.PlayListRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
public class PlayListConfig {
    private PlayListRepository playListRepository;
    private MusicaRepository musicaRepository;

    @PostConstruct
    public void initDB() {

        PlayList playList = new PlayList();
        playList.setNome("As Mais Tocadas de 2022");
        playList.setDescricao("Top 100 Sertanejas");
        playList.setUid("77b04f89-86a9-4ea4-9c82-60d93052b8f0");
        playList.add(musicaRepository.findByUid("c5c9dced-cd71-4dcd-b96d-86139a5728c0").get(0) );
        playList.add(musicaRepository.findByUid("74deedcd-531e-4e2f-90c1-7ac83a703faa").get(0) );
        playListRepository.saveAndFlush(playList);

        PlayList playList2 = new PlayList();
        playList2.setNome("Só Hits");
        playList2.setDescricao("Os Hits Mais Tocados do Momento");
        playList2.setUid("39390a56-a70d-4168-b5e1-0d8e739a644d");
        playListRepository.saveAndFlush(playList2);

    }
}
