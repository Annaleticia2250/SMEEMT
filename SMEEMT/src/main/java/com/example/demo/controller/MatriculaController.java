package com.example.demo.controller;

import com.example.demo.model.Aluno;
import com.example.demo.model.Curso;
import com.example.demo.model.Matricula;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public String listarMatriculas(Model model) {
        model.addAttribute("matricula", new Matricula());
        model.addAttribute("matriculas", matriculaRepository.findAll());
        model.addAttribute("alunos", alunoRepository.findAll());
        model.addAttribute("cursos", cursoRepository.findAll());
        return "cadastro-matricula"; // nome do HTML
    }

    @PostMapping("/salvar")
    public String salvarMatricula(
            @RequestParam("aluno") Long alunoId,
            @RequestParam("curso") Long cursoId,
            @ModelAttribute Matricula matricula,
            RedirectAttributes redirectAttributes) {

        try {
            Aluno aluno = alunoRepository.findById(alunoId).orElse(null);
            Curso curso = cursoRepository.findById(cursoId).orElse(null);

            if (aluno == null || curso == null) {
                redirectAttributes.addFlashAttribute("error", "Aluno ou Curso inválido!");
                return "redirect:/matriculas";
            }

            matricula.setAluno(aluno);
            matricula.setCurso(curso);
            matriculaRepository.save(matricula);

            redirectAttributes.addFlashAttribute("success", "Matrícula salva com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao salvar matrícula!");
        }

        return "redirect:/matriculas";
    }

    @GetMapping("/aluno/{id}")
    public String listarPorAluno(@PathVariable("id") Long alunoId, Model model) {
        var aluno = alunoRepository.findById(alunoId).orElse(null);

        if (aluno == null) {
            model.addAttribute("error", "Aluno não encontrado!");
            return "redirect:/matriculas";
        }

        model.addAttribute("aluno", aluno);
        model.addAttribute("matriculas", matriculaRepository.findByAluno(aluno));
        return "matriculas-por-aluno"; // novo template
    }
}
