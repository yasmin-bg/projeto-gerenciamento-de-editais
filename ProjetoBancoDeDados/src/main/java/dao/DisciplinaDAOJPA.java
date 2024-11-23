package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
