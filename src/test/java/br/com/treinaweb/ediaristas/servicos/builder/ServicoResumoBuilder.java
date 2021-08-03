package br.com.treinaweb.ediaristas.servicos.builder;

import java.math.BigDecimal;

import br.com.treinaweb.ediaristas.servicos.admin.dtos.ServicoResumo;
import lombok.Builder;

@Builder
public class ServicoResumoBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String nome = "Serviço de Teste";

    @Builder.Default
    private BigDecimal valorMinimo = new BigDecimal("10.00");

    public ServicoResumo buildServicoResumo() {
        return new ServicoResumo(id, nome, valorMinimo);
    }

}
