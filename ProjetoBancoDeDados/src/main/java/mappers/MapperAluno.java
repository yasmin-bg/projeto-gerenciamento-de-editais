package mappers;

import dto.AlunoDTO;
import model.Aluno;

public class MapperAluno {
	
	public Aluno fromDTO(AlunoDTO dto) {
		return new Aluno(
				dto.getNome(),
				dto.getMatricula(),
				dto.getEmail(),
				dto.getSenha()
				);
	}
	
	public AlunoDTO toDTO(Aluno aluno) {
		return new AlunoDTO(
				aluno.getNome(),
				aluno.getMatricula(),
				aluno.getEmail(),
				aluno.getSenha()
				);
	}

}
