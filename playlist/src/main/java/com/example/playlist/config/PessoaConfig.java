package com.example.playlist.config;

import com.example.playlist.model.Pessoa;
import com.example.playlist.repository.PessoaRepository;
import com.example.playlist.repository.PlayListRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Configuration
@AllArgsConstructor
public class PessoaConfig {
    private PessoaRepository pessoaRepository;
    private PlayListRepository playListRepository;

    @PostConstruct
    public void initDB() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("José Santos");
        pessoa.setEmail("josejunior@gmail.com");
        pessoa.setNascimento(LocalDate.of(1990,11,20));
        pessoa.setUid("8859dced-cd71-4dcd-b96d-86139a5728ty");
        //pessoa.add(playListRepository.findByUid("77b04f89-86a9-4ea4-9c82-60d93052b8f0").get(0) );
        pessoaRepository.saveAndFlush(pessoa);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Lucas Henrique");
        pessoa2.setEmail("lucashenrique@gmail.com");
        pessoa2.setNascimento(LocalDate.of(1992,10,15));
        pessoa2.setUid("7419dced-cd71-4dcd-b96d-86139a572ttp");
        pessoaRepository.saveAndFlush(pessoa2);
    }
}
