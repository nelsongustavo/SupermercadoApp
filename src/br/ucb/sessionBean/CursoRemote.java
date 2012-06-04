package br.ucb.sessionBean;

import java.util.List;
import br.ucb.entityBean.Curso;

public interface CursoRemote {
	
	public void salvar(Curso curso);
	public boolean excluir(Curso curso);
	public List<Curso> listar();
	public Curso consultar(int id);
}
