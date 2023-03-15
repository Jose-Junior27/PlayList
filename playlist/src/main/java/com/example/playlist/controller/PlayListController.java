package com.example.playlist.controller;

import com.example.playlist.dto.FactoryDTO;
import com.example.playlist.dto.MusicaDTO;
import com.example.playlist.dto.PlayListDTO;
import com.example.playlist.exception.NotFoundException;
import com.example.playlist.service.PlayListService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.playlist.dto.FactoryDTO.playListToDTO;

@RestController
@AllArgsConstructor
@RequestMapping("/playlist")
@OpenAPIDefinition(
        info = @Info(
                title = "PlayLists JJ",
                description = "Endpoint - PlayList",
                version = "1.000",
                contact = @Contact(name = "José Junior", url = "jj.bb.com.br", email = "junior.jm.sj25@gmail.com")
        ))
@Tag(name = "PlayList", description = "endpoints de playlists")
public class PlayListController {
    private PlayListService playListService;

    @Operation(
            summary = "Filtra playlist por UID e músicas relacionadas.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informe UID para para filtrar ex: ../playlist/1dg81... "
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @GetMapping("/{uid}")
    public ResponseEntity<PlayListDTO> getByUid(@PathVariable(value = "uid") String uid ) {
        try {
            return ResponseEntity.ok(playListToDTO(playListService.findPlayListByUid(uid)));
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Message",e.getMessage()).build();
        }
    }

    @Operation(summary = "Lista com paginação de todas as PlayLists cadastradas na base de dados e musicas relacionadas.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informe page para para filtrar por página. ex: ../list?page=1 "
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @GetMapping("/list")
    public ResponseEntity<Page<PlayListDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        try {
            var page = playListService.findAllByAtivoTrue(paginacao).map(FactoryDTO::playListToDTO);
            return ResponseEntity.ok(page);
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Message",e.getMessage()).build();
        }
    }

    @Operation(summary = "Cadastrar uma nova PlayList.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informe os dados obrigatórios para cadastrar uma nova PlayList "
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @PostMapping()
    public ResponseEntity create(@Valid @RequestBody PlayListDTO playListDTO){
        var playList = FactoryDTO.dtoToPlayList(playListDTO);
        try {
            playListService.create(playList);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Vincular uma Música à PlayList.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Para vincular uma música a playlist, informe UID da playlist na requisição e informe UID da musica no body."
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @PostMapping("/{uid}/musica/")
    public ResponseEntity add(@PathVariable(value = "uid") String uid, @RequestBody MusicaDTO musicaDTO){
        var music = FactoryDTO.dtoToMusicaUID(musicaDTO);
        try {
            playListService.addMusic(uid, music);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Alterar uma PlayList.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informe os dados obrigatórios para alterar dados de uma playList "
            )
    })
    @PutMapping()
    public ResponseEntity update(@Valid @RequestBody PlayListDTO playListDTO){
        var playList = FactoryDTO.dtoToPlayList(playListDTO);
        try{
            playListService.update(playList);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Excluir uma PlayList.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informe o UID para excluir uma PlayList "
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @DeleteMapping("{uid}")
    public ResponseEntity delete(@PathVariable(value = "uid") String uid ) {
        try {
            playListService.delete(uid);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Message",e.getMessage()).build();
        }
    }


}
