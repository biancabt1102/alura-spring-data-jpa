package br.com.alura.spring.data.service;

import br.com.alura.spring.data.entities.Funcionario;
import br.com.alura.spring.data.repositories.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {
    private final FuncionarioRepository funcionarioRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner sc){
        System.out.println("Digite um nome");
        String nome = sc.next();

        if(nome.equalsIgnoreCase("NULL")){
            nome = null;
        }

        System.out.println("Digite um cpf");
        String cpf = sc.next();

        if(cpf.equalsIgnoreCase("NULL")){
            cpf = null;
        }

        System.out.println("Digite um salario");
        Double salario = sc.nextDouble();

        if(salario == 0){
            salario = null;
        }

        System.out.println("Digite a data de contratacao");
        String data = sc.next();

        LocalDate dataContratacao;

        if(data.equalsIgnoreCase("NULL")){
            dataContratacao = null;
        } else{
            dataContratacao = LocalDate.parse(data, formatter);
        }

        List<Funcionario> funcionarios = funcionarioRepository.findAll(
                Specification.where(SpecificationFuncionario.nome(nome))
                        .or(SpecificationFuncionario.cpf(cpf))
                        .or(SpecificationFuncionario.salario(salario))
                        .or(SpecificationFuncionario.dataContratacao(dataContratacao))
                );
        funcionarios.forEach(System.out::println);
    }
}
