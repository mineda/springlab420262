package br.gov.sp.cps.springlab420262.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.cps.springlab420262.entity.Disciplina;
import br.gov.sp.cps.springlab420262.service.DisciplinaService;

@RestController
@CrossOrigin
@RequestMapping("/disciplina")
public class DisciplinaController {

    private final DisciplinaService service;

    public DisciplinaController(DisciplinaService service) {
        this.service = service;
    }

    @GetMapping
    @JsonView(View.DisciplinaView.class)
    public List<Disciplina> buscarTodos() {
        return service.buscarTodos();
    }

    @GetMapping("/{id}")
    @JsonView(View.DisciplinaView.class)
    public Disciplina buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/param")
    @JsonView(View.DisciplinaView.class)
    public Disciplina buscarPorIdParametro(@RequestParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @JsonView(View.DisciplinaView.class)
    public ResponseEntity<Disciplina> cadastrar(@RequestBody Disciplina disciplina) {
        Disciplina novaDisciplina = service.cadastrar(disciplina);
        return ResponseEntity.
            created(URI.create("/disciplina/" + novaDisciplina.getId())).
            body(novaDisciplina);
    }
    
}
