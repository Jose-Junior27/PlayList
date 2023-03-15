package com.example.playlist.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="Genero")
@Getter
@Setter
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String uid;
    private String nome;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genero")
    private Collection<Musica> musicas = new ArrayList<>();



}
