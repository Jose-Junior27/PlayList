package com.example.playlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayListDTO {
    private String uid;
    private String nome;
    private String descricao;
    private Collection<MusicaDTO> musicas = new ArrayList<>();
}
