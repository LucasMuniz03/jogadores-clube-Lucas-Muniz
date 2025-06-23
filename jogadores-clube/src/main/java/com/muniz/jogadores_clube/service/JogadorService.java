package com.muniz.jogadores_clube.service;

import com.muniz.jogadores_clube.model.Clube;
import com.muniz.jogadores_clube.model.Jogador;
import com.muniz.jogadores_clube.repository.ClubeRepository;
import com.muniz.jogadores_clube.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private ClubeRepository clubeRepository;

    public List<Jogador> listarTodos() {
        return jogadorRepository.findAll();
    }

    public ResponseEntity<?> criar(Jogador jogador) {
        if (jogador.getClube() != null && jogador.getClube().getId() != null) {
            Clube clube = clubeRepository.findById(jogador.getClube().getId()).orElse(null);
            if (clube == null) {
                return ResponseEntity.badRequest().body("Clube não encontrado com ID: " + jogador.getClube().getId());
            }
            jogador.setClube(clube);
        }
        return ResponseEntity.ok(jogadorRepository.save(jogador));
    }

    public ResponseEntity<Jogador> atualizar(Long id, Jogador dados) {
        return jogadorRepository.findById(id).map(j -> {
            j.setNome(dados.getNome());
            j.setPosicao(dados.getPosicao());
            if (dados.getClube() != null && dados.getClube().getId() != null) {
                Clube clube = clubeRepository.findById(dados.getClube().getId()).orElse(null);
                j.setClube(clube);
            }
            return ResponseEntity.ok(jogadorRepository.save(j));
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deletar(Long id) {
        return jogadorRepository.findById(id).map(j -> {
            jogadorRepository.delete(j);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
