package br.ucb.sessionBean;

import java.util.List;

import javax.ejb.Remote;

import br.ucb.entityBean.Cliente;

@Remote
public interface ClienteRemote {

	public Cliente consultar(int id);
	public List<Cliente> listar();
	public void salvar(Cliente cliente);
	public boolean excluir(Cliente cliente);
	
}
