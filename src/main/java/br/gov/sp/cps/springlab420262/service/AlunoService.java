package br.gov.sp.cps.springlab420262.service;

import java.util.List;

import br.gov.sp.cps.springlab420262.entity.Aluno;

public interface AlunoService {
    
    public Aluno cadastrar(Aluno aluno);

    public Aluno buscarPorId(Long id);

    public List<Aluno> buscarTodos();
    
}
