package br.com.treinaweb.ediaristas.usuarios.admin.services;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.usuarios.admin.dtos.AlterarSenhaFrom;
import br.com.treinaweb.ediaristas.usuarios.admin.dtos.UsuarioCadastroForm;
import br.com.treinaweb.ediaristas.usuarios.admin.dtos.UsuarioEdicaoForm;
import br.com.treinaweb.ediaristas.usuarios.admin.dtos.UsuarioResumo;
import br.com.treinaweb.ediaristas.usuarios.admin.mappers.AdminUsuarioMapper;
import br.com.treinaweb.ediaristas.usuarios.enums.Tipo;
import br.com.treinaweb.ediaristas.usuarios.exceptions.SenhaIncorretaException;
import br.com.treinaweb.ediaristas.usuarios.services.UsuarioService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminUsuarioService {

    private UsuarioService usuarioService;

    private PasswordEncoder passwordEncoder;

    private static final AdminUsuarioMapper MAPPER = AdminUsuarioMapper.INSTANCE;

    public void cadastrar(UsuarioCadastroForm form) {
        var usuario = MAPPER.cadastroFormParaModel(form);

        usuario.setTipo(Tipo.ADMIN);
        usuarioService.cadastrar(usuario);
    }

    public List<UsuarioResumo> buscarTodos() {
        return usuarioService.buscarTodos()
            .stream()
            .map(MAPPER::modelParaResumo)
            .collect(Collectors.toList());
    }

    public UsuarioEdicaoForm buscarFormPorId(Long id) {
        var usuario = usuarioService.buscarPorId(id);

        return MAPPER.modelParaEdicaoForm(usuario);
    }

    public void editar(UsuarioEdicaoForm form, Long id) {
        var usuario = usuarioService.buscarPorId(id);

        usuario.setNome(form.getNome());
        usuario.setEmail(form.getEmail());

        usuarioService.editar(usuario, id);
    }

    public void excluirPorId(Long id) {
        usuarioService.excluirPorId(id);
    }

    public void editarSenha(AlterarSenhaFrom form, Principal principal) {
        var usuario = usuarioService.buscarPorEmail(principal.getName());

        var senhaAtual = usuario.getSenha();
        var senhaAtualForm = form.getSenhaAntiga();
        var senhaNova = form.getSenha();
        var id = usuario.getId();

        if (!passwordEncoder.matches(senhaAtualForm, senhaAtual)) {
            throw new SenhaIncorretaException("A senha antiga está incorreta");
        }

        usuario.setSenha(senhaNova);

        usuarioService.editar(usuario, id);
    }

}
