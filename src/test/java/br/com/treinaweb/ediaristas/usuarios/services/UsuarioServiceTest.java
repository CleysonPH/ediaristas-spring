package br.com.treinaweb.ediaristas.usuarios.services;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.treinaweb.ediaristas.usuarios.builder.UsuarioBuilder;
import br.com.treinaweb.ediaristas.usuarios.exceptions.SenhaIncorretaException;
import br.com.treinaweb.ediaristas.usuarios.exceptions.UsuarioNaoEncontradoException;
import br.com.treinaweb.ediaristas.usuarios.repositories.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void quandoBuscarTodosForChamadoDeveRetornarTodosOsUsuarios() {
        var usuarioEsperado = UsuarioBuilder.builder().build().buildUsuario();
        var listaDeUsuariosEsperada = List.of(usuarioEsperado);

        when(usuarioRepository.findAll()).thenReturn(listaDeUsuariosEsperada);

        var listaDeUsuariosRetornada = usuarioService.buscarTodos();

        assertThat(listaDeUsuariosRetornada, is(equalTo(listaDeUsuariosEsperada)));
    }

    @Test
    void quandoBuscarPorIdComIdValidoForChamadoDeveRetornarORespectivoUsuario() {
        var usuarioEsperado = UsuarioBuilder.builder().build().buildUsuario();
        var id = usuarioEsperado.getId();

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioEsperado));

        var usuarioRetornado = usuarioService.buscarPorId(id);

        assertThat(usuarioRetornado, is(equalTo(usuarioEsperado)));
    }

    @Test
    void quandoBuscarPorIdComIdInvalidoForChamadoDeveLancarAExcecaoUsuarioNaoEncontradoException() {
        var id = 1L;
        var mensagemEsperada = String.format("Usuário com ID %d não encontrado", id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        var exception = assertThrows(UsuarioNaoEncontradoException.class,
            () -> usuarioService.buscarPorId(id)
        );
        assertThat(exception.getMessage(), is(equalTo(mensagemEsperada)));
    }

    @Test
    void quandoCadastrarForChamadoComUmUsuarioValidoDeveRetornarOUsuarioCadastrado() {
        var usuarioASerCadastrado = UsuarioBuilder.builder().id(null).build().buildUsuario();
        var usuarioEsperado = UsuarioBuilder.builder().build().buildUsuario();

        when(usuarioRepository.save(usuarioASerCadastrado)).thenReturn(usuarioEsperado);

        var usuarioRetornado = usuarioService.cadastrar(usuarioASerCadastrado);

        assertThat(usuarioRetornado, is(equalTo(usuarioEsperado)));
    }

    @Test
    void quandoCadastrarForChamadoComUmUsuarioValidoDeveSetarASenhaComOHashDaSenha() {
        var usuarioASerCadastrado = UsuarioBuilder.builder().build().buildUsuario();
        var senha = usuarioASerCadastrado.getSenha();
        var senhaHash = "senhaHash";

        when(passwordEncoder.encode(senha)).thenReturn(senhaHash);

        usuarioService.cadastrar(usuarioASerCadastrado);

        assertThat(usuarioASerCadastrado.getSenha(), is(equalTo(senhaHash)));
    }

    @Test
    void quandoEditarForChamadoComUmUsuarioValidoDeveRetornaroOUsuarioEditado() {
        var usuarioEsperado = UsuarioBuilder.builder().build().buildUsuario();
        var id = usuarioEsperado.getId();

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioEsperado));
        when(usuarioRepository.save(usuarioEsperado)).thenReturn(usuarioEsperado);

        var usuarioRetornado = usuarioService.editar(usuarioEsperado, id);

        assertThat(usuarioRetornado, is(equalTo(usuarioEsperado)));
    }

    @Test
    void quandoEditarForChamadoComUmUsuarioValidoDeveSetarASenhaComOHashDaSenha() {
        var usuarioASerEditado = UsuarioBuilder.builder().build().buildUsuario();
        var id = usuarioASerEditado.getId();
        var senha = usuarioASerEditado.getSenha();
        var senhaHash = "senhaHash";

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioASerEditado));
        when(passwordEncoder.encode(senha)).thenReturn(senhaHash);

        usuarioService.editar(usuarioASerEditado, id);

        assertThat(usuarioASerEditado.getSenha(), is(equalTo(senhaHash)));
    }

    @Test
    void quandoEditarForChamadoComUmIdInvalidoDeveLancarAExcecaoServicoNaoEncontradoException() {
        var id = 1L;
        var usuarioASerEditado = UsuarioBuilder.builder().build().buildUsuario();
        var mensagemEsperada = String.format("Usuário com ID %d não encontrado", id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        var exception = assertThrows(UsuarioNaoEncontradoException.class,
            () -> usuarioService.editar(usuarioASerEditado, id)
        );
        assertThat(exception.getMessage(), is(equalTo(mensagemEsperada)));
    }

    @Test
    void quandoExcluirPorIdForChamadoComUmIdInvalidoDeveLancarAExcecaoUsuarioNaoEncontradoException() {
        var id = 1L;
        var mensagemEsperada = String.format("Usuário com ID %d não encontrado", id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        var exception = assertThrows(UsuarioNaoEncontradoException.class,
            () -> usuarioService.excluirPorId(id)
        );
        assertThat(exception.getMessage(), is(equalTo(mensagemEsperada)));
    }

    @Test
    void quandoExcluirPorIdForChamadoComUmIdValidoDeveChamarMetodoDeleteDoUsuarioRepository() {
        var usuarioASerExcluido = UsuarioBuilder.builder().build().buildUsuario();
        var id = usuarioASerExcluido.getId();

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioASerExcluido));

        usuarioService.excluirPorId(id);

        verify(usuarioRepository, times(1)).delete(usuarioASerExcluido);
    }

    @Test
    void quandoBuscarPorEmailForChamadoComUmEmailValidoDeveRetornarORespectivoUsuario() {
        var usuarioEsperado = UsuarioBuilder.builder().build().buildUsuario();
        var email = usuarioEsperado.getEmail();

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuarioEsperado));

        var usuarioRetornado = usuarioService.buscarPorEmail(email);

        assertThat(usuarioRetornado, is(equalTo(usuarioEsperado)));
    }

    @Test
    void quandoBuscarPorEmailForChamadoComUmEmailInvalidoDeveLancarAExcecaoUsuarioNaoEncontradoException() {
        var email = "usuario@teste.com";
        var mensagemEsperada = String.format("Usuário com e-mail \"%s\" não encontrado", email);

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        var exception = assertThrows(UsuarioNaoEncontradoException.class,
            () -> usuarioService.buscarPorEmail(email)
        );
        assertThat(exception.getMessage(), is(equalTo(mensagemEsperada)));
    }

    @Test
    void quandoEditarSenhaForChamadoComSenhaAnteriorCorretaDeveRealizarAAltereaçãoDeSenha() {
        var usuarioASerEditado = UsuarioBuilder.builder().build().buildUsuario();
        var email = usuarioASerEditado.getEmail();
        var senhaAtual = usuarioASerEditado.getSenha();
        var senhaNova = "senhaNova";

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuarioASerEditado));
        when(passwordEncoder.matches(senhaAtual, usuarioASerEditado.getSenha())).thenReturn(true);
        when(passwordEncoder.encode(senhaNova)).thenReturn(senhaNova);

        usuarioService.editarSenha(email, senhaAtual, senhaNova);

        assertThat(usuarioASerEditado.getSenha(), is(equalTo(senhaNova)));
    }

    @Test
    void quandoEditarSenhaForChamadoComSenhaAnteriorIncorretaDeveLancarAExcecaoSenhaIncorretaException() {
        var usuarioASerEditado = UsuarioBuilder.builder().build().buildUsuario();
        var mensagemEsperada = "A senha antiga está incorreta";
        var email = usuarioASerEditado.getEmail();
        var senhaAtual = "senhaIncorreta";
        var senhaNova = "senhaNova";

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuarioASerEditado));
        when(passwordEncoder.matches(senhaAtual, usuarioASerEditado.getSenha())).thenReturn(false);

        var exception = assertThrows(SenhaIncorretaException.class,
            () -> usuarioService.editarSenha(email, senhaAtual, senhaNova)
        );
        assertThat(exception.getMessage(), is(equalTo(mensagemEsperada)));
    }

}
