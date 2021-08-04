package br.com.treinaweb.ediaristas.usuarios.admin.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.treinaweb.ediaristas.auth.validators.UsuarioSenhaValidator;
import br.com.treinaweb.ediaristas.dtos.FlashMessage;
import br.com.treinaweb.ediaristas.usuarios.admin.dtos.AlterarSenhaFrom;
import br.com.treinaweb.ediaristas.usuarios.admin.dtos.UsuarioCadastroForm;
import br.com.treinaweb.ediaristas.usuarios.admin.dtos.UsuarioEdicaoForm;
import br.com.treinaweb.ediaristas.usuarios.admin.services.AdminUsuarioService;
import br.com.treinaweb.ediaristas.usuarios.exceptions.SenhaIncorretaException;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/admin/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private AdminUsuarioService usuarioService;

    @InitBinder({"cadastroForm", "alterarSenhaForm"})
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(new UsuarioSenhaValidator());
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("admin/usuarios/cadastro_form");

        modelAndView.addObject("cadastroForm", new UsuarioCadastroForm());

        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @ModelAttribute("cadastroForm") UsuarioCadastroForm cadastroForm, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "admin/usuarios/cadastro_form";
        }

        usuarioService.cadastrar(cadastroForm);

        return "redirect:/admin/usuarios";
    }

    @GetMapping
    public ModelAndView listar() {
        var modelAndView = new ModelAndView("admin/usuarios/lista");

        modelAndView.addObject("usuarios", usuarioService.buscarTodos());

        return modelAndView;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        var modelAndView = new ModelAndView("admin/usuarios/edicao_form");

        modelAndView.addObject("edicaoForm", usuarioService.buscarFormPorId(id));

        return modelAndView;
    }

    @PostMapping("/{id}/editar")
    public String editar(
        @PathVariable Long id, @Valid @ModelAttribute("edicaoForm") UsuarioEdicaoForm edicaoForm, BindingResult resultado
    ) {
        if (resultado.hasErrors()) {
            return "admin/usuarios/edicao_form";
        }

        usuarioService.editar(edicaoForm, id);

        return "redirect:/admin/usuarios";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        usuarioService.excluirPorId(id);

        return "redirect:/admin/usuarios";
    }

    @GetMapping("/editar-senha")
    public ModelAndView editarSenha() {
        var modelAndView = new ModelAndView("/admin/usuarios/editar_senha");

        modelAndView.addObject("alterarSenhaForm", new AlterarSenhaFrom());

        return modelAndView;
    }

    @PostMapping("editar-senha")
    public String editarSenha(
        @Valid @ModelAttribute("alterarSenhaForm") AlterarSenhaFrom alterarSenhaFrom,
        BindingResult resultado,
        Principal principal,
        RedirectAttributes attrs
    ) {
        if (resultado.hasErrors()) {
            return "/admin/usuarios/editar_senha";
        }

        try {
            usuarioService.editarSenha(alterarSenhaFrom, principal);
        } catch (SenhaIncorretaException e) {
            resultado.rejectValue("senhaAntiga", "br.com.treinaweb.ediaristas.senhaAntiga.incorreta");
        }

        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Senha alterada com sucesso"));

        return "/admin/usuarios/editar_senha";
    }

}
