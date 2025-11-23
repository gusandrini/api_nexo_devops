package br.com.nexo.control;

import br.com.nexo.config.JwtUtil;
import br.com.nexo.dto.LoginRequest;
import br.com.nexo.model.Usuario;          // ✅ ajustado: model.Usuario
import br.com.nexo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioService usuarioService;  // usado para trazer o usuário completo

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) {
        try {
            // Autentica usuário (usa nmEmail e nmSenha do LoginRequest)
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getNmEmail(),
                            loginRequest.getNmSenha()
                    )
            );

            // Usuário autenticado
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Gera o token JWT
            String jwt = jwtUtil.generateToken(userDetails);

            // Busca o usuário completo no banco para devolver ao mobile
            Usuario usuario = usuarioService.buscarPorEmail(loginRequest.getNmEmail());

            // Monta resposta exatamente no formato que o mobile espera
            Map<String, Object> response = new HashMap<>();
            response.put("token", jwt);
            response.put("usuario", usuario);

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            // 401 se email/senha estiverem errados
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
