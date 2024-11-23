package controller;

import dao.EditalDeMonitoriaDAO;
import dto.EditalDeMonitoriaDTO;
import java.util.List;

public class EditalDeMonitoriaController {

    private final EditalDeMonitoriaDAO editalDeMonitoriaDAO;

    public EditalDeMonitoriaController() {
        this.editalDeMonitoriaDAO = new EditalDeMonitoriaDAO();
    }

    public boolean criarEdital(EditalDeMonitoriaDTO edital) {
        if (!edital.getDataInicio().isAfter(edital.getDataFinal())) {
        	editalDeMonitoriaDAO.salvar(edital);
        	return true;
            
        }
        
        return false;  
    }

    public EditalDeMonitoriaDTO buscarEditalPorId(EditalDeMonitoriaDTO dto) {
        EditalDeMonitoriaDTO edital = editalDeMonitoriaDAO.buscarPorId(dto);
        return edital;
    }

    public List<EditalDeMonitoriaDTO> listarTodosEditais() {
        return editalDeMonitoriaDAO.listarTodos();
    }

    public boolean atualizarEdital(EditalDeMonitoriaDTO edital) {
        if (edital.getId() != 0 || !edital.getDataInicio().isAfter(edital.getDataFinal())) {
        	editalDeMonitoriaDAO.atualizar(edital);
            return true;
        }
        
        return false;        
    }

    public boolean deletarEdital(EditalDeMonitoriaDTO dto) {
        EditalDeMonitoriaDTO edital = buscarEditalPorId(dto);
        if (edital != null) {
            editalDeMonitoriaDAO.excluir(dto);
            return true;
        }
        
        return false; 
    }

    public void fecharRecursos() {
        editalDeMonitoriaDAO.fecharFactory();
    }
}