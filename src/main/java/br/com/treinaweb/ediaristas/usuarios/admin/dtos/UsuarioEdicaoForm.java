package br.com.treinaweb.ediaristas.usuarios.admin.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEdicaoForm {

    @NotNull
    @Size(min = 3, max = 255)
    private String nome;

    @NotNull
    @Size(min = 10, max = 255)
    @Email
    private String email;

}
