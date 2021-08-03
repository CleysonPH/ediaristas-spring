package br.com.treinaweb.ediaristas.servicos.admin.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.servicos.admin.dtos.ServicoForm;
import br.com.treinaweb.ediaristas.servicos.admin.dtos.ServicoResumo;
import br.com.treinaweb.ediaristas.servicos.admin.mappers.AdminServicoMapper;
import br.com.treinaweb.ediaristas.servicos.services.ServicoService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServicoService {

    private ServicoService servicoService;

    private static final AdminServicoMapper ADMIN_SERVICO_MAPPER = AdminServicoMapper.INSTANCE;

    public List<ServicoResumo> buscarTodosResumidos() {
        return servicoService.buscarTodos()
            .stream()
            .map(ADMIN_SERVICO_MAPPER::modelParaResumo)
            .collect(Collectors.toList());
    }

    public ServicoForm buscarFormPorId(Long id) {
        var servico = servicoService.buscarPorId(id);

        return ADMIN_SERVICO_MAPPER.modelParaForm(servico);
    }

    public void cadastrar(ServicoForm form) {
        var servico = ADMIN_SERVICO_MAPPER.formParaModel(form);

        servicoService.cadastrar(servico);
    }

    public void editar(ServicoForm form, Long id) {
        var servico = ADMIN_SERVICO_MAPPER.formParaModel(form);

        servicoService.editar(servico, id);
    }

    public void excluirPorId(Long id) {
        servicoService.excluirPorId(id);
    }

}
