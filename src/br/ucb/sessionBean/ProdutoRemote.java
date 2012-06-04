package br.ucb.sessionBean;

import java.util.List;

import javax.ejb.Remote;

import br.ucb.entityBean.Produto;

@Remote
public interface ProdutoRemote {
	public Produto consultar(int id);
	public List<Produto> listar();
	public void salvar(Produto produto);
	public boolean excluir(Produto produto);
	public void alterar(Produto produto);;
}
