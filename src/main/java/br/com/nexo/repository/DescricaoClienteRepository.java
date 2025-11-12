package br.com.nexo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nexo.model.DescricaoCliente;

public interface DescricaoClienteRepository extends JpaRepository<DescricaoCliente, Long> {
}
