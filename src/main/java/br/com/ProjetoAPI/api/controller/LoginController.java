package br.com.ProjetoAPI.api.controller;

import br.com.ProjetoAPI.api.model.input.UsuarioInputDTO;
import br.com.ProjetoAPI.domain.model.AuthenticationResponse;
import br.com.ProjetoAPI.domain.model.Pessoa;
import br.com.ProjetoAPI.security.ImplementsUserDetailsService;
import br.com.ProjetoAPI.security.JWTUtil;
import br.com.senai.api.assembler.UsuarioAssembler;
import br.com.senai.api.model.input.UsuarioInputDTO;
import br.com.senai.domain.model.AuthenticationResponse;
import br.com.senai.domain.model.Usuario;
import br.com.senai.security.ImplementsUserDetailsService;
import br.com.senai.security.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class LoginController {

    private AuthenticationManager authenticationManager;
    private ImplementsUserDetailsService implementsUserDetailsService;
    private JWTUtil jwtUtil;
    private PessoaController pessoaAssembler;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UsuarioInputDTO usuarioInputDTO) throws Exception{
        Pessoa pessoa = pessoaAssembler.toEntity(usuarioInputDTO);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            pessoa.getUsername(), pessoa.getPassword())
            );
        } catch (BadCredentialsException ex){
            throw new Exception("Usuário ou senha inválidos.", ex);
        }

        final UserDetails userDetails = implementsUserDetailsService
                .loadUserByUsername(pessoa.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt, pessoaAssembler.toModel(pessoa)));
    }

}
