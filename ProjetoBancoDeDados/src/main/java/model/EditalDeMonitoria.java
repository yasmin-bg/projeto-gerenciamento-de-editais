package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class EditalDeMonitoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String numero;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFinal;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    
    private List<Disciplina> disciplinas = new ArrayList<>();
	
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

	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;

	}
	
	public boolean jaAcabou() {
		return LocalDateTime.now().isAfter(dataFinal);
	}
	
	public boolean jaComecou() {
		return LocalDateTime.now().isAfter(dataInicio);
	}
	
	public boolean inscrever(Aluno aluno, String nomeDisciplina) {
		if (!jaAcabou() && jaComecou()) {
			for (Disciplina disciplina: disciplinas) {
				if (disciplina.getNome().equalsIgnoreCase(nomeDisciplina)) {
					disciplina.getListaDeAlunosInscritos().add(aluno);
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
		for (Disciplina disciplina: disciplinas) {
			infoVagas += disciplina.getNome()+" - "+disciplina.getQuantidadeDeVagas()+" vagas\n";
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