package com.example.playlist.config;

import lombok.AllArgsConstructor;
import com.example.playlist.model.Genero;
import org.springframework.context.annotation.Configuration;
import com.example.playlist.repository.GeneroRepository;
import javax.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
public class GeneroConfig {
    private GeneroRepository generoRepository;

    @PostConstruct
    public void initDB(){
        Genero genero1 = new Genero();
        genero1.setNome("Sertanejo");
        genero1.setUid("7a582795-3797-46ec-9a0a-ea0981320923");
        generoRepository.saveAndFlush(genero1);

        Genero genero2 = new Genero();
        genero2.setNome("Popular");
        genero2.setUid("001d22d6-28e0-4aff-a103-456c5b2cbfc9");
        generoRepository.saveAndFlush(genero2);

        Genero genero3 = new Genero();
        genero3.setNome("Rock");
        genero3.setUid("be5a943e-29e5-4cf5-b014-aa09907f4f8d");
        generoRepository.saveAndFlush(genero3);

        Genero genero4 = new Genero();
        genero4.setNome("Gospel");
        genero4.setUid("e0dfe368-3c5f-4502-b9be-efc46098c6e4");
        generoRepository.saveAndFlush(genero4);

        Genero genero5 = new Genero();
        genero5.setNome("Funk");
        genero5.setUid("cfdb2913-8af5-4ac7-aa24-407b10316e4a");
        generoRepository.saveAndFlush(genero5);

        Genero genero6 = new Genero();
        genero6.setNome("Samba");
        genero6.setUid("377e064a-c2de-11ed-8112-325096b39f47");
        generoRepository.saveAndFlush(genero6);

        Genero genero7 = new Genero();
        genero7.setNome("Rap");
        genero7.setUid("5b5158d8-c2de-11ed-a52a-325096b39f47");
        generoRepository.saveAndFlush(genero7);

        Genero genero8 = new Genero();
        genero8.setNome("MPB");
        genero8.setUid("5b5158d8-c2de-11ed-a52a-325854139f47");
        generoRepository.saveAndFlush(genero8);

        Genero genero9 = new Genero();
        genero9.setNome("Pagode");
        genero9.setUid("8fabad5e-c2de-11ed-a696-325096b39f47");
        generoRepository.saveAndFlush(genero9);

        Genero genero10 = new Genero();
        genero10.setNome("Forro");
        genero10.setUid("8fabad5e-c2de-11ed-a696-325074128f47");
        generoRepository.saveAndFlush(genero10);

        Genero genero11 = new Genero();
        genero11.setNome("Jazz");
        genero11.setUid("b1cbf9d4-c2de-11ed-aab0-325096b39f47");
        generoRepository.saveAndFlush(genero11);

        Genero genero12 = new Genero();
        genero12.setNome("Eletrônica");
        genero12.setUid("b1cbf9d4-c2de-11ed-aab0-325085726662");
        generoRepository.saveAndFlush(genero12);

        Genero genero13 = new Genero();
        genero13.setNome("Axé");
        genero13.setUid("ecd75f96-c2de-11ed-a514-325096b39f47");
        generoRepository.saveAndFlush(genero13);

    }
}
