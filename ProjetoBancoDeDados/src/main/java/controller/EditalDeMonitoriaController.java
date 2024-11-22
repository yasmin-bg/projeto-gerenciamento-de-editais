package controller;

import dao.EditalDeMonitoriaDAO;
import dto.EditalDeMonitoriaDTO;
import java.util.List;

public class EditalDeMonitoriaController {

    private final EditalDeMonitoriaDAO editalDeMonitoriaDAO;

    public EditalDeMonitoriaController() {
        this.editalDeMonitoriaDAO = new EditalDeMonitoriaDAO();
    }

    public EditalDeMonitoriaDTO criarEdital(EditalDeMonitoriaDTO edital) {
        if (edital.getDataInicio().isAfter(edital.getDataFinal())) {
            return null;
        }
        return editalDeMonitoriaDAO.salvar(edital);
    }

    public EditalDeMonitoriaDTO buscarEditalPorId(long id) {
        EditalDeMonitoriaDTO edital = editalDeMonitoriaDAO.buscarPorId(id);
        return edital;
    }

    public List<EditalDeMonitoriaDTO> listarTodosEditais() {
        return editalDeMonitoriaDAO.listarTodos();
    }

    public EditalDeMonitoriaDTO atualizarEdital(EditalDeMonitoriaDTO edital) {
        if (edital.getId() == 0 || edital.getDataInicio().isAfter(edital.getDataFinal())) {
            return null; 
        }
        return editalDeMonitoriaDAO.salvar(edital);
    }

    public boolean deletarEdital(long id) {
        EditalDeMonitoriaDTO edital = buscarEditalPorId(id);
        if (edital != null) {
            editalDeMonitoriaDAO.excluir(id);
            return true;
        }
        return false; 
    }

    public void fecharRecursos() {
        editalDeMonitoriaDAO.fecharFactory();
    }
}