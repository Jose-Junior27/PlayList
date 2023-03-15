package com.example.playlist.service;

import com.example.playlist.exception.NotFoundException;
import com.example.playlist.model.Genero;
import com.example.playlist.model.Musica;
import com.example.playlist.model.Pessoa;
import com.example.playlist.repository.MusicaRepository;
import com.example.playlist.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MusicaService {

    private MusicaRepository musicaRepository;
    private GeneroService generoService;
    private PessoaRepository pessoaRepository;

    public List<Musica> list() throws NotFoundException{
        return musicaRepository.findAll();
    }

    public Musica musicaPorNome(String nome) throws NotFoundException {
        return musicaRepository.findByNome(nome).orElseThrow();
    }

    public Musica findMusicaByUid(String uid) throws NotFoundException {
        List<Musica> musicas = musicaRepository.findByUid(uid);
        var optMusica = musicas.stream().findFirst();
        return optMusica.orElseThrow(() -> new NotFoundException("UID: "+uid+" nao encontrado."));
    }

    public void addLike(String uidMusica, Pessoa pessoa) throws NotFoundException{
        Musica musicadb = findMusicaByUid(uidMusica);
        musicadb.add(pessoaRepository.findByUid(pessoa.getUid()).get(0));
        musicaRepository.saveAndFlush(musicadb);
    }

    public void create(Musica musica) throws NotFoundException {
        musica.setUid(UUID.randomUUID().toString());
        Genero genero = generoService.findGeneroByUid(musica.getGenero().getUid());
        musica.setGenero(genero);
        musicaRepository.saveAndFlush(musica);
    }

    public void update(Musica musica) throws NotFoundException {
        Musica musicadb = findMusicaByUid(musica.getUid());
        musicadb.setNome(musica.getNome());
        var generodb = generoService.findGeneroByUid(musica.getGenero().getUid());
        musicadb.setGenero(generodb);
        musicaRepository.saveAndFlush(musicadb);
    }

    public void delete(String uid) throws NotFoundException {
        Musica musicadb = findMusicaByUid(uid);
        musicaRepository.delete(musicadb);
    }

}
