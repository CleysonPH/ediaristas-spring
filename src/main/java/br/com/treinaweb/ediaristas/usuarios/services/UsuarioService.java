package br.com.treinaweb.ediaristas.usuarios.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.core.service.IService;
import br.com.treinaweb.ediaristas.usuarios.exceptions.SenhaIncorretaException;
import br.com.treinaweb.ediaristas.usuarios.exceptions.UsuarioNaoEncontradoException;
import br.com.treinaweb.ediaristas.usuarios.models.Usuario;
import br.com.treinaweb.ediaristas.usuarios.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService implements IService<Usuario, Long> {

    private UsuarioRepository usuarioRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return verificaSeExisteERetorna(id);
    }

    @Override
    public Usuario cadastrar(Usuario usuario) {
        var senhaHash = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaHash);

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario editar(Usuario usuario, Long id) {
        verificaSeExisteERetorna(id);

        var senhaHash = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaHash);

        usuario.setId(id);

        return usuarioRepository.save(usuario);
    }

    @Override
    public void excluirPorId(Long id) {
        var usuario = verificaSeExisteERetorna(id);

        usuarioRepository.delete(usuario);

    }

    public Usuario buscarPorEmail(String email) {
        var mensagemDeErro = String.format("Usuário com e-mail \"%s\" não encontrado", email);

        return usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(mensagemDeErro));
    }

    public void editarSenha(String email, String senhaAtual, String senhaNova) {
        var usuario = buscarPorEmail(email);

        if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
            throw new SenhaIncorretaException("A senha antiga está incorreta");
        }

        usuario.setSenha(passwordEncoder.encode(senhaNova));

        usuarioRepository.save(usuario);
    }

    private Usuario verificaSeExisteERetorna(Long id) {
        var mensagemDeErro = String.format("Usuário com ID %d não encontrado", id);

        return usuarioRepository.findById(id)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(mensagemDeErro));
    }

}
