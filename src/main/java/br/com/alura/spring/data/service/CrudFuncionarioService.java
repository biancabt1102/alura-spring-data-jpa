package br.com.alura.spring.data.service;

import br.com.alura.spring.data.entities.Cargo;
import br.com.alura.spring.data.entities.Funcionario;
import br.com.alura.spring.data.entities.UnidadeDeTrabalho;
import br.com.alura.spring.data.repositories.CargoRepository;
import br.com.alura.spring.data.repositories.FuncionarioRepository;
import br.com.alura.spring.data.repositories.UnidadeTrabalhoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.StyledEditorKit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {
    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository,
                                  CargoRepository cargoRepository,
                                  UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void iniciar(Scanner sc){
        while (system) {
            System.out.println("Qual acao de cargo deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");

            int action = sc.nextInt();

            switch (action) {
                case 1:
                    salvar(sc);
                    break;
                case 2:
                    atualizar(sc);
                    break;
                case 3:
                    visualizar(sc);
                    break;
                case 4:
                    delete(sc);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void salvar(Scanner sc){
        System.out.println("Digite o nome");
        String nome = sc.next();

        System.out.println("Digite o cpf");
        String cpf = sc.next();

        System.out.println("Digite o salario");
        Double salario = sc.nextDouble();

        System.out.println("Digite a data da contratacao");
        String data = sc.next();

        System.out.println("Digite o cargoId");
        Integer cargoId = sc.nextInt();

        List<UnidadeDeTrabalho> unidades = unidade(sc);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(data, formatter));

        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());
        funcionario.setUnidadeDeTrabalho(unidades);

        funcionarioRepository.save(funcionario);
        System.out.println("Salvo!");
    }

    private List<UnidadeDeTrabalho> unidade(Scanner sc){
        Boolean isTrue = true;
        List<UnidadeDeTrabalho> unidades = new ArrayList<>();

        while(isTrue){
            System.out.println("Digite o unidadeId (Para sair digite 0)");
            Integer unidadeId = sc.nextInt();
            if(unidadeId != 0){
                Optional<UnidadeDeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else{
                isTrue = false;
            }
        }
        return unidades;
    }
    private void atualizar(Scanner sc){
        System.out.println("Digite o id");
        Integer id = sc.nextInt();

        System.out.println("Digite o nome");
        String nome = sc.next();

        System.out.println("Digite o cpf");
        String cpf = sc.next();

        System.out.println("Digite o salario");
        Double salario = sc.nextDouble();

        System.out.println("Digite a data de contratacao");
        String data = sc.next();

        System.out.println("Digite o cargoId");
        Integer cargoId = sc.nextInt();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(data, formatter));

        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());

        funcionarioRepository.save(funcionario);
        System.out.println("Alterado!");

    }
    private void visualizar(Scanner sc){
        System.out.println("Qual página voce deseja visualizar");
        Integer page = sc.nextInt();

        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "nome"));
        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);

        System.out.println(funcionarios);
        System.out.println("Pagina atual " + funcionarios.getNumber());
        System.out.println("Total de elementos " + funcionarios.getTotalElements());

        funcionarios.forEach(funcionario -> System.out.println(funcionario));
    }
    private void delete(Scanner sc){
        System.out.println("Id");
        int id = sc.nextInt();

        funcionarioRepository.deleteById(id);
        System.out.println("Deletado!");
    }

}
