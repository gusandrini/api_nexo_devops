package br.com.nexo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.nexo.dto.UsuarioDTO;
import br.com.nexo.model.Usuario;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioCachingService cacheUsuario;

    @Transactional(readOnly = true)
    public Page<UsuarioDTO> paginar(PageRequest req) {
        Page<Usuario> paginas = cacheUsuario.findAll(req);
        Page<UsuarioDTO> paginasDto = paginas.map(usuario -> new UsuarioDTO(usuario));
        return paginasDto;
    }
}
