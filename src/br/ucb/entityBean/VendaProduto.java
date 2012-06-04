package br.ucb.entityBean;

import java.io.Serializable;

public class VendaProduto implements Serializable{

	private static final long serialVersionUID = 1L;

	private VendaProdutofk id;
	
	private double preco;

	private int quantidade;
	
	
	public VendaProdutofk getId() {
		return id;
	}
	public void setId(VendaProdutofk id) {
		this.id = id;
	}
	
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public class VendaProdutofk implements Serializable{
			
		private static final long serialVersionUID = 1L;


		private Venda venda;
		private Produto produto;

		public VendaProdutofk(){
			this.venda = new Venda();
			this.produto = new Produto();
		}
		
		public Venda getVenda() {
			return venda;
		}
		public void setVenda(Venda venda) {
			this.venda = venda;
		}
		
		public Produto getProduto() {
			return produto;
		}
		public void setProduto(Produto produto) {
			this.produto = produto;
		}

	}

}