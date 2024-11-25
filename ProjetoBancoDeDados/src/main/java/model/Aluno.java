package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "ALUNOS_CADASTRADOS")

public class Aluno {
	@Column(nullable=false)
	private String nome;
	
	@Id
	@Column(nullable=false,unique = true)
	private String matricula;
	
	@Column(nullable=false)
	private String email;
	
	public Aluno(String nome, String matricula, String email) {
	        this.nome = nome;
	        this.matricula = matricula;
	        this.email = email;
	}
	public Aluno() {
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
