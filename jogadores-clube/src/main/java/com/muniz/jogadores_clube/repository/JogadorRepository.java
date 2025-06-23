package com.muniz.jogadores_clube.repository;

import com.muniz.jogadores_clube.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {}
