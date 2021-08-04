package br.com.treinaweb.ediaristas.usuarios.builder;

import br.com.treinaweb.ediaristas.usuarios.enums.Tipo;
import br.com.treinaweb.ediaristas.usuarios.models.Usuario;
import lombok.Builder;

@Builder
public class UsuarioBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String nome = "Usuário Teste";

    @Builder.Default
    private String email = "usuario@teste.com";

    @Builder.Default
    private String senha = "senhaTeste";

    @Builder.Default
    private Tipo tipo = Tipo.ADMIN;

    public Usuario buildUsuario() {
        return new Usuario(id, nome, email, senha, tipo);
    }

}
