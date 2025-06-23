package com.muniz.jogadores_clube.service;

import com.muniz.jogadores_clube.model.Clube;
import com.muniz.jogadores_clube.repository.ClubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubeService {

    @Autowired
    private ClubeRepository clubeRepository;

    public List<Clube> listarTodos() {
        return clubeRepository.findAll();
    }

    public Clube criar(Clube clube) {
        return clubeRepository.save(clube);
    }

    public ResponseEntity<Clube> atualizar(Long id, Clube dados) {
        return clubeRepository.findById(id).map(c -> {
            c.setNome(dados.getNome());
            return ResponseEntity.ok(clubeRepository.save(c));
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deletar(Long id) {
        return clubeRepository.findById(id).map(c -> {
            clubeRepository.delete(c);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
