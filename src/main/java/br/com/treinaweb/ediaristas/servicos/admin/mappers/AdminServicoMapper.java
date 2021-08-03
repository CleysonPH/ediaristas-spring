package br.com.treinaweb.ediaristas.servicos.admin.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.treinaweb.ediaristas.servicos.admin.dtos.ServicoForm;
import br.com.treinaweb.ediaristas.servicos.admin.dtos.ServicoResumo;
import br.com.treinaweb.ediaristas.servicos.models.Servico;

@Mapper(componentModel = "spring")
public abstract class AdminServicoMapper {

    public static final AdminServicoMapper INSTANCE = Mappers.getMapper(AdminServicoMapper.class);

    public abstract Servico formParaModel(ServicoForm form);

    public abstract ServicoForm modelParaForm(Servico servico);

    public abstract ServicoResumo modelParaResumo(Servico servico);

}
