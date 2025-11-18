package br.com.nexo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nexo.model.NivelEducacional;

@Repository
public interface NivelEducacionalRepository extends JpaRepository<NivelEducacional, Long> {

}
