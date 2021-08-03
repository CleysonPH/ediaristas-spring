package br.com.treinaweb.ediaristas.usuarios.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.core.service.IService;
import br.com.treinaweb.ediaristas.usuarios.exceptions.UsuarioNaoEncontradoException;
import br.com.treinaweb.ediaristas.usuarios.models.Usuario;
import br.com.treinaweb.ediaristas.usuarios.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService implements IService<Usuario, Long> {

    private UsuarioRepository usuarioRepository;

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
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario editar(Usuario usuario, Long id) {
        verificaSeExisteERetorna(id);

        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    @Override
    public void excluirPorId(Long id) {
        var usuario = verificaSeExisteERetorna(id);

        usuarioRepository.delete(usuario);

    }

    private Usuario verificaSeExisteERetorna(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

}
