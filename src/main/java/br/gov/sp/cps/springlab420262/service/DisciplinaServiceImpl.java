package br.gov.sp.cps.springlab420262.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.cps.springlab420262.entity.Aluno;
import br.gov.sp.cps.springlab420262.entity.Disciplina;
import br.gov.sp.cps.springlab420262.repository.DisciplinaRepository;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    private final DisciplinaRepository repo;

    private final CursoService cursoService;

    private final AlunoService alunoService;

    public DisciplinaServiceImpl(DisciplinaRepository repo, CursoService cursoService, AlunoService alunoService) {
        this.repo = repo;
        this.cursoService = cursoService;
        this.alunoService = alunoService;
    }

    @Override
    @Transactional
    public Disciplina cadastrar(Disciplina disciplina) {
        if(disciplina == null ||
              disciplina.getId() != null ||
              disciplina.getCodigo() == null || 
              disciplina.getCodigo().isBlank() ||
              disciplina.getNome() == null || 
              disciplina.getNome().isBlank() ||
              disciplina.getCurso() == null || 
              disciplina.getCurso().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados da disciplina inválidos.");
        }
        if(disciplina.getCargaHoraria() != null && disciplina.getCargaHoraria() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carga horária inválida.");
        }
        disciplina.setCurso(cursoService.buscarPorId(disciplina.getCurso().getId()));
        if(disciplina.getAlunos() != null && !disciplina.getAlunos().isEmpty()) {
            Set<Aluno> alunos = new HashSet<>();
            disciplina.getAlunos().forEach(aluno -> {
                alunos.add(alunoService.buscarPorId(aluno.getId()));
            });
            disciplina.setAlunos(alunos);
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

    @Override
    @Transactional
    public void matricularAluno(Long disciplinaId, Long alunoId) {
        Disciplina disciplina = buscarPorId(disciplinaId);
        Aluno aluno = alunoService.buscarPorId(alunoId);
        if(disciplina.getAlunos() == null) {
            disciplina.setAlunos(new HashSet<>());
        }
        disciplina.getAlunos().add(aluno);
        
        repo.save(disciplina);
    }
    
}
