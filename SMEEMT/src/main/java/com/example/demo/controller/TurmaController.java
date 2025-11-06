package com.example.demo.controller;

import com.example.demo.model.Turma;
import com.example.demo.model.Componente;
import com.example.demo.repository.TurmaRepository;
import com.example.demo.repository.ComponenteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaRepository turmaRepository;
    private final ComponenteRepository componenteRepository;

    public TurmaController(TurmaRepository turmaRepository, ComponenteRepository componenteRepository) {
        this.turmaRepository = turmaRepository;
        this.componenteRepository = componenteRepository;
    }

    // Exibe a página de turmas
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("turmas", turmaRepository.findAll());
        model.addAttribute("turma", new Turma());
        model.addAttribute("componentes", componenteRepository.findAll()); // para o <select>
        return "turmas"; // nome do arquivo HTML (turmas.html)
    }

    // Salva uma nova turma
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Turma turma,
                         @RequestParam("componente") Long componenteId,
                         RedirectAttributes redirectAttributes) {

        try {
            Optional<Componente> componenteOpt = componenteRepository.findById(componenteId);

            if (componenteOpt.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Componente inválido!");
                return "redirect:/turmas";
            }

            turma.setComponente(componenteOpt.get());
            turmaRepository.save(turma);
            redirectAttributes.addFlashAttribute("success", "Turma salva com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao salvar turma!");
        }

        return "redirect:/turmas";
    }
}
