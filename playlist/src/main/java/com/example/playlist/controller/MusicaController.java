package com.example.playlist.controller;

import com.example.playlist.dto.FactoryDTO;
import com.example.playlist.dto.MusicaDTO;

import com.example.playlist.dto.PessoaResumoDTO;
import com.example.playlist.exception.NotFoundException;
import com.example.playlist.service.MusicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.playlist.dto.FactoryDTO.musicaToDTO;


@RestController
@RequestMapping("/musica")
@AllArgsConstructor
@Tag(name = "Musicas", description = "endpoints de musicas")
public class MusicaController {

    private MusicaService musicaService;

    @Operation(summary = "Filtra música por UID e os Likes recebidos.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informe UID para para filtrar ex: ../musica/1dg81... "
            ),
            @ApiResponse(responseCode = "400",
                         description = "Erro ao realizar o processamento.")
    })
    @GetMapping("/{uid}")
    public ResponseEntity<MusicaDTO> getByUid(@PathVariable(value = "uid") String uid ) {
        try {
            return ResponseEntity.ok(FactoryDTO.musicaToDTO(musicaService.findMusicaByUid(uid)));
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Message",e.getMessage()).build();
        }
    }

    @Operation(summary = "Lista todas as músicas cadastradas da base de dados e Likes recebidos.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informe id para para filtrar por página. ex: ../list?id=1 "
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @GetMapping("/list")
    public ResponseEntity<List<MusicaDTO>> getByAll(@RequestParam( value = "id", defaultValue = "-1") int page){
        try {
            List<MusicaDTO> list = (page < 0 ? musicaService.list()
                                            .stream()
                                            .map(FactoryDTO::musicaToDTO)
                                            .collect(Collectors.toList()) :
                musicaService.list()
                        .stream()
                        .skip(page)
                        .limit(2)
                        .map(FactoryDTO::musicaToDTO)
                        .collect(Collectors.toList()));


            return ResponseEntity.ok().header("List-Size", Integer.toString(list.size())).body(list);
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Message",e.getMessage()).build();
        }

    }

    @Operation(summary = "Filtra música por nome e Likes recebidos.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informe o nome para para filtrar ex: ../musica/nome_musica "
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @GetMapping("/nome/{nome}")
    public ResponseEntity<MusicaDTO> getByNome(@PathVariable final String nome){
        try {
            return ResponseEntity.ok(musicaToDTO(musicaService.musicaPorNome(nome)));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Message",e.getMessage()).build();
        }
    }

    @Operation(summary = "Cadastrar uma nova Música.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informe os dados obrigatórios para cadastrar uma nova música "
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @PostMapping()
    public ResponseEntity create(@Valid @RequestBody MusicaDTO musicaDTO){
        var musica = FactoryDTO.dtoToMusica(musicaDTO);
        try {
            musicaService.create(musica);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Gerar um Like de Usuário para a Música.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Para dar um like, informe UID da música na requisição e informe UID do usuário no body."
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @PostMapping("/{uid}/like/")
    public ResponseEntity add(@PathVariable(value = "uid") String uid, @RequestBody PessoaResumoDTO pessoaDTO){
        var pessoa = FactoryDTO.dtoToPessoaUID(pessoaDTO);
        try {
            musicaService.addLike(uid, pessoa );
            return ResponseEntity.ok().build();
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Alterar uma Música.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informe os dados obrigatórios para alterar dados de uma música "
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @PutMapping()
    public ResponseEntity update(@Valid @RequestBody MusicaDTO musicaDTO){
        var musica = FactoryDTO.dtoToMusica(musicaDTO);
        try{
            musicaService.update(musica);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @Operation(summary = "Excluir uma Música.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informe o UID para excluir uma música "
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @DeleteMapping("{uid}")
    public ResponseEntity delete(@PathVariable(value = "uid") String uid ) {
        try {
            musicaService.delete(uid);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Message",e.getMessage()).build();
        }
    }

}
