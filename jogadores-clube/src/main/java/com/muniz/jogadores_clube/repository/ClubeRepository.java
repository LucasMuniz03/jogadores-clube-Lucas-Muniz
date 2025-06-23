package com.muniz.jogadores_clube.repository;

import com.muniz.jogadores_clube.model.Clube;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubeRepository extends JpaRepository<Clube, Long> {}
