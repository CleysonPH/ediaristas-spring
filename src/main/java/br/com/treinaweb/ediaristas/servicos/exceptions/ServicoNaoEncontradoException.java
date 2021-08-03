package br.com.treinaweb.ediaristas.servicos.exceptions;

import javax.persistence.EntityNotFoundException;

public class ServicoNaoEncontradoException extends EntityNotFoundException {

    public ServicoNaoEncontradoException(Long id) {
        super(String.format("Serviço com ID %d não encontrado", id));
    }

}
