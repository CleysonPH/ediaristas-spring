package br.com.treinaweb.ediaristas.usuarios.admin.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.treinaweb.ediaristas.auth.interfaces.UsuarioSenha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlterarSenhaFrom implements UsuarioSenha {

    @NotNull
    @NotEmpty
    private String senhaAntiga;

    @NotNull
    @NotEmpty
    private String senha;

    @NonNull
    @NotEmpty
    private String confirmacaoSenha;

}
