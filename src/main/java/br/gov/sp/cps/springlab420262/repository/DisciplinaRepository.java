package br.gov.sp.cps.springlab420262.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.cps.springlab420262.entity.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    
}
