package br.com.alura.spring.data.repositories;

import br.com.alura.spring.data.entities.Funcionario;
import br.com.alura.spring.data.entities.FuncionarioProjecao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>,
        JpaSpecificationExecutor<Funcionario> {
    List<Funcionario> findByNome(String nome);

    @Query("SELECT f FROM Funcionario f WHERE f.nome = :nome " +
            "AND f.salario >= :salario AND f.dataContratacao = :dataContratacao")
    List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate dataContratacao);

//  NativeQuery é codigo do banco de dados
    @Query(value = "SELECT * FROM Funcionarios f WHERE f.data_contratacao >= :data", nativeQuery = true)
    List<Funcionario> findDataContratacaoMaior(LocalDate data);

    @Query(value = "SELECT f.id, f.nome, f.salario FROM Funcionarios f", nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalario();
}
