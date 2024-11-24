package view;

import java.util.List;
import java.util.Scanner;

import dao.AlunoDao;
import dto.AlunoDTO;
import mappers.MapperAluno;
import model.Aluno;

public class MainAlunos {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        MapperAluno mapper = new MapperAluno(); // Inicializa o mapeador de DTO
        AlunoDao alunoDao = new AlunoDao(mapper); // Inicializa o DAO

        System.out.println("Bem-vindo ao Sistema de Gestão de Alunos e Editais!");

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Cadastrar novo aluno");
            System.out.println("2 - Listar todos os alunos");
            System.out.println("3 - Buscar aluno por matrícula");
            System.out.println("4 - Editar informações de um aluno");
            System.out.println("S - Sair");

            String escolha = leitor.nextLine().toLowerCase();
            try {
                switch (escolha) {
                    case "1":
                        cadastrarAluno(alunoDao, leitor);
                        break;

                    case "2":
                        listarTodosOsAlunos(alunoDao);
                        break;

                    case "3":
                        buscarAlunoPorMatricula(alunoDao, leitor);
                        break;

                    case "4":
                        editarAluno(alunoDao, leitor);
                        break;

                    case "s":
                        System.out.println("Programa encerrado. Até logo!");
                        leitor.close();
                        alunoDao.fecharFactory(); // Fecha a factory ao sair
                        System.exit(0);

                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void cadastrarAluno(AlunoDao alunoDao, Scanner leitor) throws Exception {
        System.out.print("Digite o nome do aluno: ");
        String nome = leitor.nextLine();

        System.out.print("Digite a matrícula do aluno: ");
        String matricula = leitor.nextLine();

        System.out.print("Digite o email do aluno: ");
        String email = leitor.nextLine();

        System.out.print("Digite a senha do aluno: ");
        String senha = leitor.nextLine();

        System.out.print("Digite o gênero do aluno (M/F): ");
        String genero = leitor.nextLine().toUpperCase();

        AlunoDTO alunoDTO = new AlunoDTO(nome, matricula, email, senha);

        alunoDao.cadastrarAluno(alunoDTO);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    private static void listarTodosOsAlunos(AlunoDao alunoDao) throws Exception {
        List<AlunoDTO> alunos = alunoDao.listarTodosOsAlunos();

        if (alunos.isEmpty()) {
            System.out.println("Não há alunos cadastrados no momento.");
        } else {
            System.out.println("\nLista de Alunos:");
            for (AlunoDTO aluno : alunos) {
                System.out.println(aluno);
            }
        }
    }

    private static void buscarAlunoPorMatricula(AlunoDao alunoDao, Scanner leitor) throws Exception {
        System.out.print("Digite a matrícula do aluno que deseja buscar: ");
        String matricula = leitor.nextLine();

        AlunoDTO aluno = alunoDao.buscarAluno(new AlunoDTO(matricula));
        if (aluno != null) {
            System.out.println("\nInformações do Aluno:");
            System.out.println(aluno);
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    private static void editarAluno(AlunoDao alunoDao, Scanner leitor) throws Exception {
        System.out.print("Digite a matrícula do aluno que deseja editar: ");
        String matricula = leitor.nextLine();

        AlunoDTO aluno = alunoDao.buscarAluno(new AlunoDTO());
        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.print("Digite o novo nome do aluno (ou deixe em branco para manter): ");
        String novoNome = leitor.nextLine();
        if (!novoNome.isBlank()) {
            aluno.setNome(novoNome);
        }

        System.out.print("Digite o novo email do aluno (ou deixe em branco para manter): ");
        String novoEmail = leitor.nextLine();
        if (!novoEmail.isBlank()) {
            aluno.setEmail(novoEmail);
        }

        alunoDao.editarAluno(aluno);
        System.out.println("Aluno atualizado com sucesso!");
    }
}