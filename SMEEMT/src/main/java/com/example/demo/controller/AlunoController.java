package com.example.demo.controller;

import com.example.demo.model.Aluno;
import com.example.demo.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/alunos")
    public String exibirCadastro(Model model) {
        model.addAttribute("aluno", new Aluno()); // objeto vazio pro formulário
        model.addAttribute("alunos", alunoService.listarTodos()); // lista dos alunos
        return "cadastro-aluno"; // página principal
    }

    @PostMapping("/alunos/salvar")
    public String salvarAluno(Aluno aluno, RedirectAttributes redirectAttributes) {
        try {
            alunoService.salvar(aluno);
            redirectAttributes.addFlashAttribute("success", "Aluno salvo com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao salvar o aluno!");
        }
        return "redirect:/alunos"; // volta pra mesma página após salvar
    }
}
