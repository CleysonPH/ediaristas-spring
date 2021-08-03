package br.com.treinaweb.ediaristas.usuarios.admin.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.treinaweb.ediaristas.usuarios.admin.dtos.UsuarioCadastroForm;
import br.com.treinaweb.ediaristas.usuarios.admin.dtos.UsuarioEdicaoForm;
import br.com.treinaweb.ediaristas.usuarios.admin.dtos.UsuarioResumo;
import br.com.treinaweb.ediaristas.usuarios.models.Usuario;

@Mapper(componentModel = "spring")
public abstract class AdminUsuarioMapper {

    public static final AdminUsuarioMapper INSTANCE = Mappers.getMapper(AdminUsuarioMapper.class);

    public abstract Usuario cadastroFormParaModel(UsuarioCadastroForm form);

    public abstract UsuarioEdicaoForm modelParaEdicaoForm(Usuario usuario);

    public abstract UsuarioResumo modelParaResumo(Usuario usuario);

}
