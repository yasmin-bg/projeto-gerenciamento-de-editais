package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(name = "quantidade_vagas", nullable = false)
	private int quantidadeDeVagas;
	
	@ManyToMany(cascade = CascadeType.ALL, targetEntity = model.Aluno.class)
	@JoinTable(name = "inscricoes",
				joinColumns = @JoinColumn(name = "disciplina"),
				inverseJoinColumns = @JoinColumn(name = "aluno"))
	private List<Aluno> listaDeAlunosInscritos = new ArrayList<>();

	public Disciplina() {
	}

	public Disciplina(String nome, int quantidadeDeVagas, List<Aluno> listaDeAlunosInscritos) {
		this.nome = nome;
		this.quantidadeDeVagas = quantidadeDeVagas;
		this.listaDeAlunosInscritos = listaDeAlunosInscritos;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getQuantidadeDeVagas() {
		return quantidadeDeVagas;
	}
	public void setQuantidadeDeVagas(int quantidadeDeVagas) {
		this.quantidadeDeVagas = quantidadeDeVagas;
	}
	
	public List<Aluno> getListaDeAlunosInscritos() {
		return listaDeAlunosInscritos;
	}
	public void setListaDeAlunosInscritos(List<Aluno> listaDeAlunosInscritos) {
		this.listaDeAlunosInscritos = listaDeAlunosInscritos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
