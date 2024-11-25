package controller;

import java.util.ArrayList;
import java.util.List;

import Exception.AlunoJaCadastradoException;
import Exception.AlunoNaoCadastradoException;
import Exception.ListaDeAlunosVaziaException;
import dao.AlunoDAOJPA;
import dto.AlunoDTO;

public class AlunoController {

    private final AlunoDAOJPA alunoDao;

    public AlunoController() {
        this.alunoDao = new AlunoDAOJPA();
    }
    
    public void salvarAluno(AlunoDTO aluno) {
    	try {
			alunoDao.cadastrarAluno(aluno);
		} catch (AlunoJaCadastradoException e) {
			System.out.println("O aluno da matrícula: " + aluno.getMatricula()+" já está cadastrado.");
		}
    	
    }
    public void editarAluno(AlunoDTO aluno) {
    	try {
			alunoDao.editarAluno(aluno);
		} catch (AlunoNaoCadastradoException e) {
			System.out.println("O aluno não está cadastrado.");
		} catch (Exception e) {
			System.out.print("Erro ao alterar informações.");
		}
    }
    public AlunoDTO buscarAlunoPorMatricula(AlunoDTO aluno) {
    	AlunoDTO alunoEncontrado = null;
    	try {
			alunoEncontrado = alunoDao.buscarAluno(aluno);
		} catch (AlunoNaoCadastradoException e) {
			System.out.println("Aluno não cadastrado.");
		} catch (Exception e) {
			System.out.print("Erro ao carregar informações.");
		}
		return alunoEncontrado;
    	
    }
    public List<AlunoDTO> listarAlunos() {
        try {
            return alunoDao.listarTodosOsAlunos();
        } catch (ListaDeAlunosVaziaException e) {
            System.out.println("Não há alunos cadastrados no momento.");
            return new ArrayList<>(); // Retorna uma lista vazia
        } catch (Exception e) {
            System.out.println("Erro ao carregar informações: " + e.getMessage());
            return new ArrayList<>(); // Retorna uma lista vazia em caso de erro genérico
        }
    }
    
}

