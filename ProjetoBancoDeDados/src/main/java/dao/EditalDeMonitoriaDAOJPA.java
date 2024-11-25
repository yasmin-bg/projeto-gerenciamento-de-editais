package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dto.DisciplinaDTO;
import dto.EditalDeMonitoriaDTO;
import exception.ListaDeEditaisVaziaException;
import mappers.MapperEditalDeMonitoria;
import model.EditalDeMonitoria;

public class EditalDeMonitoriaDAOJPA implements EditalDeMonitoriaDAO {

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
            
            if(edital != null) {
            	MapperEditalDeMonitoria mapper = new MapperEditalDeMonitoria();
            	edital = mapper.fromDTO(dto);
            	em.merge(edital);
            	
            	for(DisciplinaDTO disciplina: dto.getDisciplinas()) {
            		DisciplinaDAOJPA daoDisciplina = new DisciplinaDAOJPA();
            		daoDisciplina.atualizar(disciplina);
            	}
            }	
            
    	} catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao atualizar o edital: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public EditalDeMonitoriaDTO buscarPorId(EditalDeMonitoriaDTO dto) throws RuntimeException{
        EntityManager em = emf.createEntityManager();
        try {
        	EditalDeMonitoria edital = em.find(EditalDeMonitoria.class, dto.getId());
        	if(edital == null) {
        		//throw new EditalNaoEncontradoException();
        		return null;
        	}
        	MapperEditalDeMonitoria mapper = new MapperEditalDeMonitoria();
            return mapper.toDTO(edital);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar edital: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List<EditalDeMonitoriaDTO> listarTodos() throws ListaDeEditaisVaziaException, RuntimeException{
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<EditalDeMonitoria> query = em.createQuery(
                "SELECT e FROM EditalDeMonitoria e", EditalDeMonitoria.class
            );
            
            if(query.getResultList().size() == 0) {
            	throw new ListaDeEditaisVaziaException();
            }else {
            	
            	MapperEditalDeMonitoria mapper = new MapperEditalDeMonitoria();
            	List<EditalDeMonitoriaDTO> lista = new ArrayList<>();
            	for(EditalDeMonitoria edital : query.getResultList()) {
            		lista.add(mapper.toDTO(edital));
            	}
            	return lista;
            }
            
            
      
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar editais: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public void excluir(EditalDeMonitoriaDTO dto) throws RuntimeException{
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            EditalDeMonitoria edital = em.find(EditalDeMonitoria.class, dto.getId());
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