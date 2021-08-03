package br.com.treinaweb.ediaristas.usuarios.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResumo {

    private Long id;

    private String nome;

    private String email;

}
