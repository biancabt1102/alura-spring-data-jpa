package br.com.alura.spring.data.service;

import br.com.alura.spring.data.entities.Funcionario;
import br.com.alura.spring.data.entities.FuncionarioProjecao;
import br.com.alura.spring.data.repositories.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {
    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final FuncionarioRepository funcionarioRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner sc) {
        while (system) {
            System.out.println("Qual acao de cargo deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca funcionario nome");
            System.out.println("2 - Busca funcionario nome, data contratacao e salario maior");
            System.out.println("3 - Busca funcionario data contratacao");
            System.out.println("4 - Pesquisa funcionario salario");

            int action = sc.nextInt();

            switch (action) {
                case 1:
                    buscaFuncionarioNome(sc);
                    break;
                case 2:
                    buscaFuncionarioNomeSalarioMaiorData(sc);
                    break;
                case 3:
                    buscaFuncionarioDataContratacao(sc);
                case 4:
                    pesquisaFuncionarioSalario();
                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscaFuncionarioNome(Scanner sc){
        System.out.println("Qual nome deseja pesquisar");
        String nome = sc.next();

        List<Funcionario> list = funcionarioRepository.findByNome(nome);
        list.forEach(System.out::println);
    }

    private void buscaFuncionarioNomeSalarioMaiorData(Scanner sc){
        System.out.println("Digite o nome que deseja pesquisar");
        String nome =sc.next();

        System.out.println("Digite o salario que deseja pesquisar");
        Double salario = sc.nextDouble();

        System.out.println("Digite a data de contratacao que deseja pesquisar");
        String data = sc.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
        list.forEach(System.out::println);
    }

    private void buscaFuncionarioDataContratacao(Scanner sc){
        System.out.println("Digite a data de contratacao que deseja pesquisar");
        String data = sc.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
        list.forEach(System.out::println);
    }

    private void pesquisaFuncionarioSalario(){
        List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
        list.forEach(f -> System.out.println("Funcionario: id: " + f.getId()
                + " | nome: " + f.getNome() + " | salario: " + f.getSalario()));

    }
}
