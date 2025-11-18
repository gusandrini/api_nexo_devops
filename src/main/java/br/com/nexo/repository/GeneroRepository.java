package br.com.nexo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nexo.model.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {

}
