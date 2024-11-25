package dao;

import java.util.List;
import dto.EditalDeMonitoriaDTO;
import exception.ListaDeEditaisVaziaException;

public interface EditalDeMonitoriaDAO {
	
	public void salvar(EditalDeMonitoriaDTO dto) throws RuntimeException;
	public void atualizar(EditalDeMonitoriaDTO dto);
	public EditalDeMonitoriaDTO buscarPorId(EditalDeMonitoriaDTO dto) throws RuntimeException;
	public List<EditalDeMonitoriaDTO> listarTodos() throws ListaDeEditaisVaziaException, RuntimeException;
	public void excluir(EditalDeMonitoriaDTO dto) throws RuntimeException;
}