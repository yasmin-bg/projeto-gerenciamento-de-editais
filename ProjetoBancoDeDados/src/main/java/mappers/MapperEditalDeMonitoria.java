package mappers;

import java.util.ArrayList;
import java.util.List;

import dto.DisciplinaDTO;
import dto.EditalDeMonitoriaDTO;
import model.Disciplina;
import model.EditalDeMonitoria;

public class MapperEditalDeMonitoria {
	
	public EditalDeMonitoria fromDTO(EditalDeMonitoriaDTO dto) {
		EditalDeMonitoria edital = new EditalDeMonitoria();
		edital.setId(dto.getId());
		edital.setDataInicio(dto.getDataInicio());
		edital.setDataFinal(dto.getDataFinal());
		edital.setNumero(dto.getNumero());
		
		MapperDisciplina mapperDisciplina = new MapperDisciplina();
		List<Disciplina> lista = new ArrayList<>();
		
		//Percorre todas as DisciplinaDTO do EditalDTO
		for(DisciplinaDTO disciplinaDTO: dto.getDisciplinas()) {
			
			//Transforma cada DisciplinaDTO em objeto Disciplina e adiciona na lista de disciplinas
			lista.add(mapperDisciplina.fromDTO(disciplinaDTO));
		}
		
		edital.setDisciplinas(lista);
		
		return edital;
	}
	
	public EditalDeMonitoriaDTO toDTO(EditalDeMonitoria edital) {
		EditalDeMonitoriaDTO dto = new EditalDeMonitoriaDTO();
		dto.setId(edital.getId());
		dto.setDataInicio(edital.getDataInicio());
		dto.setDataFinal(edital.getDataFinal());
		dto.setNumero(edital.getNumero());
		
		MapperDisciplina mapperDisciplina = new MapperDisciplina();
		
		//Percorre todas as disciplinas do edital
		for(Disciplina disciplina: edital.getDisciplinas()) {
			
			//Transforma cada disciplina em DisciplinaDTO e adiciona na lista de disciplinas do EditalDTO
			dto.getDisciplinas().add(mapperDisciplina.toDTO(disciplina));
		}
		
		return dto;
	}
}
