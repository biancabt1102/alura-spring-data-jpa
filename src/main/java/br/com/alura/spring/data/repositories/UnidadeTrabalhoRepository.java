package br.com.alura.spring.data.repositories;

import br.com.alura.spring.data.entities.UnidadeDeTrabalho;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeTrabalhoRepository extends CrudRepository<UnidadeDeTrabalho, Integer> {

}
