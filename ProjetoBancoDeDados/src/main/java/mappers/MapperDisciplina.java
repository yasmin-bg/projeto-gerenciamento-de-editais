package mappers;

import dto.AlunoDTO;
import dto.DisciplinaDTO;
import model.Aluno;
import model.Disciplina;

public class MapperDisciplina {
	
	public Disciplina fromDTO(DisciplinaDTO dto) {
		Disciplina disciplina = new Disciplina();
		disciplina.setNome(dto.getNome());
		disciplina.setQuantidadeDeVagas(dto.getQuantidadeDeVagas());
		
		MapperAluno mapperAluno = new MapperAluno();
		
		//Percorre todos os AlunoDTO da lista de inscritos da DisciplinaDTO
		for(AlunoDTO alunoDTO: dto.getListaDeAlunosInscritos()) {
			
			//Transforma cada AlunoDTO em objeto Aluno e adiciona na lista de inscritos da disciplina
			disciplina.getListaDeAlunosInscritos().add(mapperAluno.fromDTO(alunoDTO));
		}
		
		return disciplina;
	}
	
	public DisciplinaDTO toDTO(Disciplina disciplina) {
		DisciplinaDTO dto = new DisciplinaDTO();
		dto.setNome(disciplina.getNome());
		dto.setQuantidadeDeVagas(disciplina.getQuantidadeDeVagas());
		
		MapperAluno mapperAluno = new MapperAluno();
		
		//Percorre todos os alunos da lista de inscritos da disciplina
		for(Aluno aluno: disciplina.getListaDeAlunosInscritos()) {
			
			//Transforma cada aluno em AlunoDTO e adiciona na lista de inscritos da DisciplinaDTO
			dto.getListaDeAlunosInscritos().add(mapperAluno.toDTO(aluno));
		}
		
		return dto;
	}
}
