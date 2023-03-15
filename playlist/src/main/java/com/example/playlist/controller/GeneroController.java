package com.example.playlist.controller;

import com.example.playlist.dto.FactoryDTO;
import com.example.playlist.dto.GeneroDTO;
import com.example.playlist.exception.NotFoundException;
import com.example.playlist.service.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/genero")
@AllArgsConstructor
@Tag(name = "Genero", description = "endpoints de genero")
public class GeneroController {
    private GeneroService generoService;

    @Operation(summary = "Lista todos os generos da base de dados.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "lista todos os gêneros. "
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @GetMapping("/list")
    public List<GeneroDTO> list(){
        return generoService.list()
                .stream()
                .map(FactoryDTO::generoToDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Filtra um genero por UID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informe UID para para filtrar ex: ../genero/1dg81... "
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @GetMapping("/{uid}")
    public ResponseEntity<GeneroDTO> getByUid(@PathVariable(value = "uid") String uid ) {
        try {
            return ResponseEntity.ok(FactoryDTO.generoToDTO(generoService.findGeneroByUid(uid)));
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Message",e.getMessage()).build();
        }
    }

}
