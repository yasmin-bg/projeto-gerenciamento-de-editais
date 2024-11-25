package dto;

public class AlunoDTO {
	private String nome;
	private String matricula;
	private String email;
	
	public AlunoDTO(String nome, String matricula, String email) {
	        this.nome = nome;
	        this.matricula = matricula;
	        this.email = email;
	}
	
	public AlunoDTO() {
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

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}