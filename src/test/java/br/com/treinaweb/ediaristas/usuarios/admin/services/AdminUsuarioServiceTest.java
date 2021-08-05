package br.com.treinaweb.ediaristas.usuarios.admin.services;

import static org.mockito.Mockito.*;

import java.security.Principal;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.treinaweb.ediaristas.usuarios.builder.AlterarSenhaFormBuilder;
import br.com.treinaweb.ediaristas.usuarios.builder.UsuarioBuilder;
import br.com.treinaweb.ediaristas.usuarios.builder.UsuarioCadastroFormBuilder;
import br.com.treinaweb.ediaristas.usuarios.builder.UsuarioEdicaoFormBuilder;
import br.com.treinaweb.ediaristas.usuarios.builder.UsuarioResumoBuilder;
import br.com.treinaweb.ediaristas.usuarios.services.UsuarioService;

@ExtendWith(MockitoExtension.class)
public class AdminUsuarioServiceTest {

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private Principal principal;

    @InjectMocks
    private AdminUsuarioService adminUsuarioService;

    @Test
    void quandoCadastrarForChamadoDeveChavarOCadastrarDeUsuarioService() {
        var form = UsuarioCadastroFormBuilder.builder().build().buildUsuarioCadastroForm();
        var usuario = UsuarioBuilder.builder().id(null).build().buildUsuario();

        adminUsuarioService.cadastrar(form);

        verify(usuarioService, times(1)).cadastrar(usuario);
    }

    @Test
    void quandoBuscarTodosForChamadoDeveRetornarTodosOsUsuariosConvertidosParaUsuarioResumo() {
        var usuario = UsuarioBuilder.builder().build().buildUsuario();
        var listaUsuarios = List.of(usuario);
        var usuarioResumo = UsuarioResumoBuilder.builder().build().buildUsuarioResumo();
        var listaUsuariosResumo = List.of(usuarioResumo);

        when(usuarioService.buscarTodos()).thenReturn(listaUsuarios);

        var retorno = adminUsuarioService.buscarTodos();

        assertThat(retorno, is(equalTo(listaUsuariosResumo)));
    }

    @Test
    void quandoBuscarFormPorIdForChamadoDeveRetornarOUsuarioConvertidoParaUsuarioEdicaoForm() {
        var usuario = UsuarioBuilder.builder().build().buildUsuario();
        var id = usuario.getId();
        var usuarioEdicaoForm = UsuarioEdicaoFormBuilder.builder().build().buildUsuarioEdicaoForm();

        when(usuarioService.buscarPorId(id)).thenReturn(usuario);

        var retorno = adminUsuarioService.buscarFormPorId(id);

        assertThat(retorno, is(equalTo(usuarioEdicaoForm)));
    }

    @Test
    void quandoEditarForChamadoDeveChamarOMetodoEditarDoUsuarioService() {
        var usuario = UsuarioBuilder.builder().build().buildUsuario();
        var id = usuario.getId();
        var usuarioEdicaoForm = UsuarioEdicaoFormBuilder.builder().build().buildUsuarioEdicaoForm();

        when(usuarioService.buscarPorId(id)).thenReturn(usuario);

        adminUsuarioService.editar(usuarioEdicaoForm, id);

        verify(usuarioService, times(1)).editar(usuario, id);
    }

    @Test
    void quandoExcluirPorIdForChamadoDeveChamarExcluirPorIdDoUsuarioService() {
        var id = 1L;

        adminUsuarioService.excluirPorId(id);

        verify(usuarioService, times(1)).excluirPorId(id);
    }

    @Test
    void quandoEditarSenhaForChamadoDeveChamarOEditarSenhaDeUsuarioService() {
        var alterarSenhaForm = AlterarSenhaFormBuilder.builder().build().buildAlterarSenhaForm();
        var senhaAtual = alterarSenhaForm.getSenhaAntiga();
        var senhaNova = alterarSenhaForm.getSenha();

        adminUsuarioService.editarSenha(alterarSenhaForm, principal);

        verify(usuarioService, times(1)).editarSenha(principal.getName(), senhaAtual, senhaNova);
    }

}
