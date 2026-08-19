package br.gov.sp.cps.springlab420262.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.cps.springlab420262.entity.Disciplina;
import br.gov.sp.cps.springlab420262.repository.DisciplinaRepository;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    private final DisciplinaRepository repo;

    public DisciplinaServiceImpl(DisciplinaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Disciplina cadastrar(Disciplina disciplina) {
        if(disciplina == null ||
              disciplina.getCodigo() == null || 
              disciplina.getCodigo().isBlank() ||
              disciplina.getNome() == null || 
              disciplina.getNome().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados da disciplina inválidos.");
        }
        if(disciplina.getCargaHoraria() != null && disciplina.getCargaHoraria() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carga horária inválida.");
        }
        return repo.save(disciplina);
    }

    @Override
    public Disciplina buscarPorId(Long id) {
        if(id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID não pode ser nulo.");
        }
        return repo.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina não encontrada."));
    }

    @Override
    public List<Disciplina> buscarTodos() {
        return repo.findAll();
    }
    
}
