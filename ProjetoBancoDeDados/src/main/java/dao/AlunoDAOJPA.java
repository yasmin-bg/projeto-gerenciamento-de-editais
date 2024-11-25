package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Exception.AlunoJaCadastradoException;
import Exception.AlunoNaoCadastradoException;
import Exception.ListaDeAlunosVaziaException;
import dto.AlunoDTO;
import mappers.MapperAluno;
import model.Aluno;

public class AlunoDAOJPA implements AlunoDAO {
    private EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("editais-monitoria");
    private MapperAluno conversor;

	public AlunoDAOJPA() {
		this.conversor= new MapperAluno();
	}
	
	public void fecharFactory() {
        if (entityFactory.isOpen()) {
        	entityFactory.close();
        }
	}
	@Override
	public void cadastrarAluno(AlunoDTO dto) throws AlunoJaCadastradoException {
	    EntityManager entityManager = entityFactory.createEntityManager();
	    
	    try {
	        entityManager.getTransaction().begin();
	        Aluno aluno = entityManager.find(Aluno.class, dto.getMatricula());
	        Aluno novoAluno = conversor.fromDTO(dto);
	        if (aluno != null) {
                throw new AlunoJaCadastradoException();
	        }
	        entityManager.persist(novoAluno);
	        entityManager.getTransaction().commit();
	    } catch (Exception e) {
	        entityManager.getTransaction().rollback();
	        throw e;
	    } finally {
	        entityManager.close();
	    }
	}
	@Override
	public void editarAluno(AlunoDTO dto) throws Exception,AlunoNaoCadastradoException {
	    EntityManager entityManager = entityFactory.createEntityManager();
	    try {
	        entityManager.getTransaction().begin();
	        Aluno alunoExiste = entityManager.find(Aluno.class, dto.getMatricula());
	        if (alunoExiste == null) {
	        	throw new AlunoNaoCadastradoException();
	        }
	        alunoExiste = conversor.fromDTO(dto);
	        entityManager.merge(alunoExiste);
	    }catch(Exception e) {
	    	entityManager.getTransaction().rollback();
	    	throw e;
	    }finally {
	    	entityManager.close();
	    }
	}
	@Override
	public AlunoDTO buscarAluno(AlunoDTO dto)throws AlunoNaoCadastradoException,Exception {
	    EntityManager entityManager = entityFactory.createEntityManager();
	    try {
	        entityManager.getTransaction().begin();
	        Aluno alunoExiste = entityManager.find(Aluno.class, dto.getMatricula());
	        if (alunoExiste == null) {
	        	throw new AlunoNaoCadastradoException();
	        }
	        AlunoDTO outro = conversor.toDTO(alunoExiste);
	        return outro;
	    }catch(Exception e) {
	    	entityManager.getTransaction().rollback();
	    	throw e;
	    }finally {
	    	entityManager.close();
	    }
	 }
	@Override
	public List<AlunoDTO> listarTodosOsAlunos() throws ListaDeAlunosVaziaException,Exception {
	    EntityManager entityManager = entityFactory.createEntityManager();
	    try {
            TypedQuery<Aluno> alunos = entityManager.createQuery("SELECT e FROM Aluno e", Aluno.class);
            List<AlunoDTO> alunosDTO = new ArrayList<>();
            if(alunosDTO.size()== 0) {
            	throw new AlunoJaCadastradoException();
            }
            for(Aluno aluno: alunos.getResultList()) {
            	alunosDTO.add(conversor.toDTO(aluno));	
            }
            return alunosDTO;
        } catch (Exception e) {
	    	entityManager.getTransaction().rollback();
	    	throw e;
        } finally {
        	entityManager.close();
        }
  	}
}
	