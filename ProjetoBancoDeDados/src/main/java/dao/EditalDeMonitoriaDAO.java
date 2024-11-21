package dao;

import dto.EditalDeMonitoriaDTO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class EditalDeMonitoriaDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("editais-monitoria");

    public EditalDeMonitoriaDTO salvar(EditalDeMonitoriaDTO edital) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (edital.getId() == 0) {
                em.persist(edital);
            } else {
                em.merge(edital);
            }
            em.getTransaction().commit();
            return edital;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar edital: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public EditalDeMonitoriaDTO buscarPorId(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(EditalDeMonitoriaDTO.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar edital: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List<EditalDeMonitoriaDTO> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<EditalDeMonitoriaDTO> query = em.createQuery(
                "SELECT e FROM EditalDeMonitoria e", EditalDeMonitoriaDTO.class
            );
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar editais: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public void excluir(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            EditalDeMonitoriaDTO edital = em.find(EditalDeMonitoriaDTO.class, id);
            if (edital != null) {
                em.remove(edital);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao excluir edital: " + e.getMessage(), e);
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