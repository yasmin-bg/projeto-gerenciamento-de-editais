package controller;

import dao.EditalDeMonitoriaDAO;
import dto.AlunoDTO;
import dto.DisciplinaDTO;
import dto.EditalDeMonitoriaDTO;
import mappers.MapperEditalDeMonitoria;
import model.EditalDeMonitoria;

import java.time.LocalDateTime;
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
    
    public boolean inscrever(EditalDeMonitoriaDTO editalDTO, AlunoDTO alunoDTO, DisciplinaDTO disciplinaDTO) {
        MapperEditalDeMonitoria mapper = new MapperEditalDeMonitoria();

        EditalDeMonitoria edital = mapper.fromDTO(editalDTO);

        boolean inscrito = edital.inscrever(alunoDTO, disciplinaDTO);

        if (inscrito) {
            EditalDeMonitoriaDAO dao = new EditalDeMonitoriaDAO();
            dao.atualizar(mapper.toDTO(edital));
        }

        return inscrito;
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