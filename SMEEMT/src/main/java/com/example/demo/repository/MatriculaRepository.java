package com.example.demo.repository;

import com.example.demo.model.Matricula;
import com.example.demo.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    List<Matricula> findByAluno(Aluno aluno);
}
