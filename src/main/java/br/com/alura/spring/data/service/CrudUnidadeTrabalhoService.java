package br.com.alura.spring.data.service;

import br.com.alura.spring.data.entities.UnidadeDeTrabalho;
import br.com.alura.spring.data.repositories.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {
    private Boolean system = true;

    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial(Scanner sc){
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
                    visualizar();
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
        System.out.println("Digite o nome da unidade");
        String nome = sc.next();

        System.out.println("Digite o endereco");
        String endereco = sc.next();

        UnidadeDeTrabalho unidadeDeTrabalho = new UnidadeDeTrabalho();
        unidadeDeTrabalho.setDescricao(nome);
        unidadeDeTrabalho.setEndereco(endereco);

        unidadeTrabalhoRepository.save(unidadeDeTrabalho);
        System.out.println("Salvo!");
    }

    private void atualizar(Scanner sc){
        System.out.println("Digite o id");
        int id = sc.nextInt();

        System.out.println("Digite o nome da unidade");
        String nome = sc.next();

        System.out.println("Digite o endereco");
        String endereco = sc.next();

        UnidadeDeTrabalho unidadeDeTrabalho = new UnidadeDeTrabalho();
        unidadeDeTrabalho.setId(id);
        unidadeDeTrabalho.setDescricao(nome);
        unidadeDeTrabalho.setEndereco(endereco);

        unidadeTrabalhoRepository.save(unidadeDeTrabalho);
        System.out.println("Alterado!");
    }

    private void visualizar(){
        Iterable<UnidadeDeTrabalho> unidades = unidadeTrabalhoRepository.findAll();
        unidades.forEach(unidade -> System.out.println(unidade));
    }

    private void delete(Scanner sc){
        System.out.println("Id");
        int id = sc.nextInt();

        unidadeTrabalhoRepository.deleteById(id);
        System.out.println("Deletado!");
    }
}
