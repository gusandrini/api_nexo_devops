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
import br.com.nexo.repository.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repU;

    @Override
    public UserDetails loadUserByUsername(String nmEmail) throws UsernameNotFoundException {
        Usuario usuario = repU.findByNmEmail(nmEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new User(usuario.getNmEmail(), usuario.getNmSenha(),
                usuario.getFuncoes().stream()
                        .map(funcao -> new SimpleGrantedAuthority(funcao.getNmFuncao().toString()))
                        .collect(Collectors.toList()));
    }
}
