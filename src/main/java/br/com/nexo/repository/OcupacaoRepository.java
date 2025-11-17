package br.com.nexo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.nexo.model.Ocupacao;

public interface OcupacaoRepository extends JpaRepository<Ocupacao, Long> {
    // Optional custom finders can be added here
}
