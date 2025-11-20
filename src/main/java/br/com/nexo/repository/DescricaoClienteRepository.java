package br.com.nexo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nexo.model.DescricaoCliente;

@Repository
public interface DescricaoClienteRepository extends JpaRepository<DescricaoCliente, Long> {
	java.util.List<DescricaoCliente> findByUsuarioNmEmailIgnoreCase(String nmEmail);
}
