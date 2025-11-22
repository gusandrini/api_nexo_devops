package br.com.nexo.control;

import br.com.nexo.config.JwtUtil;
import br.com.nexo.dto.LoginRequest;
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
@RequestMapping("/api/auth")  // ✅ mantém /api/auth/login para o mobile
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) {
        try {
            // Autentica usuário
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getNmEmail(),
                            loginRequest.getNmSenha()
                    )
            );

            // Pega o UserDetails autenticado
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Gera o token JWT
            String jwt = jwtUtil.generateToken(userDetails);

            // Monta resposta
            Map<String, Object> response = new HashMap<>();
            response.put("token", jwt);
            response.put("username", userDetails.getUsername());

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            // 401 se email/senha estiverem errados
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
