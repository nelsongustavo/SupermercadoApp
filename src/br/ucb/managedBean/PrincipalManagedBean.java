package br.ucb.managedBean;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.faces.event.ActionEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.ucb.entityBean.Cliente;
import br.ucb.entityBean.Item;
import br.ucb.entityBean.Produto;
import br.ucb.entityBean.Venda;
import br.ucb.entityBean.VendaProduto;
import br.ucb.entityBean.VendaProduto.VendaProdutofk;
import br.ucb.sessionBean.ClienteRemote;
import br.ucb.sessionBean.ProdutoRemote;
import br.ucb.sessionBean.VendaProdutoRemote;
import br.ucb.sessionBean.VendaRemote;

public class PrincipalManagedBean {

	private Cliente cliente;
	private ClienteRemote clienteBean;
	private Venda venda;
	private VendaProduto vendaProduto;
	private VendaProdutofk id;
	private VendaProdutoRemote vendaProdutoBean;
	private VendaRemote vendaBean;
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
			this.vendaBean = (VendaRemote) context.lookup("VendaBean/remote");
			this.vendaProdutoBean = (VendaProdutoRemote) context.lookup("VendaProdutoBean/remote");
			this.clienteBean = (ClienteRemote) context.lookup("ClienteBean/remote");
		}catch (NamingException e) {
			System.out.println(e);
		}

		this.produtos = this.produtoBean.listar();
		this.venda = new Venda();
		this.itens  = new ArrayList<Item>();
		this.cliente = new Cliente();
		this.item = new Item();
		this.vendaProduto = new VendaProduto();
		this.id = vendaProduto.new VendaProdutofk();
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

	public void removeItem(ActionEvent evento){
		this.setItem((Item)evento.getComponent().getAttributes().get("item"));
		System.out.println(this.item.getProduto().getNome());

		this.itens.remove(this.item);
		this.item = new Item();

	}

	public String listar(){
		this.produtos = produtoBean.listar();
		return "produtos";
	}

	public String gravaVenda(){
		try{
			// Cliente
			this.cliente = clienteBean.consultar(1);    	

			// Venda
			this.venda.setCliente(cliente);
			this.venda = vendaBean.salvar(this.venda);

			// Chave composta
			id.setVenda(venda);
			for (Item i : itens) {
				Produto produto = new Produto();
				produto = i.getProduto();

				id.setProduto(produto);

				this.vendaProduto = new VendaProduto();    			
				vendaProduto.setId(id);
				vendaProduto.setQuantidade(i.getQuantidade());
				vendaProduto.setPreco(i.getProduto().getPreco());

				vendaProdutoBean.salvar(vendaProduto);

				System.out.println("Id da venda : "+vendaProduto.getId().getVenda().getId());
				System.out.println("Nome do Produto:"+vendaProduto.getId().getProduto().getNome());
				System.out.println("Quantidade:"+vendaProduto.getQuantidade());
				System.out.println("Preço: R$: "+vendaProduto.getPreco());
			}
			}catch (UndeclaredThrowableException e) {
				System.out.println(e.getCause());
				return "default";
			}
			return "produtos";
		}

		/* public String testeGrava(){
    	try{
    	this.cliente = new Cliente();
    	this.cliente = clienteBean.consultar(1);

    	this.produto = new Produto();
    	this.produto = this.produtoBean.consultar(1);

    	this.venda.setCliente(this.cliente);
    	this.venda = this.vendaBean.salvar(venda);

    	this.id.setProduto(produto);
    	this.id.setVenda(venda);

    	this.vendaProduto = new VendaProduto();
    	this.vendaProduto.setId(this.id);
    	this.vendaProduto.setPreco(5.0);
    	this.vendaProduto.setQuantidade(1);

    	this.vendaProdutoBean.salvar(this.vendaProduto);
    	return "default";
    	}
    	catch (UndeclaredThrowableException e) {
			System.out.println(e.getCause());
	    	return "default";
		}

    }*/

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		public ClienteRemote getClienteBean() {
			return clienteBean;
		}

		public void setClienteBean(ClienteRemote clienteBean) {
			this.clienteBean = clienteBean;
		}

		public Venda getVenda() {
			return venda;
		}

		public void setVenda(Venda venda) {
			this.venda = venda;
		}

		public VendaProduto getVendaProduto() {
			return vendaProduto;
		}

		public void setVendaProduto(VendaProduto vendaProduto) {
			this.vendaProduto = vendaProduto;
		}

		public VendaProdutoRemote getVendaProdutoBean() {
			return vendaProdutoBean;
		}

		public void setVendaProdutoBean(VendaProdutoRemote vendaProdutoBean) {
			this.vendaProdutoBean = vendaProdutoBean;
		}

		public VendaRemote getVendaBean() {
			return vendaBean;
		}

		public void setVendaBean(VendaRemote vendaBean) {
			this.vendaBean = vendaBean;
		}

		public ProdutoRemote getProdutoBean() {
			return produtoBean;
		}

		public void setProdutoBean(ProdutoRemote produtoBean) {
			this.produtoBean = produtoBean;
		}
	}
