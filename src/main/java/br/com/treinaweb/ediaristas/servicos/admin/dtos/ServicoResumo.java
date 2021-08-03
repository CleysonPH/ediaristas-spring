package br.com.treinaweb.ediaristas.servicos.admin.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoResumo {

    private Long id;

    private String nome;

    private BigDecimal valorMinimo;

}
