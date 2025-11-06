package com.example.demo.controller;

import com.example.demo.model.Componente;
import com.example.demo.model.Curso;
import com.example.demo.repository.ComponenteRepository;
import com.example.demo.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/componentes")
public class ComponenteController {

    @Autowired
    private ComponenteRepository componenteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("componentes", componenteRepository.findAll());
        model.addAttribute("componente", new Componente());
        model.addAttribute("cursos", cursoRepository.findAll());
        return "componentes"; // seu HTML
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Componente componente,
                         @RequestParam("cursoId") Long cursoId,
                         RedirectAttributes ra) {

        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);
        cursoOpt.ifPresent(componente::setCurso);

        componenteRepository.save(componente);
        ra.addFlashAttribute("success", "Componente salvo com sucesso!");
        return "redirect:/componentes";
    }
}
