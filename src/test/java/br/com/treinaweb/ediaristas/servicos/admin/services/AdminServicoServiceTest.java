package br.com.treinaweb.ediaristas.servicos.admin.services;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.treinaweb.ediaristas.servicos.builder.ServicoBuilder;
import br.com.treinaweb.ediaristas.servicos.builder.ServicoFormBuilder;
import br.com.treinaweb.ediaristas.servicos.builder.ServicoResumoBuilder;
import br.com.treinaweb.ediaristas.servicos.services.ServicoService;

@ExtendWith(MockitoExtension.class)
public class AdminServicoServiceTest {

    @Mock
    private ServicoService servicoService;

    @InjectMocks
    private AdminServicoService adminServicoService;

    @Test
    void quandoBuscarTodosResumidosForChamadoDeveRetornaAListaDeServicosConvertidoParaServicoResumo() {
        var servicoEsperado = ServicoBuilder.builder().build().buildServico();
        var listaDeServicosEsperado = List.of(servicoEsperado);
        var servicoResumoEsperado = ServicoResumoBuilder.builder().build().buildServicoResumo();
        var listaDeServicoResumoEsperado = List.of(servicoResumoEsperado);

        when(servicoService.buscarTodos()).thenReturn(listaDeServicosEsperado);

        var listaDeServicosResumoRetornada = adminServicoService.buscarTodosResumidos();

        assertThat(listaDeServicosResumoRetornada, is(equalTo(listaDeServicoResumoEsperado)));
    }

    @Test
    void quandoBuscarFormPorIdForChamadoComUmIdValidoDeveRetornaOServicoConvertidoParaServicoForm() {
        var servicoEsperado = ServicoBuilder.builder().build().buildServico();
        var id = servicoEsperado.getId();
        var servicoFormEsperado = ServicoFormBuilder.builder().build().buildServicoForm();

        when(servicoService.buscarPorId(id)).thenReturn(servicoEsperado);

        var servicoFormRetornado = adminServicoService.buscarFormPorId(id);

        assertThat(servicoFormRetornado, is(equalTo(servicoFormEsperado)));
    }

    @Test
    void quandoCadastrarForChamadoDeveExecutarOMetodoCadastrarDeServicoServiceComODevidoServicoConvertido() {
        var servicoEsperado = ServicoBuilder.builder().id(null).build().buildServico();
        var servicoFormEsperado = ServicoFormBuilder.builder().build().buildServicoForm();

        adminServicoService.cadastrar(servicoFormEsperado);

        verify(servicoService, times(1)).cadastrar(servicoEsperado);
    }

    @Test
    void quandoEditarForChamadoDeveExecutarOMetodoEditarDeServicoServiceComODevidoServicoConvertido() {
        var id = 1L;
        var servicoEsperado = ServicoBuilder.builder().id(null).build().buildServico();
        var servicoFormEsperado = ServicoFormBuilder.builder().build().buildServicoForm();

        adminServicoService.editar(servicoFormEsperado, id);

        verify(servicoService, times(1)).editar(servicoEsperado, id);
    }

    @Test
    void quandoExcluirPorIdForChamadoDeveExecutarOMetodoExcluirPorIdDeServicoService() {
        var id = 1L;

        adminServicoService.excluirPorId(id);

        verify(servicoService, times(1)).excluirPorId(id);
    }

}
