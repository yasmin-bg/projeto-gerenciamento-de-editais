package dao;

import java.util.List;

import Exception.AlunoJaCadastradoException;
import Exception.AlunoNaoCadastradoException;
import Exception.ListaDeAlunosVaziaException;
import dto.AlunoDTO;
import model.Aluno;

public interface AlunoDAO {
	public void cadastrarAluno(AlunoDTO aluno) throws AlunoJaCadastradoException;
	public void editarAluno(AlunoDTO aluno) throws Exception, AlunoNaoCadastradoException;
	public AlunoDTO buscarAluno(AlunoDTO dto) throws AlunoNaoCadastradoException,Exception;
	public List<AlunoDTO> listarTodosOsAlunos() throws ListaDeAlunosVaziaException, Exception;

}