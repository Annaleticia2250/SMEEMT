package com.example.demo.controller;

import com.example.demo.model.Curso;
import com.example.demo.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // Página de cadastro
    @GetMapping("/cursos/cadastro")
    public String exibirCadastro(Model model) {
        model.addAttribute("curso", new Curso()); // objeto vazio pro formulário
        model.addAttribute("cursos", cursoService.listarTodos()); // lista de cursos
        return "cadastro-curso"; // nome do arquivo HTML em templates/
    }

    // Salvamento
    @PostMapping("/cursos/salvar")
    public String salvarCurso(Curso curso) {
        cursoService.salvar(curso);
        return "redirect:/cursos/cadastro"; // redireciona pra página de listagem após salvar
    }
}
