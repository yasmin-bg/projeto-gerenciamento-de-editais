package model;

import java.util.ArrayList;

public class Disciplina {
	
	private String nome;
	private int quantidadeDeVagas;
	private ArrayList<Aluno> listaDeAlunosInscritos = new ArrayList<Aluno>();

	public Disciplina() {
	}

	public Disciplina(String nome, int quantidadeDeVagas, ArrayList<Aluno> listaDeAlunosInscritos) {
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
	
	public ArrayList<Aluno> getListaDeAlunosInscritos() {
		return listaDeAlunosInscritos;
	}
	public void setListaDeAlunosInscritos(ArrayList<Aluno> listaDeAlunosInscritos) {
		this.listaDeAlunosInscritos = listaDeAlunosInscritos;
	}

}
