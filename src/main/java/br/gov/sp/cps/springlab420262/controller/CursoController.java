package br.gov.sp.cps.springlab420262.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.cps.springlab420262.entity.Curso;
import br.gov.sp.cps.springlab420262.service.CursoService;

@RestController
@CrossOrigin
@RequestMapping("/curso")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    @JsonView(View.CursoView.class)
    public List<Curso> buscarTodos() {
        return service.buscarTodos();
    }
    
}
