package br.com.alura.spring.data;

import br.com.alura.spring.data.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private Boolean system = true;
	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
	private final RelatoriosService relatoriosService;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

	public SpringDataApplication(CrudCargoService cargoService,
								 CrudFuncionarioService funcionarioService,
								 CrudUnidadeTrabalhoService unidadeTrabalhoService,
								 RelatoriosService relatoriosService,
								 RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
		this.relatoriosService = relatoriosService;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);
		while(system){
			System.out.println("Qual acao voce quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade");
			System.out.println("4 - Relatorios");
			System.out.println("5 - Relatorio dinamico");

			Integer action = sc.nextInt();

			switch (action){
				case 1:
					cargoService.inicial(sc);
					break;
				case 2:
					funcionarioService.iniciar(sc);
					break;
				case 3:
					unidadeTrabalhoService.inicial(sc);
					break;
				case 4:
					relatoriosService.inicial(sc);
					break;
				case 5:
					relatorioFuncionarioDinamico.inicial(sc);
					break;
				default:
					System.out.println("Finalizado");
					system = false;
					break;
			}
		}
	}
}
