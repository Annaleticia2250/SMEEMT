package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String curso;         // categoria / tipo do curso
    private String descricao;
    private Integer cargaHoraria; // Integer facilita o binding no formulário

    // Construtor vazio (necessário para JPA/Thymeleaf)
    public Curso() {
    }

    // Construtor completo
    public Curso(Long id, String nome, String curso, String descricao, Integer cargaHoraria) {
        this.id = id;
        this.nome = nome;
        this.curso = curso;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(Integer cargaHoraria) { this.cargaHoraria = cargaHoraria; }

    @Override
    public String toString() {
        return "Curso{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", curso='" + curso + '\'' +
               ", descricao='" + descricao + '\'' +
               ", cargaHoraria=" + cargaHoraria +
               '}';
    }
}
