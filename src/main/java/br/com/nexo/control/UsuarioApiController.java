package br.com.nexo.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import br.com.nexo.model.Usuario;
import br.com.nexo.repository.UsuarioRepository;
import br.com.nexo.service.UsuarioCachingService;
import br.com.nexo.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioApiController {

    @Autowired
    private UsuarioRepository repUsuario;

    @Autowired
    private UsuarioCachingService cacheUsuario;

    @Autowired
    private UsuarioService servUsuario;

    @GetMapping("/todos_usuarios")
    public List<Usuario> retornaTodosUsuarios() {
        List<Usuario> usuarios = repUsuario.findAll();
        usuarios.forEach(u -> {
            if (u.getFuncoes() != null) {
                u.getFuncoes().forEach(f -> f.setUsuarios(null));
            }
        });
        return usuarios;
    }

    @GetMapping("/todos_cacheable")
    public List<Usuario> retornaTodosUsuariosCacheable() {
        List<Usuario> usuarios = cacheUsuario.findAll();
        usuarios.forEach(u -> {
            if (u.getFuncoes() != null) {
                u.getFuncoes().forEach(f -> f.setUsuarios(null));
            }
        });
        return usuarios;
    }

    @GetMapping("/paginados")
    public ResponseEntity<?> paginarUsuarios(
            @RequestParam(value = "pagina", defaultValue = "0") Integer page,
            @RequestParam(value = "tamanho", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(servUsuario.paginar(PageRequest.of(page, size)));
    }

    @GetMapping("/{id_usuario}")
    public Usuario retornaUsuarioPorID(@PathVariable Long id_usuario) {
        Optional<Usuario> op = cacheUsuario.findById(id_usuario);
        if (op.isPresent()) {
            Usuario usuario = op.get();
            if (usuario.getFuncoes() != null) {
                usuario.getFuncoes().forEach(f -> f.setUsuarios(null));
            }
            return usuario;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/inserir")
    public Usuario inserirUsuario(@Valid @RequestBody Usuario usuario) {
        repUsuario.save(usuario);
        cacheUsuario.limparCache();
        if (usuario.getFuncoes() != null) {
            usuario.getFuncoes().forEach(f -> f.setUsuarios(null));
        }
        return usuario;
    }

    @PutMapping("/atualizar/{id_usuario}")
    public Usuario atualizarUsuario(@Valid @RequestBody Usuario usuario, @PathVariable Long id_usuario) {
        Optional<Usuario> op = cacheUsuario.findById(id_usuario);
        if (op.isPresent()) {
            Usuario usuarioAtual = op.get();
            usuarioAtual.setNmCliente(usuario.getNmCliente());
            usuarioAtual.setNmEmail(usuario.getNmEmail());
            usuarioAtual.setNmSenha(usuario.getNmSenha());
            usuarioAtual.setFuncoes(usuario.getFuncoes());
            repUsuario.save(usuarioAtual);
            cacheUsuario.limparCache();
            if (usuarioAtual.getFuncoes() != null) {
                usuarioAtual.getFuncoes().forEach(f -> f.setUsuarios(null));
            }
            return usuarioAtual;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/remover/{id_usuario}")
    public Usuario removerUsuario(@PathVariable Long id_usuario) {
        Optional<Usuario> op = cacheUsuario.findById(id_usuario);
        if (op.isPresent()) {
            Usuario usuario = op.get();
            repUsuario.delete(usuario);
            cacheUsuario.limparCache();
            if (usuario.getFuncoes() != null) {
                usuario.getFuncoes().forEach(f -> f.setUsuarios(null));
            }
            return usuario;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
