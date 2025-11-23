package br.com.nexo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.nexo.dto.UsuarioDTO;
import br.com.nexo.model.Usuario;
import br.com.nexo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioCachingService cacheUsuario;

    @Autowired
    private UsuarioRepository usuarioRepository; // ✅ necessário para buscar por email

    @Transactional(readOnly = true)
    public Page<UsuarioDTO> paginar(PageRequest req) {
        Page<Usuario> paginas = cacheUsuario.findAll(req);
        Page<UsuarioDTO> paginasDto = paginas.map(usuario -> new UsuarioDTO(usuario));
        return paginasDto;
    }

    // ============================================================
    // 🔥 MÉTODO NECESSÁRIO PARA O LOGIN DO MOBILE
    // ============================================================
    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository
                .findByNmEmailIgnoreCase(email)
                .orElse(null);
    }
}
