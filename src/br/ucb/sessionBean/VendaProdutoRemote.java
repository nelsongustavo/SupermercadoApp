package br.ucb.sessionBean;

import java.util.List;

import javax.ejb.Remote;

import br.ucb.entityBean.VendaProduto;

@Remote
public interface VendaProdutoRemote {
	public List<VendaProduto> listar();
	public void salvar(VendaProduto vendaProduto);
	public boolean excluir(VendaProduto vendaProduto);
}
