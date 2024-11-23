package dto;

import java.util.ArrayList;

public class DisciplinaDTO {
	
	private long id;
	private String nome;
	private int quantidadeDeVagas;
	private ArrayList<AlunoDTO> listaDeAlunosInscritos = new ArrayList<AlunoDTO>();

	public DisciplinaDTO(String nome, int quantidadeDeVagas, ArrayList<AlunoDTO> listaDeAlunosInscritos) {
		this.nome = nome;
		this.quantidadeDeVagas = quantidadeDeVagas;
		this.listaDeAlunosInscritos = listaDeAlunosInscritos;
	}
	
	public DisciplinaDTO() {
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
	
	public ArrayList<AlunoDTO> getListaDeAlunosInscritos() {
		return listaDeAlunosInscritos;
	}
	public void setListaDeAlunosInscritos(ArrayList<AlunoDTO> listaDeAlunosInscritos) {
		this.listaDeAlunosInscritos = listaDeAlunosInscritos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
