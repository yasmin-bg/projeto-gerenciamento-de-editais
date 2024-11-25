package controller;

import java.util.ArrayList;
import java.util.List;

import dao.DisciplinaDAOJPA;
import dao.EditalDeMonitoriaDAO;
import dto.AlunoDTO;
import dto.DisciplinaDTO;
import dto.EditalDeMonitoriaDTO;
import exception.ListaDeEditaisVaziaException;
import mappers.MapperEditalDeMonitoria;
import model.EditalDeMonitoria;

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
            this.editalDeMonitoriaDAO.atualizar(editalDTO);
            DisciplinaDAOJPA daoDisciplina = new DisciplinaDAOJPA();
            for(DisciplinaDTO dto: editalDTO.getDisciplinas()) {
            	daoDisciplina.atualizar(disciplinaDTO);
            	
            }
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
        if (!edital.getDataInicio().isAfter(edital.getDataFinal())) {
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
    	}else {
    		System.out.println("Erro ao detalhar edital");	

    	}
    }

    public void fecharRecursos() {
        editalDeMonitoriaDAO.fecharFactory();
    }
}