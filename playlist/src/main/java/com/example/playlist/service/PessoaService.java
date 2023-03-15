package com.example.playlist.service;
import com.example.playlist.exception.NotFoundException;
import com.example.playlist.model.Pessoa;
import com.example.playlist.model.PlayList;
import com.example.playlist.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PessoaService {
    private PessoaRepository pessoaRepository;
    private PlayListService playListService;

    public List<Pessoa> list() throws NotFoundException {
        return pessoaRepository.findAll();
    }

    public Pessoa findPessoaByUid(String uid) throws NotFoundException {
        List<Pessoa> pessoa = pessoaRepository.findByUid(uid);
        var optPessoa = pessoa.stream().findFirst();
        return optPessoa.orElseThrow(() -> new NotFoundException("UID Pessoa: "+uid+" nao encontrado."));
    }

    public void addPlayList(String uidPessoa, PlayList playList) throws NotFoundException{
        Pessoa pessoadb = findPessoaByUid(uidPessoa);
        pessoadb.add(playListService.findPlayListByUid(playList.getUid()));
        pessoaRepository.saveAndFlush(pessoadb);
    }

    public void create(Pessoa pessoa) throws NotFoundException {
        pessoa.setUid(UUID.randomUUID().toString());
        pessoaRepository.saveAndFlush(pessoa);
    }

    public void update(Pessoa pessoa) throws NotFoundException {
        Pessoa pessoadb = findPessoaByUid(pessoa.getUid());
        pessoadb.setNome(pessoa.getNome());
        pessoadb.setEmail(pessoa.getEmail());
        pessoadb.setNascimento(pessoa.getNascimento());
        pessoaRepository.saveAndFlush(pessoadb);
    }

    public void delete(String uid) throws NotFoundException {
        Pessoa pessoadb = findPessoaByUid(uid);
        pessoaRepository.delete(pessoadb);
    }

    public Pessoa pessoaPorEmail(String email) throws NotFoundException {
        return pessoaRepository.findByEmail(email).orElseThrow();
    }


}
