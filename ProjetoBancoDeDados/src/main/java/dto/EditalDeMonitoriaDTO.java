package dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EditalDeMonitoriaDTO {
	
	private long id;
	private String numero;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFinal;
	private ArrayList<DisciplinaDTO> disciplinas = new ArrayList<DisciplinaDTO>();
	
	public EditalDeMonitoriaDTO() {
	}
	
	public EditalDeMonitoriaDTO(long id, String numero, LocalDateTime dataInicio, LocalDateTime dataFinal,
			ArrayList<DisciplinaDTO> disciplinas) {
		this.id = id;
		this.numero = numero;
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
		this.disciplinas = disciplinas;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public LocalDateTime getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public LocalDateTime getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(LocalDateTime dataFinal) {
		this.dataFinal = dataFinal;
	}

	public ArrayList<DisciplinaDTO> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(ArrayList<DisciplinaDTO> disciplinas) {
		this.disciplinas = disciplinas;

	}
}