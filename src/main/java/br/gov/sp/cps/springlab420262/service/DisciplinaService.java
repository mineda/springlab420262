package br.gov.sp.cps.springlab420262.service;

import java.util.List;

import br.gov.sp.cps.springlab420262.entity.Disciplina;

public interface DisciplinaService {

    public Disciplina cadastrar(Disciplina disciplina);

    public Disciplina buscarPorId(Long id);

    public List<Disciplina> buscarTodos();

    public void matricularAluno(Long disciplinaId, Long alunoId);
    
}
