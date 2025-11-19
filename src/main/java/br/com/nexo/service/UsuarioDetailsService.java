package br.com.nexo.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.nexo.model.Usuario;

@Service
public class UsuarioDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioCachingService cache;


    @Override
        public UserDetails loadUserByUsername(String nmEmail) throws UsernameNotFoundException {
                String emailNormalizado = nmEmail == null ? "" : nmEmail.trim();
                Usuario usuario = cache.findByNmEmail(emailNormalizado)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

                String senhaSemEspacos = usuario.getNmSenha() == null ? "" : usuario.getNmSenha().trim();
                return new User(usuario.getNmEmail(), senhaSemEspacos,
                usuario.getFuncoes().stream()
                    .map(funcao -> new SimpleGrantedAuthority("ROLE_" + funcao.getNmFuncao().toString()))
                    .collect(Collectors.toList()));
    }
}
