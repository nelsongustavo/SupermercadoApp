package br.ucb.managedBean;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;

import br.ucb.entityBean.Produto;
import br.ucb.sessionBean.ProdutoRemote;
import br.ucb.util.JSFMensageiro;

public class ProdutoManagedBean {

	private ProdutoRemote produtoBean; 
	private Produto produto;
	private List<Produto> produtos;
	byte[] foto;
	String arquivo;
	
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
		return "/restrito/produtoForm?faces-redirect=true";
	}
	
	public String salvar() {
		if(this.produto.getId() == 0){
			this.produtoBean.salvar(this.produto);
		}else{
			this.produtoBean.alterar(this.produto);	
		}
		
		gravar();
		
		JSFMensageiro.info("Sucesso", this.produto.getNome() + " Gravado com sucesso");
		this.produto = new Produto();
		this.produtos = this.produtoBean.listar();
		return "produtoLista";
	}
	
	public void listar(ActionEvent evento) {
		this.produto = new Produto();
		this.produtos = this.produtoBean.listar();
	}

	
	public void excluir(ActionEvent evento) {
		this.produto = (Produto) evento.getComponent().getAttributes().get("produto");
		if (this.produtoBean.excluir(this.produto)) {
		
		JSFMensageiro.info("Sucesso", this.produto.getNome() + " Excluido com sucesso!");
		
		this.produtos = this.produtoBean.listar();
		}
		else
		JSFMensageiro.error("Erro inesperado ao excluir curso!");
		this.produto = new Produto();
	}
	
	
public void uploadAction (FileUploadEvent event){
		
		try {
			
			
			// pegando a foto
			foto = event.getFile().getContents();
			Date data = new Date();
			//pegando o nome da foto com o caminho
			String nome = (data.getTime() + ".jpg");
			
			//pegando o caminho que a foto vai ser gravada
			FacesContext facesContext = FacesContext.getCurrentInstance();  
			ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();  
			arquivo = scontext.getRealPath("/resources/images/" + nome);
			
			//setando o nome da foto no banco
			this.produto.setUrl(nome);
			
			if(foto != null) {  
	            FacesMessage msg = new FacesMessage("Sucesso", event.getFile().getFileName() + "Foi upada!");  
	            FacesContext.getCurrentInstance().addMessage(null, msg);  
	        }  

		}catch (Exception ex){
<<<<<<< HEAD
			FacesMessage msg = new FacesMessage("ERRO", event.getFile().getFileName() + "Não foi upada!");  
=======
			FacesMessage msg = new FacesMessage("ERRO", event.getFile().getFileName() + "NÃ£o foi upada!");  
>>>>>>> 9e882840f6f5ad5b72851c3bb5f7ceaa2021327f
            FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
		//gravar imagen na aplicacao
		public void gravar(){
			FileOutputStream fos;
			try {
			//passando o caminho;	
			fos = new FileOutputStream(arquivo);
			//passando o arquivo;
			fos.write(foto);
			fos.close();
			
			} catch (FileNotFoundException ex) {
			Logger.getLogger(ProdutoManagedBean.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
			Logger.getLogger(ProdutoManagedBean.class.getName()).log(Level.SEVERE, null, ex);
			}
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
