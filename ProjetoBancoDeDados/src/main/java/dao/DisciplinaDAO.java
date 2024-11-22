package dao;

import java.util.List;

import dto.DisciplinaDTO;

public interface DisciplinaDAO {
	
	public DisciplinaDTO salvar(DisciplinaDTO dto);
	public DisciplinaDTO buscarPorId(DisciplinaDTO dto);
	public List<DisciplinaDTO> listarTodos(DisciplinaDTO dto);
	public void excluir(DisciplinaDTO dto);

}
