package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EditalDeMonitoria {
	
	private long id;
	private String numero;

	private LocalDateTime dataInicio;
	private LocalDateTime dataFinal;
	private ArrayList<Vaga> vagas = new ArrayList<Vaga>();
	
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

	public ArrayList<Vaga> getVagas() {
		return vagas;
	}
	public void setVagas(ArrayList<Vaga> vagas) {
		this.vagas = vagas;

	}
	
	public boolean jaAcabou() {
		return LocalDateTime.now().isAfter(dataFinal);
	}
	
	public boolean jaComecou() {
		return LocalDateTime.now().isAfter(dataInicio);
	}
	
	public boolean inscrever(Aluno aluno, String disciplina) {
		if (!jaAcabou() && jaComecou()) {
			for (Vaga vaga: vagas) {
				if (vaga.getDisciplina().equalsIgnoreCase(disciplina)) {
					vaga.getListaDeAlunosInscritos().add(aluno);
					return true;
				}
			}
		}
		return false;
	}
	
	public EditalDeMonitoria(String numero, LocalDateTime dataInicio, LocalDateTime dataFinal) {
		this.id = System.currentTimeMillis();
		this.numero = numero;
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
	}
	
	public EditalDeMonitoria() {
		this.id = System.currentTimeMillis();
	}
	
	public String toString() {
		
		String infoVagas = "";
		for (Vaga vaga: vagas) {
			infoVagas += vaga.getDisciplina()+" - "+vaga.getQuantidadeDeVagas()+" vagas\n";
		}
		
		String status;
		if(jaAcabou() || !jaComecou()) {
			status = "encerradas.";
		}else {
			status = "abertas.";
		}
		
		String infoEdital = "Edital de Monitoria "+this.numero+"\nVagas \n"+infoVagas+"Inscrições "+status;
		return infoEdital;
	}
}