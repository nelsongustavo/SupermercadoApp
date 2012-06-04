package br.ucb.sessionBean;

import java.util.List;

import javax.ejb.Remote;

import br.ucb.entityBean.Venda;

@Remote
public interface VendaRemote {
	public Venda consultar(int id);
	public List<Venda> listar();
	public void salvar(Venda venda);
	public boolean excluir(Venda venda);
}
