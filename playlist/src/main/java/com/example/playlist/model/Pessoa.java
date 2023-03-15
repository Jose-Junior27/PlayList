package com.example.playlist.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Getter
@Setter
@Table(name = "Pessoa")
public class Pessoa{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uid;
    @NotBlank
    @Size(min = 3, message = "Informe Nome Maior")
    private String nome;
    @Email(message = "e-mail inválido.")
    private String email;
    @NotNull(message = "Campo Data de Nascimento é obrigatório")
    @Past(message = "Data precisa ser menor que atual")
    private LocalDate nascimento;

    @ManyToMany(mappedBy = "pessoasLike")
    private Collection<Musica> musicasLike = new HashSet<>();

    /*@ManyToMany(fetch = FetchType.LAZY)
    private Collection<PlayList> playLists = new ArrayList<>();*/

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "pessoa_playlist",
            joinColumns = { @JoinColumn(name = "playlist_id") },
            inverseJoinColumns = { @JoinColumn(name = "pessoa_id") },
            uniqueConstraints = {@UniqueConstraint(columnNames = {"playlist_id", "pessoa_id"})}
    )
    private Collection<PlayList> playLists = new HashSet<>();

    public void add(PlayList playList) {
        if (playList != null) {
            this.playLists.add(playList);
        }
    }
}