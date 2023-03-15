package com.example.playlist.dto;

import com.example.playlist.model.Genero;
import com.example.playlist.model.Musica;
import com.example.playlist.model.Pessoa;
import com.example.playlist.model.PlayList;

import java.util.ArrayList;
import java.util.Collection;

public class FactoryDTO {
    public static MusicaDTO musicaToDTO(Musica musica){
        if (musica == null){
            return null;
        }
        return new MusicaDTO(musica.getUid(), musica.getNome(), generoToDTO(musica.getGenero()), collectionPessoaToDTO(musica.getPessoasLike()) );

    }

    public static Musica dtoToMusica(MusicaDTO musicaDTO){
        var musica = new Musica();
        musica.setUid(musicaDTO.getUid());
        musica.setNome(musicaDTO.getNome());
        musica.setGenero(dtoToGenero( musicaDTO.getGenero()) );
        return musica;
    }

    public static Musica dtoToMusicaUID(MusicaDTO musicaDTO){
        var musica = new Musica();
        musica.setUid(musicaDTO.getUid());
        return musica;
    }

    public static Collection<MusicaDTO> collectionMusicaToDTO(Collection<Musica> musicas){
        if (musicas == null){
            return null;
        }
        Collection<MusicaDTO> list = new ArrayList<>();
        musicas.stream().forEach(musica -> {
            list.add(new MusicaDTO(musica.getUid(), musica.getNome(), generoToDTO(musica.getGenero()), collectionPessoaToDTO(musica.getPessoasLike()) ));
        });

        return list;

    }

    public static GeneroDTO generoToDTO(Genero genero) {
        if (genero == null){
            return null;
        }
        return new GeneroDTO(genero.getUid(), genero.getNome());
    }

    public static Genero dtoToGenero(GeneroDTO generoDTO){
        var genero = new Genero();
        genero.setUid(generoDTO.getUid());
        genero.setNome(generoDTO.getNome());
        return genero;
    }

    public static PessoaDTO pessoaToDTO(Pessoa pessoa){
        if (pessoa == null){
            return null;
        }
        return new PessoaDTO(pessoa.getUid(), pessoa.getNome(), pessoa.getEmail(), pessoa.getNascimento(), collectionPlayListToDTO(pessoa.getPlayLists()) );
    }

    public static Pessoa dtoToPessoa(PessoaDTO pessoaDTO){
        var pessoa = new Pessoa();
        pessoa.setUid(pessoaDTO.getUid());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setEmail(pessoaDTO.getEmail());
        pessoa.setNascimento(pessoaDTO.getNascimento());
        return pessoa;
    }

    public static PlayListDTO playListToDTO(PlayList playList){
        if (playList == null){
            return null;
        }
        return new PlayListDTO(playList.getUid(), playList.getNome(), playList.getDescricao(), collectionMusicaToDTO(playList.getMusicas())) ;
    }

    public static PlayList dtoToPlayList(PlayListDTO playListDTO){
        var playList = new PlayList();
        playList.setUid(playListDTO.getUid());
        playList.setNome(playListDTO.getNome());
        playList.setDescricao(playListDTO.getDescricao());
        //playList.setMusicas(playListDTO.getMusicas());
        return playList;
    }

    public static PlayList dtoToPlayListUID(PlayListDTO playListDTO){
        var playList = new PlayList();
        playList.setUid(playListDTO.getUid());
        return playList;
    }

    public static Collection<PlayListDTO> collectionPlayListToDTO(Collection<PlayList> playLists){
        if (playLists == null){
            return null;
        }
        Collection<PlayListDTO> list = new ArrayList<>();
        playLists.stream().forEach(play -> {
            list.add(new PlayListDTO(play.getUid(), play.getNome(), play.getDescricao(), collectionMusicaToDTO( play.getMusicas()) ) );
        });

        return list;

    }

    public static Pessoa dtoToPessoaUID(PessoaResumoDTO pessoaDTO){
        var pessoa = new Pessoa();
        pessoa.setUid(pessoaDTO.getUid());
        return pessoa;
    }

    public static Collection<PessoaResumoDTO> collectionPessoaToDTO(Collection<Pessoa> pessoas){
        if (pessoas == null){
            return null;
        }
        Collection<PessoaResumoDTO> list = new ArrayList<>();
        pessoas.stream().forEach(p -> {
            list.add(new PessoaResumoDTO(p.getUid(), p.getNome() ) );
        });
        return list;

    }

}
