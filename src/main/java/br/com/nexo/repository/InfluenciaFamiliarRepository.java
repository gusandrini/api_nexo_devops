package br.com.nexo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nexo.model.InfluenciaFamiliar;

@Repository
public interface InfluenciaFamiliarRepository extends JpaRepository<InfluenciaFamiliar, Long> {

}
