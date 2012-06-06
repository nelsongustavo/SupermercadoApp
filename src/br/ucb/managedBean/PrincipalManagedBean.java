package br.ucb.managedBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.faces.event.ActionEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.ucb.entityBean.Item;
import br.ucb.entityBean.Produto;
import br.ucb.sessionBean.ProdutoRemote;

public class PrincipalManagedBean {
	
	private ProdutoRemote produtoBean;
	private Produto produto;
	private Item item;
	private List<Produto>produtos;
	private List<Item> itens;
	
	
	public PrincipalManagedBean() {
		try{
			Properties properties = new Properties();
			properties.put(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			properties.put(InitialContext.PROVIDER_URL,"jnp://localhost:1099");
			Context context = new InitialContext(properties);
			this.produtoBean = (ProdutoRemote) context.lookup("ProdutoBean/remote");	
			}catch (NamingException e) {
				System.out.println(e);
			}
		
		this.produtos = this.produtoBean.listar();
		this.itens  = new ArrayList<Item>();
		this.item = new Item();
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}


	public void addItem(ActionEvent evento){
		this.item.setProduto((Produto)evento.getComponent().getAttributes().get("produto")) ;
		this.itens.add(this.item);
		
		for (Item i : itens) {
			System.out.println(i.getProduto().getId());
			System.out.println(i.getProduto().getNome());
			System.out.println(i.getQuantidade());
		}
		this.item = new Item();
	}

	public String listar(){
		this.produtos = produtoBean.listar();
		return "produtos";
	}
	
    
}
