package br.com.treinaweb.ediaristas.servicos.converters;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.treinaweb.ediaristas.servicos.enums.Icone;

public class IconeConverterTest {

    private IconeConverter iconeConverter;

    @BeforeEach
    public void setUp() {
        this.iconeConverter = new IconeConverter();
    }

    @Test
    void quandoPassadoUmEnumIconeDeveRetornarOAtributoNomeDesseEnum() {
        var icone = Icone.TWF_CLEANING_1;

        var iconeNome = iconeConverter.convertToDatabaseColumn(icone);

        assertThat(iconeNome, is(equalTo("twf-cleaning-1")));
    }

    @Test
    void quandoPassadoUmaStringValidaDeveRetornarOEnumEquivalente() {
        var iconeNome = "twf-cleaning-1";

        var icone = iconeConverter.convertToEntityAttribute(iconeNome);

        assertThat(icone, is(equalTo(Icone.TWF_CLEANING_1)));
    }

    @Test
    void quandoPassadoUmaStringInvalidaDeveLancarUmaExcecaoDoTipoIllegalArgumentException() {
        var iconeNome = "String Inexistente";

        Assertions.assertThrows(IllegalArgumentException.class,
            () -> iconeConverter.convertToEntityAttribute(iconeNome));
    }

}
