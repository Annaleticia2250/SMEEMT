package com.example.demo.service;

import com.example.demo.model.Aluno;
import com.example.demo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public void salvar(Aluno aluno) {
        alunoRepository.save(aluno);
    }
}
