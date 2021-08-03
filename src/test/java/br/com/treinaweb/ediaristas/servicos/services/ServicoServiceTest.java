package br.com.treinaweb.ediaristas.servicos.services;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.treinaweb.ediaristas.servicos.builder.ServicoBuilder;
import br.com.treinaweb.ediaristas.servicos.exceptions.ServicoNaoEncontradoException;
import br.com.treinaweb.ediaristas.servicos.repositories.ServicoRepository;

@ExtendWith(MockitoExtension.class)
public class ServicoServiceTest {

    @Mock
    private ServicoRepository servicoRepository;

    @InjectMocks
    private ServicoService servicoService;

    @Test
    void quandoBuscarTodosForChamadoDeveRetornarTodosOsServicos() {
        var servicoEsperado = ServicoBuilder.builder().build().buildServico();
        var listaDeServicosEsperada = List.of(servicoEsperado);
        when(servicoRepository.findAll()).thenReturn(listaDeServicosEsperada);

        var listaDeServicosRetornada = servicoService.buscarTodos();

        assertThat(listaDeServicosRetornada, is(equalTo(listaDeServicosEsperada)));
    }

    @Test
    void quandoBuscarPorIdForChamadaComUmIdValidoDeveRetornarORespectivoServico() {
        var servicoEsperado = ServicoBuilder.builder().build().buildServico();
        var id = servicoEsperado.getId();
        when(servicoRepository.findById(id)).thenReturn(Optional.of(servicoEsperado));

        var servicoRetornado = servicoService.buscarPorId(id);

        assertThat(servicoRetornado, is(equalTo(servicoEsperado)));
    }

    @Test
    void quandoBuscarPorIdForChamadaComUmIdInvalidoDeveLancarAExcecaoServicoNaoEncontradoException() {
        var id = 1L;
        var mensagemEsperada = "Serviço com ID 1 não encontrado";
        when(servicoRepository.findById(id)).thenReturn(Optional.empty());

        try {
            servicoService.buscarPorId(id);
            fail("Deveria lançar uma exceção do tipo ServicoNaoEncontradoException com a mensagem " + mensagemEsperada);
        } catch (ServicoNaoEncontradoException e) {
            assertThat(e.getMessage(), is(equalTo(mensagemEsperada)));
        }
    }

    @Test
    void quandoCadastrarForChamadoComUmServicoValidoDeveRetornaroServicoCadastrado() {
        var servicoASerCadastrado = ServicoBuilder.builder().id(null).build().buildServico();
        var servicoEsperado = ServicoBuilder.builder().build().buildServico();
        when(servicoRepository.save(servicoASerCadastrado)).thenReturn(servicoEsperado);

        var servicoRetornado = servicoService.cadastrar(servicoASerCadastrado);

        assertThat(servicoRetornado, is(equalTo(servicoEsperado)));
    }

    @Test
    void quandoEditarForChamadoComUmServicoValidoDeveRetornaroServicoEditado() {
        var servicoASerEditado = ServicoBuilder.builder().build().buildServico();
        var id = servicoASerEditado.getId();
        var servicoEsperado = ServicoBuilder.builder().build().buildServico();
        when(servicoRepository.findById(id)).thenReturn(Optional.of(servicoEsperado));
        when(servicoRepository.save(servicoASerEditado)).thenReturn(servicoEsperado);

        var servicoRetornado = servicoService.editar(servicoASerEditado, id);

        assertThat(servicoRetornado, is(equalTo(servicoEsperado)));
    }

    @Test
    void quandoEditarForChamadoComUmIdInvalidoDeveLancarAExcecaoServicoNaoEncontradoException() {
        var servicoASerEditado = ServicoBuilder.builder().build().buildServico();
        var id = servicoASerEditado.getId();
        var mensagemEsperada = "Serviço com ID 1 não encontrado";
        when(servicoRepository.findById(id)).thenReturn(Optional.empty());

        try {
            servicoService.editar(servicoASerEditado, id);
            fail("Deveria lançar uma exceção do tipo ServicoNaoEncontradoException com a mensagem " + mensagemEsperada);
        } catch (ServicoNaoEncontradoException e) {
            assertThat(e.getMessage(), is(equalTo(mensagemEsperada)));
        }
    }

    @Test
    void quandoExcluirPorIdForChamadoComUmIdInvalidoDeveLancarAExcecaoServicoNaoEncontradoException() {
        var id = 1L;
        var mensagemEsperada = "Serviço com ID 1 não encontrado";
        when(servicoRepository.findById(id)).thenReturn(Optional.empty());

        try {
            servicoService.excluirPorId(id);
            fail("Deveria lançar uma exceção do tipo ServicoNaoEncontradoException com a mensagem " + mensagemEsperada);
        } catch (ServicoNaoEncontradoException e) {
            assertThat(e.getMessage(), is(equalTo(mensagemEsperada)));
        }
    }

    @Test
    void quandoExcluirPorIdForChamadoComUmIdValidoDeveChamarMetodoDeleteDoServicoRepository() {
        var servicoASerExcluido = ServicoBuilder.builder().build().buildServico();
        var id = servicoASerExcluido.getId();
        when(servicoRepository.findById(id)).thenReturn(Optional.of(servicoASerExcluido));

        servicoService.excluirPorId(id);

        verify(servicoRepository, times(1)).delete(servicoASerExcluido);
    }

}
