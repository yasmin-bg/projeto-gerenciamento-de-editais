package dao;

import model.EditalDeMonitoria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class EditalDeMonitoriaDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("editais-monitoria");

    public EditalDeMonitoria salvar(EditalDeMonitoria edital) {
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

    public EditalDeMonitoria buscarPorId(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(EditalDeMonitoria.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar edital: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List<EditalDeMonitoria> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<EditalDeMonitoria> query = em.createQuery(
                "SELECT e FROM EditalDeMonitoria e", EditalDeMonitoria.class
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
            EditalDeMonitoria edital = em.find(EditalDeMonitoria.class, id);
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