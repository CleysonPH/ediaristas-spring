package br.com.treinaweb.ediaristas.servicos.admin.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.ediaristas.servicos.admin.dtos.ServicoForm;
import br.com.treinaweb.ediaristas.servicos.admin.services.AdminServicoService;
import br.com.treinaweb.ediaristas.servicos.enums.Icone;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/admin/servicos")
@AllArgsConstructor
public class ServicoController {

    private AdminServicoService servicoService;

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("admin/servicos/form");

        modelAndView.addObject("form", new ServicoForm());

        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @ModelAttribute("form") ServicoForm form, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "admin/servicos/form";
        }

        servicoService.cadastrar(form);

        return "redirect:/admin/servicos";
    }

    @GetMapping
    public ModelAndView listar() {
        var modelAndView = new ModelAndView("admin/servicos/lista");

        modelAndView.addObject("servicos", servicoService.buscarTodosResumidos());

        return modelAndView;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        var modelAndView = new ModelAndView("admin/servicos/form");

        modelAndView.addObject("form", servicoService.buscarFormPorId(id));

        return modelAndView;
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable Long id, @Valid @ModelAttribute("form") ServicoForm form, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "admin/servicos/form";
        }

        servicoService.editar(form, id);

        return "redirect:/admin/servicos";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        servicoService.excluirPorId(id);

        return "redirect:/admin/servicos";
    }

    @ModelAttribute("icones")
    public Icone[] getIcones() {
        return Icone.values();
    }

}
