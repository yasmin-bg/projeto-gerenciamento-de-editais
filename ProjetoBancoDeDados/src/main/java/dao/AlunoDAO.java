package dao;

import java.util.List;

import dto.AlunoDTO;
import exception.AlunoJaCadastradoException;
import exception.AlunoNaoCadastradoException;
import exception.ListaDeAlunosVaziaException;
import model.Aluno;

public interface AlunoDAO {
	public void cadastrarAluno(AlunoDTO aluno) throws AlunoJaCadastradoException;
	public void editarAluno(AlunoDTO aluno) throws Exception, AlunoNaoCadastradoException;
	public AlunoDTO buscarAluno(AlunoDTO dto) throws AlunoNaoCadastradoException,Exception;
	public List<AlunoDTO> listarTodosOsAlunos() throws ListaDeAlunosVaziaException, Exception;

}