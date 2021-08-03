package br.com.treinaweb.ediaristas.usuarios.exceptions;

import javax.persistence.EntityNotFoundException;

public class UsuarioNaoEncontradoException extends EntityNotFoundException {

    public UsuarioNaoEncontradoException(Long id) {
        super(String.format("Usuário com ID %d não encontrado", id));
    }

}
