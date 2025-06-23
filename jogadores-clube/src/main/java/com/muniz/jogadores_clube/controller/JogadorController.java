package com.muniz.jogadores_clube.controller;

import com.muniz.jogadores_clube.model.Jogador;
import com.muniz.jogadores_clube.service.JogadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @Operation(summary = "Listar todos os jogadores")
    @GetMapping
    public List<Jogador> listar() {
        return jogadorService.listarTodos();
    }

    @Operation(summary = "Criar um novo jogador")
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Jogador jogador) {
        return jogadorService.criar(jogador);
    }

    @Operation(summary = "Atualizar um jogador pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogador atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Jogador> atualizar(@PathVariable Long id, @RequestBody Jogador dados) {
        return jogadorService.atualizar(id, dados);
    }

    @Operation(summary = "Deletar um jogador pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Jogador deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return jogadorService.deletar(id);
    }
}
