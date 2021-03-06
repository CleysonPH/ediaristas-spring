package br.com.treinaweb.ediaristas.servicos.admin.controllers;

import static org.mockito.Mockito.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.treinaweb.ediaristas.servicos.admin.dtos.ServicoForm;
import br.com.treinaweb.ediaristas.servicos.admin.services.AdminServicoService;
import br.com.treinaweb.ediaristas.servicos.builder.ServicoFormBuilder;
import br.com.treinaweb.ediaristas.servicos.builder.ServicoResumoBuilder;
import br.com.treinaweb.ediaristas.servicos.enums.Icone;

@ExtendWith(MockitoExtension.class)
public class ServicoControllerTest {

    @Mock
    private AdminServicoService adminServicoService;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private ServicoController servicoController;

    @Test
    void quandoActionGETCadastrarForChamadaDeveUtilizarAViewCorreta() {
        var modelAndViewRetornada = servicoController.cadastrar();

        assertThat(modelAndViewRetornada.getViewName(), is(equalTo("admin/servicos/form")));
    }

    @Test
    void quandoActionGETCadastrarForChamadaDeveEnviarServicoFormVazioParaAView() {
        var modelAndViewRetornada = servicoController.cadastrar();

        assertThat(modelAndViewRetornada.getModel().size(), is(equalTo(1)));
        assertTrue(modelAndViewRetornada.getModel().get("form") != null);
        assertThat(modelAndViewRetornada.getModel().get("form"), is(equalTo(new ServicoForm())));
    }

    @Test
    void quandoActionPOSTCadastrarForChamadaComServicoFormValidoDeveRedirecionarParaListaDeServicos() {
        var servicoFormEsperado = ServicoFormBuilder.builder().build().buildServicoForm();

        var retorno = servicoController.cadastrar(servicoFormEsperado, bindingResult, redirectAttributes);

        assertThat(retorno, is(equalTo("redirect:/admin/servicos")));
    }

    @Test
    void quandoActionPOSTCadastrarForChamadaComServicoFormValidoDeveExecutarMetodoCadastrarDeAdminServicoService() {
        var servicoFormEsperado = ServicoFormBuilder.builder().build().buildServicoForm();

        servicoController.cadastrar(servicoFormEsperado, bindingResult, redirectAttributes);

        verify(adminServicoService, times(1)).cadastrar(servicoFormEsperado);
    }

    @Test
    void quandoActionPOSTCadastrarForChamadaComServicoFormInvalidoDeveChamarAViewForm() {
        var servicoFormEsperado = ServicoFormBuilder.builder().build().buildServicoForm();
        when(bindingResult.hasErrors()).thenReturn(true);

        var retorno = servicoController.cadastrar(servicoFormEsperado, bindingResult, redirectAttributes);

        assertThat(retorno, is(equalTo("admin/servicos/form")));
    }

    @Test
    void quandoActionGETListarForChamadaDeveUtilizarAViewCorreta() {
        var modelAndViewRetornada = servicoController.listar();

        assertThat(modelAndViewRetornada.getViewName(), is(equalTo("admin/servicos/lista")));
    }

    @Test
    void quandoActionGETListarForChamadaDeveEnviarAListaDeServicoResumoParaAView() {
        var servicoResumoEsperado = ServicoResumoBuilder.builder().build().buildServicoResumo();
        var listaServicoResumoEsperado = List.of(servicoResumoEsperado);
        when(adminServicoService.buscarTodosResumidos()).thenReturn(listaServicoResumoEsperado);

        var modelAndViewRetornada = servicoController.listar();

        assertThat(modelAndViewRetornada.getModel().size(), is(equalTo(1)));
        assertTrue(modelAndViewRetornada.getModel().get("servicos") != null);
        assertThat(modelAndViewRetornada.getModel().get("servicos"), is(equalTo(listaServicoResumoEsperado)));
    }

    @Test
    void quandoActionGETEditarForChamadaDeveUtilizarAViewCorreta() {
        var modelAndViewRetornada = servicoController.editar(1L);

        assertThat(modelAndViewRetornada.getViewName(), is(equalTo("admin/servicos/form")));
    }

    @Test
    void quandoActionGETEditarForChamadaDeveEnviarOServicoFormRespectivo() {
        var id = 1L;
        var servicoFormEsperado = ServicoFormBuilder.builder().build().buildServicoForm();
        when(adminServicoService.buscarFormPorId(id)).thenReturn(servicoFormEsperado);

        var modelAndViewRetornada = servicoController.editar(id);

        assertThat(modelAndViewRetornada.getModel().size(), is(equalTo(1)));
        assertTrue(modelAndViewRetornada.getModel().get("form") != null);
        assertThat(modelAndViewRetornada.getModel().get("form"), is(equalTo(servicoFormEsperado)));
    }

    @Test
    void quandoActionPOSTEditarForChamadaComServicoFormValidoDeveRedirecionarParaListaDeServicos() {
        var id = 1L;
        var servicoFormEsperado = ServicoFormBuilder.builder().build().buildServicoForm();

        var retorno = servicoController.editar(id, servicoFormEsperado, bindingResult, redirectAttributes);

        assertThat(retorno, is(equalTo("redirect:/admin/servicos")));
    }

    @Test
    void quandoActionPOSTEditarForChamadaComServicoFormValidoDeveExecutarMetodoEditarDeAdminServicoService() {
        var id = 1L;
        var servicoFormEsperado = ServicoFormBuilder.builder().build().buildServicoForm();

        servicoController.editar(id, servicoFormEsperado, bindingResult, redirectAttributes);

        verify(adminServicoService, times(1)).editar(servicoFormEsperado, id);
    }

    @Test
    void quandoActionPOSTEditarForChamadaComServicoFormInvalidoDeveChamarAViewForm() {
        var id = 1L;
        var servicoFormEsperado = ServicoFormBuilder.builder().build().buildServicoForm();
        when(bindingResult.hasErrors()).thenReturn(true);

        var retorno = servicoController.editar(id, servicoFormEsperado, bindingResult, redirectAttributes);

        assertThat(retorno, is(equalTo("admin/servicos/form")));
    }

    @Test
    void quandoActionGETExcluirForChamadaDeveChamarMetodoExcluirPorIdDeAdminServicoService() {
        servicoController.excluir(1L, redirectAttributes);

        verify(adminServicoService, times(1)).excluirPorId(1L);
    }

    @Test
    void quandoGetIconsForChamadoDeveRetornarListaDeIcones() {
        var icones = servicoController.getIcones();

        assertThat(icones, is(equalTo(Icone.values())));
    }

}
