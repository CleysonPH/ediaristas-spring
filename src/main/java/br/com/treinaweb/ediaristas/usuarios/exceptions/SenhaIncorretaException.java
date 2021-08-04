package br.com.treinaweb.ediaristas.usuarios.exceptions;

public class SenhaIncorretaException extends RuntimeException {

    public SenhaIncorretaException(String mensagem) {
        super(mensagem);
    }

}
