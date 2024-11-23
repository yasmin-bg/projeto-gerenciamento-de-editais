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

public class AlunoDao implements IAlunoDao {
    private EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("Alunos");
    private MapperAluno conversor;

	public AlunoDao() {
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
	public void excluirAluno(AlunoDTO dto) throws AlunoNaoCadastradoException{
	    EntityManager entityManager = entityFactory.createEntityManager();
	    try {
	        entityManager.getTransaction().begin();
	        Aluno alunoExiste = entityManager.find(Aluno.class, dto.getMatricula());
	        alunoExiste = conversor.fromDTO(dto);
	        if (alunoExiste == null) {
	        	throw new AlunoNaoCadastradoException();
	        }
        	entityManager.remove(alunoExiste);
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