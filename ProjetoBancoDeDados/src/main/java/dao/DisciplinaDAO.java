package dao;

import dto.DisciplinaDTO;

public interface DisciplinaDAO {
	
	public void salvar(DisciplinaDTO dto);
	public DisciplinaDTO buscarPorId(DisciplinaDTO dto);
	public void excluir(DisciplinaDTO dto);

}
