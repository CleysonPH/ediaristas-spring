package br.com.treinaweb.ediaristas.servicos.admin.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import br.com.treinaweb.ediaristas.servicos.enums.Icone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoForm {

    @Size(min = 3, max = 50)
    @NotNull
    private String nome;

    @PositiveOrZero
    @DecimalMax("999.99")
    @NotNull
    @NumberFormat(style = Style.CURRENCY, pattern = "000,00")
    private BigDecimal valorMinimo;

    @PositiveOrZero
    @Max(24)
    @NotNull
    private Integer qtdHoras;

    @PositiveOrZero
    @Max(100)
    @NotNull
    private BigDecimal porcentagemComissao;

    @PositiveOrZero
    @Max(24)
    @NotNull
    private Integer horasQuarto;

    @PositiveOrZero
    @DecimalMax("999.99")
    @NotNull
    @NumberFormat(style = Style.CURRENCY, pattern = "000,00")
    private BigDecimal valorQuarto;

    @PositiveOrZero
    @Max(24)
    @NotNull
    private Integer horasSala;

    @PositiveOrZero
    @DecimalMax("999.99")
    @NotNull
    @NumberFormat(style = Style.CURRENCY, pattern = "000,00")
    private BigDecimal valorSala;

    @PositiveOrZero
    @Max(24)
    @NotNull
    private Integer horasBanheiro;

    @PositiveOrZero
    @DecimalMax("999.99")
    @NotNull
    @NumberFormat(style = Style.CURRENCY, pattern = "000,00")
    private BigDecimal valorBanheiro;

    @PositiveOrZero
    @Max(24)
    @NotNull
    private Integer horasCozinha;

    @PositiveOrZero
    @DecimalMax("999.99")
    @NotNull
    @NumberFormat(style = Style.CURRENCY, pattern = "000,00")
    private BigDecimal valorCozinha;

    @PositiveOrZero
    @Max(24)
    @NotNull
    private Integer horasQuintal;

    @PositiveOrZero
    @DecimalMax("999.99")
    @NotNull
    @NumberFormat(style = Style.CURRENCY, pattern = "000,00")
    private BigDecimal valorQuintal;

    @PositiveOrZero
    @Max(24)
    @NotNull
    private Integer horasOutros;

    @PositiveOrZero
    @DecimalMax("999.99")
    @NotNull
    @NumberFormat(style = Style.CURRENCY, pattern = "000,00")
    private BigDecimal valorOutros;

    @NotNull
    private Icone icone;

    @PositiveOrZero
    @NotNull
    private Integer posicao;

}
