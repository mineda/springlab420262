package br.gov.sp.cps.springlab420262.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.cps.springlab420262.entity.Curso;
import br.gov.sp.cps.springlab420262.repository.CursoRepository;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository repo;

    public CursoServiceImpl(CursoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Curso cadastrar(Curso curso) {
        if(curso == null ||
              curso.getId() != null ||
              curso.getSigla() == null || 
              curso.getSigla().isBlank() ||
              curso.getNome() == null || 
              curso.getNome().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados do curso inválidos.");
        }
        return repo.save(curso);
    }

    @Override
    public Curso buscarPorId(Long id) {
        if(id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID não pode ser nulo.");
        }
        return repo.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado.")
        );
    }

    @Override
    public List<Curso> buscarTodos() {
        return repo.findAll();
    }
    
}
