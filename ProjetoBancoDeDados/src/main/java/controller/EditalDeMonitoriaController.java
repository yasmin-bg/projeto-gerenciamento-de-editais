package controller;

import dao.EditalDeMonitoriaDAO;
import dto.AlunoDTO;
import dto.DisciplinaDTO;
import dto.EditalDeMonitoriaDTO;
import exception.ListaDeEditaisVaziaException;
import mappers.MapperEditalDeMonitoria;
import model.EditalDeMonitoria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EditalDeMonitoriaController {

    private final EditalDeMonitoriaDAO editalDeMonitoriaDAO;

    public EditalDeMonitoriaController() {
        this.editalDeMonitoriaDAO = new EditalDeMonitoriaDAO();
    }
    
    public boolean criarEdital(EditalDeMonitoriaDTO edital) {
    	if (!edital.getDataInicio().isAfter(edital.getDataFinal())) {
    		try {
				editalDeMonitoriaDAO.salvar(edital);
				return true;
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} 		
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
        EditalDeMonitoriaDTO edital;
		try {
			edital = editalDeMonitoriaDAO.buscarPorId(dto);
			return edital;
		} catch (Exception e) {
			System.out.println("Erro ao buscar edital");
			return null;
		}
    }

    public List<EditalDeMonitoriaDTO> listarTodosEditais() {
        try {
			return editalDeMonitoriaDAO.listarTodos();
		} catch (ListaDeEditaisVaziaException e) {
			System.out.print("Não há editais cadastrados");
			return new ArrayList<>();
		} catch (RuntimeException e) {
			System.out.print("Erro ao listar editais");
			return new ArrayList<>();
		}
    }

    public boolean atualizarEdital(EditalDeMonitoriaDTO edital) {
        if (edital.getId() != 0 || !edital.getDataInicio().isAfter(edital.getDataFinal())) {
        	editalDeMonitoriaDAO.atualizar(edital);
            return true;
        }
        
        return false;        
    }

    public boolean deletarEdital(EditalDeMonitoriaDTO dto) {
        try {
			editalDeMonitoriaDAO.excluir(dto);
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
         
    }
    
    public void detalharEdital(EditalDeMonitoriaDTO dto) {
    	dto = buscarEditalPorId(dto);
    	if(dto != null) {
    		MapperEditalDeMonitoria mapper = new MapperEditalDeMonitoria();
    		System.out.println(mapper.fromDTO(dto));	
    	}
    }

    public void fecharRecursos() {
        editalDeMonitoriaDAO.fecharFactory();
    }
}