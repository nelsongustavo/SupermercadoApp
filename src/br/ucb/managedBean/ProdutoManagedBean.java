package br.ucb.managedBean;

import java.util.List;
import java.util.Properties;

import javax.faces.event.ActionEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.ucb.entityBean.Produto;
import br.ucb.sessionBean.ProdutoRemote;
import br.ucb.util.JSFMensageiro;

public class ProdutoManagedBean {

	private ProdutoRemote produtoBean; 
	private Produto produto;
	private List<Produto> produtos;
	
	public ProdutoManagedBean(){
		try{
			Properties properties = new Properties();
			properties.put(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			properties.put(InitialContext.PROVIDER_URL,"jnp://localhost:1099");
			Context context = new InitialContext(properties);
			this.produtoBean = (ProdutoRemote) context.lookup("ProdutoBean/remote");	
			}catch (NamingException e) {
				System.out.println(e);
			}
		
		this.produto = new Produto();
		this.produtos = this.produtoBean.listar();
	}
	
	public String incluir(){
		return "/restrito/produtoForm";
	}
	
	public String salvar() {
		System.out.println("id : "+this.produto.getId());
		System.out.println("nome: "+this.produto.getNome());
		System.out.println("marca:"+this.produto.getMarca());
		
		if(this.produto.getId() == 0){
			System.out.println("Salvar !!");
			this.produtoBean.salvar(this.produto);
		}else{
			System.out.println("Alterar !!");
			this.produtoBean.alterar(this.produto);	
		}
		
		this.produto = new Produto();
		this.produtos = this.produtoBean.listar();
		JSFMensageiro.info("Curso incluido com sucesso!");
		return "produtoLista";
	}
	
	public void listar(ActionEvent evento) {
		this.produto = new Produto();
		this.produtos = this.produtoBean.listar();
	}

	
	public void excluir(ActionEvent evento) {
		this.produto = (Produto) evento.getComponent().getAttributes().get("produto");
		if (this.produtoBean.excluir(this.produto)) {
		JSFMensageiro.info("Curso excluido com sucesso!");
		this.produtos = this.produtoBean.listar();
		}
		else
		JSFMensageiro.error("Erro inesperado ao excluir curso!");
		this.produto = new Produto();
	}
	
	public ProdutoRemote getProdutoBean() {
		return produtoBean;
	}

	public void setProdutoBean(ProdutoRemote produtoBean) {
		this.produtoBean = produtoBean;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
	
}
