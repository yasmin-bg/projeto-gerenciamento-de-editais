package model;

import javax.persistence.*;
import dto.AlunoDTO;
import dto.DisciplinaDTO;
import mappers.MapperAluno;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EDITAL_DE_MONITORIA")
public class EditalDeMonitoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable=false,unique = true)
    private String numero;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFinal;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Disciplina> disciplinas = new ArrayList<>();
    
    public EditalDeMonitoria() {
    	
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

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public boolean jaAcabou() {
        return LocalDateTime.now().isAfter(dataFinal);
    }

    public boolean jaComecou() {
        return LocalDateTime.now().isAfter(dataInicio);
    }

    public boolean inscrever(AlunoDTO aluno, DisciplinaDTO disciplinaDTO) {
        if (!jaAcabou() && jaComecou()) {
            for (Disciplina disciplina : disciplinas) {
                if (disciplina.getNome().equals(disciplinaDTO.getNome())) {
                	MapperAluno conversor = new MapperAluno();
                    disciplina.getListaDeAlunosInscritos().add(conversor.fromDTO(aluno));
                    return true;
                }
            }
        }
        return false;
    }

    public EditalDeMonitoria(String numero, LocalDateTime dataInicio, LocalDateTime dataFinal) {
        this.numero = numero;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
    }

    @Override
    public String toString() {
        StringBuilder infoVagas = new StringBuilder();
        for (Disciplina disciplina : disciplinas) {
            infoVagas.append(disciplina.getNome()).append(" - ")
                     .append(disciplina.getQuantidadeDeVagas()).append(" vagas\n");
        }

        String status = (jaAcabou() || !jaComecou()) ? "encerradas." : "abertas.";
        return "Edital de Monitoria " + this.numero + "\nVagas \n" + infoVagas + "Inscrições " + status;
    }
}