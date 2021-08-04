package br.com.treinaweb.ediaristas.auth.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.treinaweb.ediaristas.auth.interfaces.UsuarioSenha;

public class UsuarioSenhaValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UsuarioSenha.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var usuario = (UsuarioSenha) target;

        if (!usuario.getSenha().equals(usuario.getConfirmacaoSenha())) {
            errors.rejectValue("confirmacaoSenha", "br.com.treinaweb.ediaristas.auth.validator.senhasNaoConferem");
        }
    }

}
