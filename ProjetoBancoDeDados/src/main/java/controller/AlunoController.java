package controller;

import java.util.ArrayList;
import java.util.List;

import Exception.AlunoJaCadastradoException;
import Exception.AlunoNaoCadastradoException;
import Exception.ListaDeAlunosVaziaException;
import dao.AlunoDao;
import dto.AlunoDTO;
import mappers.MapperAluno;

public class AlunoController {

    private final AlunoDao alunoDao;
    private MapperAluno conversor;

    public AlunoController(MapperAluno conversor) {
        this.alunoDao = new AlunoDao();
        this.conversor= conversor;
    }
    
    public void salvarAluno(AlunoDTO aluno) {
    	try {
			alunoDao.cadastrarAluno(aluno);
			aluno.getMatricula();
		} catch (AlunoJaCadastradoException e) {
			System.out.println("O aluno da Matricula:" + aluno.getMatricula()+"já está cadastrado.");
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
    	try {
			alunoDao.buscarAluno(aluno);
		} catch (AlunoNaoCadastradoException e) {
			System.out.println("Aluno não cadastrado.");
		} catch (Exception e) {
			System.out.print("Erro ao carregar informações.");
		}
		return aluno;
    	
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

