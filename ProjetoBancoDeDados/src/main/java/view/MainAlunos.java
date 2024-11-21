package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainAlunos {
	
	/* public static void main(String[] args) throws Exception {
		Scanner leitor = new Scanner(System.in);
		Persistencia persistencia = new Persistencia();
		CentralDeInformacoes central = new CentralDeInformacoes();

		try {
			central = persistencia.recuperarCentral("central.xml");
		} catch (Exception e) {
			System.out.println("Não foi possível recuperar as informações da central.");
		}


		while (true) {
			System.out.println("Escolha uma opção:");
			System.out.println("1 - Novo aluno");
			System.out.println("2 - Listar todos os alunos:");
			System.out.println("3 - Exibir informações de um aluno específico:");
			System.out.println("4 - Novo Edital");
			System.out.println("5 - Informar a quantidade de Editas cadastrados:");
			System.out.println("6 - Detalhar um edital específico:");
			System.out.println("7 - Inscrever Aluno em Edital:");
			System.out.println("8 - Gerar comprovante de Inscrição ao edital:");
			System.out.println("S - Sair");
			
			String escolha = leitor.nextLine().toLowerCase();
			switch (escolha) {
				case "1":
					Sexo sexo;
					String nome;
					String sexoStr;
					String matricula;
					String email;
					String senha;
					System.out.print("Digite o nome do aluno: ");
					nome = leitor.nextLine();
					while (true) {
						System.out.print("Digite o sexo do aluno: ");
						sexoStr = leitor.nextLine();
						if (sexoStr.equalsIgnoreCase("feminino")) {
							sexo = Sexo.FEMININO;
							break;
						} else if (sexoStr.equalsIgnoreCase("masculino")) {
							sexo = Sexo.MASCULINO;
							break;
						} else {
							System.out.println("Por favor, digite novamente. O gênero deve ser 'feminino' ou 'masculino'.");
						}
					}
					System.out.print("Digite a matricula do aluno: ");
					matricula = leitor.nextLine();
					System.out.print("Digite o email do aluno: ");
					email = leitor.nextLine();
					System.out.print("Digite a senha do email do aluno: ");
					senha = leitor.nextLine();
					Aluno aluno = new Aluno(nome, sexo, matricula, email, senha);
					central.adicionarAluno(aluno);
					persistencia.salvarCentral(central,"central.xml");
					break;
	
				case "2":
					central.listarTodosOsAlunos();
					break;
	
				case "3":
					System.out.print("Digite a matrícula do aluno que deseja ver as informações:");
					matricula = leitor.nextLine();
					aluno = central.recuperarAlunoPorMatricula(matricula);
					if (aluno != null) {
						System.out.println("Informações do aluno:");
						System.out.println(aluno.toString());
					} else {
						System.out.println("Aluno não encontrado.");
					}
					break;
	
				case "4":
					System.out.print("Digite o número do edital: ");
					String numEdital=leitor.nextLine();
	
					System.out.print("Digite a data inicial do edital (dd/mm/aaaa): ");
					String dataIncio = leitor.nextLine();
					DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDateTime dataFormatadaInicio = LocalDate.parse(dataIncio,parser).atStartOfDay();
	
					System.out.print("Digite a data final do edital (dd/mm/aaaa): ");
					String dataFinal = leitor.nextLine();
					LocalDateTime dataFormatadaFinal = LocalDate.parse(dataFinal, parser).atStartOfDay();
	
					EditalDeMonitoria edital = new EditalDeMonitoria(numEdital, dataFormatadaInicio,dataFormatadaFinal);
	
					System.out.print("Quantas disciplinas terão vaga no edital? ");
					int quantidade = Integer.parseInt(leitor.nextLine());
	
					for (int num = 0; num<quantidade; num++) {
						System.out.print("Informe o nome da disciplina: ");
						String disciplina = leitor.nextLine();
	
						System.out.print("Quantas vagas essa disciplina terá? ");
						int quantVagas = Integer.parseInt(leitor.nextLine());
	
						Vaga vaga = new Vaga();
						vaga.setDisciplina(disciplina);
						vaga.setQuantidadeDeVagas(quantVagas);
						edital.getVagas().add(vaga);
					}
	
					central.adicionarEdital(edital);
					break;
					
				case "5":
					if (central.getTodosOsEditais() != null) {
						System.out.println(central.getTodosOsEditais().size() + " editais cadastrados.");		
					}else {
						System.out.println("Nenhum edital cadastrado");
					}
					break;
					
				case "6":
					System.out.print("Informe o id do edital que deseja detalhar: ");
					long id = Long.parseLong(leitor.nextLine());
					edital = central.recuperarEditalPeloId(id);
	
					if (edital != null) {
						System.out.println(edital.toString());
					}else {
						System.out.println("Edital não encontrado.");
					}
					break;
	
				case "7":
					
					System.out.print("Informe a matrícula do aluno: ");
					matricula = leitor.nextLine();
	
					System.out.print("Informe o id do edital: ");
					id = Long.parseLong(leitor.nextLine());
	
					System.out.print("Informe o nome da disciplina da vaga: ");
					String disciplina = leitor.nextLine();
	
					aluno = central.recuperarAlunoPorMatricula(matricula);
	
					if (central.recuperarEditalPeloId(id).inscrever(aluno, disciplina)) {
						Mensageiro.enviarEmail(aluno, "Inscrição realizada com sucesso!");
					}
					break;
	
				case "8":
					System.out.print("Informe a matrícula do aluno: ");
					matricula = leitor.nextLine();
	
					System.out.print("Informe o id do edital: ");
					id = Long.parseLong(leitor.nextLine());
	
					GeradorDeRelatorios.obterComprovanteDeInscricoesAluno(matricula, id, central);
					break;
	
				case "s":
					System.out.println("Programa Finalizado, informações salvas!");
					try {
						persistencia.salvarCentral(central, "central.xml");
					} catch (Exception e) {
						System.out.println("Não foi possível salvar as informações da central.");
					}
					leitor.close();
					System.exit(0);
					break;
					
				default:
					System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
			}
		}
	}
	*/
}
