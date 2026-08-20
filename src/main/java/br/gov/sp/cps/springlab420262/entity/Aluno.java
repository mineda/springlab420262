package br.gov.sp.cps.springlab420262.entity;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.cps.springlab420262.controller.View;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "aln_aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aln_id")
    @JsonView({View.DisciplinaView.class})
    private Long id;

    @Column(name = "aln_ra")
    @JsonView({View.DisciplinaView.class})
    private Long ra;

    @Column(name = "aln_nome")
    @JsonView({View.DisciplinaView.class})
    private String nome;

    @Column(name = "aln_data_nascimento")
    private LocalDate dataNascimento;

    @ManyToMany(mappedBy = "alunos")
    private Set<Disciplina> disciplinas;

    public Aluno(Long ra, String nome, LocalDate dataNascimento) {
        this.ra = ra;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Aluno() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRa() {
        return ra;
    }

    public void setRa(Long ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Set<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Set<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
    
}
