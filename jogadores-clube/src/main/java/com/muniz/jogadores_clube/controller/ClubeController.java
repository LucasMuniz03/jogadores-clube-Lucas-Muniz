package com.muniz.jogadores_clube.controller;

import com.muniz.jogadores_clube.model.Clube;
import com.muniz.jogadores_clube.service.ClubeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubes")
public class ClubeController {

    @Autowired
    private ClubeService clubeService;

    @Operation(summary = "Listar todos os clubes")
    @GetMapping
    public List<Clube> listar() {
        return clubeService.listarTodos();
    }

    @Operation(summary = "Criar um novo clube")
    @PostMapping
    public Clube criar(@RequestBody Clube clube) {
        return clubeService.criar(clube);
    }

    @Operation(summary = "Atualizar um clube existente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clube atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Clube não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Clube> atualizar(@PathVariable Long id, @RequestBody Clube dados) {
        return clubeService.atualizar(id, dados);
    }

    @Operation(summary = "Deletar um clube pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Clube deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Clube não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return clubeService.deletar(id);
    }
}
