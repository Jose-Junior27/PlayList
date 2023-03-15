package com.example.playlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PessoaDTO {
    private String uid;
    private String nome;
    private String email;
    private LocalDate nascimento;
    private Collection<PlayListDTO> playLists = new ArrayList<>();
}
