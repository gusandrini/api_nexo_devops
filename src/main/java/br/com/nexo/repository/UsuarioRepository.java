package br.com.nexo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nexo.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNmEmail(String nmEmail);
    Optional<Usuario> findByNmEmailIgnoreCase(String nmEmail);
}
