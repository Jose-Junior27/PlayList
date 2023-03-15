package com.example.playlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MusicaDTO {
    private String uid;
    private String nome;
    private GeneroDTO genero;
    private Collection<PessoaResumoDTO> pessoasLike = new HashSet<>();
}
