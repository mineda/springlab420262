package br.gov.sp.cps.springlab420262.service;

import java.util.List;

import br.gov.sp.cps.springlab420262.entity.Curso;

public interface CursoService {

    public Curso cadastrar(Curso curso);

    public Curso buscarPorId(Long id);

    public List<Curso> buscarTodos();
    
}
