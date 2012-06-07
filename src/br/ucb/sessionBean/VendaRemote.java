package br.ucb.sessionBean;

import java.util.List;

import javax.ejb.Remote;

import br.ucb.entityBean.Venda;

@Remote
public interface VendaRemote {
	public Venda consultar(int id);
	public List<Venda> listar();
	public boolean excluir(Venda venda);
	public Venda salvar(Venda venda);
}
