package br.com.treinaweb.ediaristas.servicos.converters;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.treinaweb.ediaristas.servicos.enums.Icone;

@Converter
public class IconeConverter implements AttributeConverter<Icone, String> {

    @Override
    public String convertToDatabaseColumn(Icone icone) {
        return icone.getNome();
    }

    @Override
    public Icone convertToEntityAttribute(String icone) {
        return Stream.of(Icone.values())
            .filter(i -> i.getNome().equals(icone))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

}
