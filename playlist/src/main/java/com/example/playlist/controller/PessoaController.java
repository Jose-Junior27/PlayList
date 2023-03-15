package com.example.playlist.controller;

import com.example.playlist.dto.FactoryDTO;
import com.example.playlist.dto.PessoaDTO;
import com.example.playlist.dto.PlayListDTO;
import com.example.playlist.exception.NotFoundException;
import com.example.playlist.service.PessoaService;
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

import static com.example.playlist.dto.FactoryDTO.pessoaToDTO;

@RestController
@RequestMapping("/pessoa")
@AllArgsConstructor
@Tag(name = "Pessoas", description = "endpoints de pessoas")
public class PessoaController {
    private PessoaService pessoaService;
    @Operation(summary = "Filtra usuário por UID e suas PlayLists.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informe UID para para filtrar ex: ../pessoa/1dg81... "
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @GetMapping("/{uid}")
    public ResponseEntity<PessoaDTO> getByUid(@PathVariable(value = "uid") String uid ) {
        try {
            return ResponseEntity.ok(pessoaToDTO(pessoaService.findPessoaByUid(uid)));
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Message",e.getMessage()).build();
        }
    }

    @Operation(summary = "Lista todos os Usuários cadastradas da base de dados e suas PlayLists.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informe id para para filtrar por página. ex: ../list?id=1 "
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @GetMapping("/list")
    public ResponseEntity<List<PessoaDTO>> getByAll(@RequestParam( value = "id", defaultValue = "-1") int page){
        try {
            List<PessoaDTO> list = (page < 0 ? pessoaService.list()
                    .stream()
                    .map(FactoryDTO::pessoaToDTO)
                    .collect(Collectors.toList()) :
                    pessoaService.list()
                            .stream()
                            .skip(page)
                            .limit(2)
                            .map(FactoryDTO::pessoaToDTO)
                            .collect(Collectors.toList()));


            return ResponseEntity.ok().header("List-Size", Integer.toString(list.size())).body(list);
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Message",e.getMessage()).build();
        }
    }

    @Operation(summary = "Filtra usuário por email e suas PlayLists.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informe email para para filtrar ex: ../musica/abc@gmail.com "
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<PessoaDTO> getByNome(@PathVariable final String email){
        try {
            return ResponseEntity.ok(pessoaToDTO(pessoaService.pessoaPorEmail(email)));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Message",e.getMessage()).build();
        }
    }

    @Operation(summary = "Cadastrar um Usuário.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informe os dados obrigatórios para cadastrar uma nova usuário "
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @PostMapping()
    public ResponseEntity create(@Valid @RequestBody PessoaDTO pessoaDTO){
        var pessoa = FactoryDTO.dtoToPessoa(pessoaDTO);
        try {
            pessoaService.create(pessoa);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Vincular uma PlayList para um Usuário.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Para vincular uma playlist, informe UID do usuário na requisição e informe UID da playlist no body."
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @PostMapping("/{uid}/playlist/")
    public ResponseEntity add(@PathVariable(value = "uid") String uid, @RequestBody PlayListDTO playListDTO){
        var playList = FactoryDTO.dtoToPlayListUID(playListDTO);
        try {
            pessoaService.addPlayList(uid, playList);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Alterar cadastro de usuário.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informe os dados obrigatórios para alterar dados de um usuário "
            )
    })
    @PutMapping()
    public ResponseEntity update(@Valid @RequestBody PessoaDTO pessoaDTO){
        var pessoa = FactoryDTO.dtoToPessoa(pessoaDTO);
        try{
            pessoaService.update(pessoa);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Excluir um Usuário.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informe o UID para excluir um Usuário "
            ),
            @ApiResponse(responseCode = "400",
                    description = "Erro ao realizar o processamento.")
    })
    @DeleteMapping("{uid}")
    public ResponseEntity delete(@PathVariable(value = "uid") String uid ) {
        try {
            pessoaService.delete(uid);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Message",e.getMessage()).build();
        }
    }


}
