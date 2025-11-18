package br.com.nexo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.nexo.model.Usuario;
import br.com.nexo.repository.UsuarioRepository;

@Service
public class UsuarioCachingService {

    @Autowired
    private UsuarioRepository repUsuario;

    @Cacheable(value = "usuariosAll")
    public List<Usuario> findAll() {
        return repUsuario.findAll();
    }

    @Cacheable(value = "usuariosById", key = "#idUsuario")
    public Optional<Usuario> findById(Long idUsuario) {
        return repUsuario.findById(idUsuario);
    }
    
    @Cacheable(value = "usuarioByEmail", key = "#nmEmail")
    public Optional<Usuario> findByNmEmail(String nmEmail) {
        return repUsuario.findByNmEmailIgnoreCase(nmEmail);
    }

    @Cacheable(value = "usuariosPage", key = "#req")
    public Page<Usuario> findAll(PageRequest req) {
        return repUsuario.findAll(req);
    }

    @CacheEvict(value = { "usuariosAll", "usuariosById", "usuariosPage", "usuarioByEmail" }, allEntries = true)
    public void limparCache() {
        System.out.println("Limpando cache de usuarios!");
    }
}
