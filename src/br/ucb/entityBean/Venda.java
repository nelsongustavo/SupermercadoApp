package br.ucb.entityBean;

import java.io.Serializable;
import java.util.Collection;

public class Venda implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private Cliente cliente;
	private Collection<VendaProduto>vendaProdutoList;
	
	public Venda(){
		this.cliente = new Cliente();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Collection<VendaProduto> getVendaProdutoList() {
		return vendaProdutoList;
	}
	public void setVendaProdutoList(Collection<VendaProduto> vendas) {
		this.vendaProdutoList = vendas;
	}
}
