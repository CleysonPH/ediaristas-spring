package br.com.treinaweb.ediaristas.usuarios.builder;

import br.com.treinaweb.ediaristas.usuarios.admin.dtos.UsuarioEdicaoForm;
import lombok.Builder;

@Builder
public class UsuarioEdicaoFormBuilder {

    @Builder.Default
    private String nome = "Usuário Teste";

    @Builder.Default
    private String email = "usuario@teste.com";

    public UsuarioEdicaoForm buildUsuarioEdicaoForm() {
        return new UsuarioEdicaoForm(nome, email);
    }

}
