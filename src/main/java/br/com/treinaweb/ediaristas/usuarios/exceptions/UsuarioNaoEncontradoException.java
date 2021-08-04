package br.com.treinaweb.ediaristas.usuarios.exceptions;

import javax.persistence.EntityNotFoundException;

public class UsuarioNaoEncontradoException extends EntityNotFoundException {

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

}
