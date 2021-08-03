package br.com.treinaweb.ediaristas.servicos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.core.service.IService;
import br.com.treinaweb.ediaristas.servicos.exceptions.ServicoNaoEncontradoException;
import br.com.treinaweb.ediaristas.servicos.models.Servico;
import br.com.treinaweb.ediaristas.servicos.repositories.ServicoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServicoService implements IService<Servico, Long> {

    private ServicoRepository servicoRepository;

    @Override
    public List<Servico> buscarTodos() {
        return servicoRepository.findAll();
    }

    @Override
    public Servico buscarPorId(Long id) {
        return verificaSeExisteERetorna(id);
    }

    @Override
    public Servico cadastrar(Servico servico) {
        return servicoRepository.save(servico);
    }

    @Override
    public Servico editar(Servico servico, Long id) {
        verificaSeExisteERetorna(id);

        servico.setId(id);
        return servicoRepository.save(servico);
    }

    @Override
    public void excluirPorId(Long id) {
        var servico = verificaSeExisteERetorna(id);

        servicoRepository.delete(servico);
    }

    private Servico verificaSeExisteERetorna(Long id) {
        return servicoRepository.findById(id)
            .orElseThrow(() -> new ServicoNaoEncontradoException(id));
    }

}
