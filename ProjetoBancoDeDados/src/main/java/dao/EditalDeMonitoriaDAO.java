package dao;

import dto.EditalDeMonitoriaDTO;
import mappers.MapperEditalDeMonitoria;
import model.EditalDeMonitoria;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class EditalDeMonitoriaDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("editais-monitoria");

    public void salvar(EditalDeMonitoriaDTO dto) throws RuntimeException{
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
                 
            EditalDeMonitoria edital = em.find(EditalDeMonitoria.class, dto.getId());
            
            if(edital == null) {  
            	MapperEditalDeMonitoria mapper = new MapperEditalDeMonitoria();
            	em.persist(mapper.fromDTO(dto));
            }
            
            em.getTransaction().commit();
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar o edital: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
    
    public void atualizar(EditalDeMonitoriaDTO dto) {
    	EntityManager em = emf.createEntityManager();
    	
    	try {
    		em.getTransaction().begin();
            
            EditalDeMonitoria edital = em.find(EditalDeMonitoria.class, dto.getId());
            MapperEditalDeMonitoria mapper = new MapperEditalDeMonitoria();
            
            if(!edital.equals(null)) {
            	em.merge(mapper.fromDTO(dto));
            }	
            
    	} catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao atualizar o edital: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public EditalDeMonitoriaDTO buscarPorId(EditalDeMonitoriaDTO dto) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(EditalDeMonitoriaDTO.class, dto.getId());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar edital: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List<EditalDeMonitoriaDTO> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<EditalDeMonitoria> query = em.createQuery(
                "SELECT e FROM EditalDeMonitoria e", EditalDeMonitoria.class
            );
            
            MapperEditalDeMonitoria mapper = new MapperEditalDeMonitoria();
            List<EditalDeMonitoriaDTO> lista = null;
            for(EditalDeMonitoria edital : query.getResultList()) {
            	lista.add(mapper.toDTO(edital));
            }
            
            return lista;
      
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar editais: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public void excluir(EditalDeMonitoriaDTO dto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            EditalDeMonitoriaDTO edital = em.find(EditalDeMonitoriaDTO.class, dto.getId());
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