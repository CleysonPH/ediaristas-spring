package br.com.treinaweb.ediaristas.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.auth.model.UsuarioAutenticado;
import br.com.treinaweb.ediaristas.usuarios.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var mensagemDeErro = String.format("Usuário com o email %s não encontrado", email);

        var usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(mensagemDeErro));

        return new UsuarioAutenticado(usuario);
    }

}
