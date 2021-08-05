package br.com.treinaweb.ediaristas.usuarios.builder;

import br.com.treinaweb.ediaristas.usuarios.admin.dtos.UsuarioCadastroForm;
import lombok.Builder;

@Builder
public class UsuarioCadastroFormBuilder {

    @Builder.Default
    private String nome = "Usuário Teste";

    @Builder.Default
    private String email = "usuario@teste.com";

    @Builder.Default
    private String senha = "senhaTeste";

    @Builder.Default
    private String confirmacaoSenha = "senhaTeste";

    public UsuarioCadastroForm buildUsuarioCadastroForm() {
        return new UsuarioCadastroForm(nome, email, senha, confirmacaoSenha);
    }

}
