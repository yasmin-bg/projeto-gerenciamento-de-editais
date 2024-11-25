package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dto.DisciplinaDTO;
import mappers.MapperDisciplina;
import model.Disciplina;

public class DisciplinaDAOJPA implements DisciplinaDAO{
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("editais-monitoria");

    public void salvar(DisciplinaDTO dto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Disciplina disciplina = em.find(Disciplina.class, dto.getId());
            if (disciplina == null) {
                em.persist(disciplina);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar disciplina: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public DisciplinaDTO buscarPorId(DisciplinaDTO dto) {
        EntityManager em = emf.createEntityManager();
        try {
        	MapperDisciplina mapper = new MapperDisciplina();
            return mapper.toDTO(em.find(Disciplina.class, dto.getId()));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar disciplina: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
    
    public void atualizar(DisciplinaDTO dto) {
    	EntityManager entityManager = emf.createEntityManager();
    	    try {
    	        entityManager.getTransaction().begin();
    	        Disciplina disciplina = entityManager.find(Disciplina.class, dto.getId());
    	        if (disciplina != null) {
    	        	MapperDisciplina mapper = new MapperDisciplina();
    	        	disciplina = mapper.fromDTO(dto);
    	        	entityManager.merge(disciplina);        	
    	        }
    	        
    	    }catch(Exception e) {
    	    	entityManager.getTransaction().rollback();
    	    	throw e;
    	    }finally {
    	    	entityManager.close();
    	    }
    	
    }

    public void excluir(DisciplinaDTO dto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Disciplina disciplina = em.find(Disciplina.class, dto.getId());
            if (disciplina != null) {
                em.remove(disciplina);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao excluir disciplina: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public void fecharFactory() {
        if (emf.isOpen()) {
            emf.close();
        }
    }

}
