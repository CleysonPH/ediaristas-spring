package br.com.treinaweb.ediaristas.usuarios.builder;

import br.com.treinaweb.ediaristas.usuarios.admin.dtos.UsuarioResumo;
import lombok.Builder;

@Builder
public class UsuarioResumoBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String nome = "Usuário Teste";

    @Builder.Default
    private String email = "usuario@teste.com";

    public UsuarioResumo buildUsuarioResumo() {
        return new UsuarioResumo(id, nome, email);
    }

}
