package br.com.nexo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nexo.model.Funcao;

public interface FuncaoRepository extends JpaRepository<Funcao, Long> {
}
