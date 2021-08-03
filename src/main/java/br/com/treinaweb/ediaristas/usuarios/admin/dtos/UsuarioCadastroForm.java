package br.com.treinaweb.ediaristas.usuarios.admin.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.treinaweb.ediaristas.auth.interfaces.UsuarioSenha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCadastroForm implements UsuarioSenha {

    @NotNull
    @Size(min = 3, max = 255)
    private String nome;

    @NotNull
    @Size(min = 10, max = 255)
    @Email
    private String email;

    @NotNull
    private String senha;

    @NotNull
    private String confirmacaoSenha;

}
