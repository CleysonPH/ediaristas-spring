package br.com.treinaweb.ediaristas.servicos.builder;

import java.math.BigDecimal;

import br.com.treinaweb.ediaristas.servicos.enums.Icone;
import br.com.treinaweb.ediaristas.servicos.models.Servico;
import lombok.Builder;

@Builder
public class ServicoBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String nome = "Serviço de Teste";

    @Builder.Default
    private BigDecimal valorMinimo = new BigDecimal("10.00");

    @Builder.Default
    private Integer qtdHoras = 1;

    @Builder.Default
    private BigDecimal porcentagemComissao = new BigDecimal("10.00");

    @Builder.Default
    private Integer horasQuarto = 1;

    @Builder.Default
    private BigDecimal valorQuarto = new BigDecimal("10.00");

    @Builder.Default
    private Integer horasSala = 1;

    @Builder.Default
    private BigDecimal valorSala = new BigDecimal("10.00");

    @Builder.Default
    private Integer horasBanheiro = 1;

    @Builder.Default
    private BigDecimal valorBanheiro = new BigDecimal("10.00");

    @Builder.Default
    private Integer horasCozinha = 1;

    @Builder.Default
    private BigDecimal valorCozinha = new BigDecimal("10.00");

    @Builder.Default
    private Integer horasQuintal = 1;

    @Builder.Default
    private BigDecimal valorQuintal = new BigDecimal("10.00");

    @Builder.Default
    private Integer horasOutros = 1;

    @Builder.Default
    private BigDecimal valorOutros = new BigDecimal("10.00");

    @Builder.Default
    private Icone icone = Icone.TWF_CLEANING_3;

    @Builder.Default
    private Integer posicao = 1;

    public Servico buildServico() {
        return new Servico(id, nome, valorMinimo, qtdHoras, porcentagemComissao, horasQuarto, valorQuarto, horasSala,
                valorSala, horasBanheiro, valorBanheiro, horasCozinha, valorCozinha, horasQuintal, valorQuintal,
                horasOutros, valorOutros, icone, posicao);
    }

}
