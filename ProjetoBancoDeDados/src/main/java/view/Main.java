package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import controller.AlunoController;
import controller.EditalDeMonitoriaController;
import dto.AlunoDTO;
import dto.DisciplinaDTO;
import dto.EditalDeMonitoriaDTO;

public class Main {
	public static void main(String[] args) {
		System.out.println("Bem-vindo ao Sistema de Gestão de Alunos e Editais!");

        Scanner leitor = new Scanner(System.in);
     	AlunoController alunoController = new AlunoController();
     	EditalDeMonitoriaController editalController = new EditalDeMonitoriaController();
 		String escolha = "";
 		
         while(!escolha.equals("s")) {
 			System.out.println("1 - Novo aluno");
 			System.out.println("2 - Listar todos os alunos:");
 			System.out.println("3 - Exibir informações de um aluno específico:");
 			System.out.println("4 - Novo Edital");
 			System.out.println("5 - Informar a quantidade de Editas cadastrados:");
 			System.out.println("6 - Detalhar um edital específico:");
 			System.out.println("7 - Inscrever Aluno em Edital:");
 			System.out.println("8 - Excluir edital:");
 			System.out.println("S - Sair");
 			
 			System.out.print("Escolha uma opção:");
 			escolha = leitor.nextLine().toLowerCase();
 			
 			switch(escolha) {
 			case "1":
 				AlunoDTO aluno = new AlunoDTO();
 				
 				System.out.print("Digite o nome do aluno: ");
 				aluno.setNome(leitor.nextLine());

 				System.out.print("Digite a matricula do aluno: ");
 				aluno.setMatricula(leitor.nextLine());
 				
 				System.out.print("Digite o email do aluno: ");
 				aluno.setEmail(leitor.nextLine());
 				
 				alunoController.salvarAluno(aluno);
 				break;
 			
 			case "2":
 				for(AlunoDTO dto: alunoController.listarAlunos()) {
 					System.out.println(dto.getNome() +" "+ dto.getMatricula() +" "+ dto.getEmail());
 				}
 				break;
 			
 			case "3":				
 				AlunoDTO alunoDTO = new AlunoDTO();

 				System.out.print("Digite a matricula do aluno: ");
 				alunoDTO.setMatricula(leitor.nextLine());
 				
 				alunoDTO = alunoController.buscarAlunoPorMatricula(alunoDTO);
 				
 				if(alunoDTO != null) {
 					System.out.println("Nome: " + alunoDTO.getNome() +"\nEmail: "+ alunoDTO.getEmail());					
 				}
 				break;
 			
 			case "4":
 				EditalDeMonitoriaDTO editalDTO = new EditalDeMonitoriaDTO();
 				System.out.print("Digite o número do edital: ");
 				editalDTO.setNumero(leitor.nextLine());

 				System.out.print("Digite a data inicial do edital (dd/mm/aaaa): ");
 				String dataIncio = leitor.nextLine();
 				DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
 				LocalDateTime dataFormatadaInicio = LocalDate.parse(dataIncio,parser).atStartOfDay();
 				editalDTO.setDataInicio(dataFormatadaInicio);

 				System.out.print("Digite a data final do edital (dd/mm/aaaa): ");
 				String dataFinal = leitor.nextLine();
 				LocalDateTime dataFormatadaFinal = LocalDate.parse(dataFinal, parser).atStartOfDay();
 				editalDTO.setDataFinal(dataFormatadaFinal);

 				System.out.print("Quantas disciplinas terão vaga no edital? ");
 				int quantidade = Integer.parseInt(leitor.nextLine());

 				for (int num = 0; num<quantidade; num++) {
 					
 					DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
 					
 					System.out.print("Informe o nome da disciplina: ");
 					disciplinaDTO.setNome(leitor.nextLine());

 					System.out.print("Quantas vagas essa disciplina terá? ");
 					disciplinaDTO.setQuantidadeDeVagas(Integer.parseInt(leitor.nextLine()));

 					editalDTO.getDisciplinas().add(disciplinaDTO);
 				}
 				
 				if(editalController.criarEdital(editalDTO)) {
 					System.out.println("Edital cadastrado com sucesso!");
 				}else {
 					System.out.println("Falha ao cadastrar edital!");
 				}
 				break;
 				
 			case "5":
 				System.out.println("Editais cadastrados: " + editalController.listarTodosEditais().size());
 				break;
 				
 			case "6":
 				EditalDeMonitoriaDTO editalParaDetalhar = new EditalDeMonitoriaDTO();
 				System.out.print("Digite o id do edital: ");
 				editalParaDetalhar.setId(Long.parseLong(leitor.nextLine()));
 				
 				editalController.detalharEdital(editalParaDetalhar);
 				break;
 				
 			case "7":
 				System.out.print("Informe a matrícula do aluno: ");
 				AlunoDTO dtoAluno = new AlunoDTO();
 				dtoAluno.setMatricula(leitor.nextLine());
 		
 				dtoAluno = alunoController.buscarAlunoPorMatricula(dtoAluno);

 				System.out.print("Informe o id do edital: ");
 				EditalDeMonitoriaDTO dtoEdital = new EditalDeMonitoriaDTO();
 				dtoEdital.setId(Long.parseLong(leitor.nextLine()));
 				dtoEdital = editalController.buscarEditalPorId(dtoEdital);

 				System.out.print("Informe o nome da disciplina da vaga: ");
 				DisciplinaDTO dtoDisciplina = new DisciplinaDTO();
 				dtoDisciplina.setNome(leitor.nextLine());
 				
 				boolean inscrito = editalController.inscrever(dtoEdital, dtoAluno, dtoDisciplina);
 				if(inscrito) {
 					System.out.println("Inscrição realizada");
 				}else {
 					System.out.println("Falha em realizar inscrição");
 				}
 				break;
 				
 			case "8":
 				EditalDeMonitoriaDTO edital = new EditalDeMonitoriaDTO();
 				System.out.print("Digite o id do edital: ");
 				edital.setId(Long.parseLong(leitor.nextLine()));
 				
 				if(editalController.deletarEdital(edital)) {
 					System.out.println("Edital excluído com sucesso!");
 				}else {
 					System.out.println("Erro ao excluir edital");
 				}
 				break;
 			}
         }
    }
}