package com.example.demo.service;

import com.example.demo.model.Curso;
import com.example.demo.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public void salvar(Curso curso) {
        cursoRepository.save(curso);
    }
}
