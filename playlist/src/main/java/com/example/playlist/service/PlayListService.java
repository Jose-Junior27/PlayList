package com.example.playlist.service;

import com.example.playlist.exception.NotFoundException;
import com.example.playlist.model.Musica;
import com.example.playlist.model.PlayList;
import com.example.playlist.repository.PlayListRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PlayListService {
    private PlayListRepository playListRepository;
    private MusicaService musicaService;

    public List<PlayList> list() throws NotFoundException {
        return playListRepository.findAll();
    }

    public Page<PlayList> findAllByAtivoTrue(Pageable paginacao) throws NotFoundException {
        return playListRepository.findAll(paginacao);
    }

    public PlayList findPlayListByUid(String uid) throws NotFoundException {
        List<PlayList> playlist = playListRepository.findByUid(uid);
        var optPlayList = playlist.stream().findFirst();
        return optPlayList.orElseThrow(() -> new NotFoundException("UID PlayList: "+uid+" nao encontrado."));
    }

    public void create(PlayList playList) throws NotFoundException {
        playList.setUid(UUID.randomUUID().toString());
        playListRepository.saveAndFlush(playList);
    }

    public void addMusic(String uidPlayList, Musica musica) throws NotFoundException{
        PlayList playListdb = findPlayListByUid(uidPlayList);
        playListdb.add(musicaService.findMusicaByUid(musica.getUid()));
        playListRepository.saveAndFlush(playListdb);
    }

    public void update(PlayList playList) throws NotFoundException {
        PlayList playListdb = findPlayListByUid(playList.getUid());
        playListdb.setNome(playList.getNome());
        playListdb.setDescricao(playList.getDescricao());
        playListRepository.saveAndFlush(playListdb);
    }

    public void delete(String uid) throws NotFoundException {
        PlayList playListdb = findPlayListByUid(uid);
        playListRepository.delete(playListdb);
    }
}
