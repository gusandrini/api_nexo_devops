package br.com.nexo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nexo.model.DescricaoCliente;
import br.com.nexo.model.Predicao;

@Repository
public interface PredicaoRepository extends JpaRepository<Predicao, Long> {

	Predicao findFirstByDescricaoClienteOrderByIdPredicaoDesc(DescricaoCliente descricaoCliente);
}
