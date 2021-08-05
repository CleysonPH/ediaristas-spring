package br.com.treinaweb.ediaristas.usuarios.builder;

import br.com.treinaweb.ediaristas.usuarios.admin.dtos.AlterarSenhaForm;
import lombok.Builder;

@Builder
public class AlterarSenhaFormBuilder {

    @Builder.Default
    private String senhaAntiga = "senhaAntinga";

    @Builder.Default
    private String senha = "senhaNova";

    @Builder.Default
    private String confirmacaoSenha = "senhaNova";

    public AlterarSenhaForm buildAlterarSenhaForm() {
        return new AlterarSenhaForm(senhaAntiga, senha, confirmacaoSenha);
    }

}
