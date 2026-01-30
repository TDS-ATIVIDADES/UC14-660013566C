package app.cinema.demo.controller.api;

import app.cinema.demo.dto.LoginRequest;
import app.cinema.demo.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação", description = "Endpoints para autenticação e geração de tokens JWT")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    @Operation(summary = "Realizar login e gerar token JWT",
               description = "Autentica o usuário e retorna um token JWT válido por um tempo especificado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login bem-sucedido, token retornado"),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Validação básica (substitua por lógica real, ex.: UserDetailsService)
        if ("user".equals(loginRequest.getUsername()) && "pass".equals(loginRequest.getPassword())) {
            long expirationMillis = loginRequest.getExpirationMinutes() * 60 * 1000; // Converter para milissegundos
            String token = jwtUtil.generateToken(loginRequest.getUsername(), expirationMillis);
            return ResponseEntity.ok().body("{\"token\":\"" + token + "\"}");
        } else {
            return ResponseEntity.status(401).body("{\"error\":\"Credenciais inválidas\"}");
        }
    }
}