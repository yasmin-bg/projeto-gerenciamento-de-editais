package dao;

import java.util.List;

import Exception.AlunoJaCadastradoException;
import Exception.AlunoNaoCadastradoException;
import dto.AlunoDTO;
import model.Aluno;

public interface IAlunoDao {
	public void cadastrarAluno(AlunoDTO aluno) throws AlunoJaCadastradoException;
	public void excluirAluno(AlunoDTO aluno) throws AlunoNaoCadastradoException;
	public void editarAluno(AlunoDTO aluno) throws Exception, AlunoNaoCadastradoException;
	public AlunoDTO buscarAluno(AlunoDTO dto) throws AlunoNaoCadastradoException,Exception;
	public List<AlunoDTO> listarTodosOsAlunos() throws ListaDeAlunosVaziaException, Exception;

}
