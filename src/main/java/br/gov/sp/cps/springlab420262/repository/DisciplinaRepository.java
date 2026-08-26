package br.gov.sp.cps.springlab420262.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.cps.springlab420262.entity.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    public Optional<Disciplina> findByCodigo(String codigo);

    @Query("SELECT d FROM Disciplina d WHERE d.codigo = :codigo")
    public Optional<Disciplina> buscarPeloCodigo(String codigo);

    public List<Disciplina> findByCodigoContainingIgnoreCaseOrNomeContainingIgnoreCase(String codigo, String nome);

    @Query("SELECT d FROM Disciplina d WHERE lower(d.codigo) LIKE lower(%:codigo%) OR lower(d.nome) LIKE lower(%:nome%)")
    public List<Disciplina> buscarPorCodigoOuNome(String codigo, String nome);

    public List<Disciplina> findByCursoSigla(String sigla);

    @Query("SELECT d FROM Disciplina d JOIN d.curso c WHERE c.sigla = :sigla")
    public List<Disciplina> buscarPorSiglaCurso(String sigla);

    public List<Disciplina> findByAlunosRa(Long ra);

    @Query("SELECT d FROM Disciplina d JOIN d.alunos a WHERE a.ra = :ra")
    public List<Disciplina> buscarPorRaAluno(Long ra);
    
}
