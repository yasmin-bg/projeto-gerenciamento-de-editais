package dto;

import java.util.ArrayList;

import model.Aluno;

public class DiscilplinaDTO {
	
	private String nome;
	private int quantidadeDeVagas;
	private ArrayList<Aluno> listaDeAlunosInscritos = new ArrayList<Aluno>();

	public String getDisciplina() {
		return nome;
	}
	public void setDisciplina(String nome) {
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
