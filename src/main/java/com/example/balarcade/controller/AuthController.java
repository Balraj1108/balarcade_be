package com.example.balarcade.controller;

import com.example.balarcade.dto.UtenteAuthDTO;
import com.example.balarcade.dto.UtenteDTO;
import com.example.balarcade.exception.BalarcadeException;
import com.example.balarcade.model.Utente;
import com.example.balarcade.security.JWTUtil;
import com.example.balarcade.service.provider.ServiceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JWTUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final ServiceProvider sp;

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody UtenteAuthDTO body) {
        System.out.println("loginHandler");
        try {
            // Login e generazione token
            UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
                    body.getUsername(), body.getPassword());

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(body.getUsername());

            Utente utente = sp.utenteService.cercaPerEmail(body.getUsername());
            UtenteDTO utenteDTO = UtenteDTO.fromModel(utente);

            Map<String, Object> response = new HashMap<>();
            response.put("jwt-token", token);
            response.put("utente", utenteDTO);

            return response;
        } catch (AuthenticationException authExc) {
            throw new BalarcadeException("Invalid Login Credentials", HttpStatus.UNAUTHORIZED);
        }
    }

}






