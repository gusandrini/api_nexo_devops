package br.com.nexo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nexo.model.Ocupacao;

@Repository
public interface OcupacaoRepository extends JpaRepository<Ocupacao, Long> {
    // Optional custom finders can be added here
}
