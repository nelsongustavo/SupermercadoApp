package br.ucb.managedBean;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.ucb.entityBean.Autorizacao;
import br.ucb.entityBean.Cliente;
import br.ucb.entityBean.Usuario;
import br.ucb.sessionBean.AutorizacaoRemote;
import br.ucb.sessionBean.ClienteRemote;
import br.ucb.sessionBean.UsuarioRemote;
import br.ucb.util.JSFMensageiro;

public class ClienteManagedBean {

	private ClienteRemote clienteBean;
	private Cliente cliente;
	private List<Cliente> clientes;
	
	private UsuarioRemote usuarioBean;
	private Usuario usuario;
	
	private AutorizacaoRemote autorizacaoBean;
	private Autorizacao autorizacao;
	
	public ClienteManagedBean(){
		
		try{
			Properties properties = new Properties();
			properties.put(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			properties.put(InitialContext.PROVIDER_URL,"jnp://localhost:1099");
			Context context = new InitialContext(properties);
			this.usuarioBean = (UsuarioRemote) context.lookup("UsuarioBean/remote");
			this.clienteBean = (ClienteRemote) context.lookup("ClienteBean/remote");
			this.autorizacaoBean = (AutorizacaoRemote) context.lookup("AutorizacaoBean/remote");
			}catch (NamingException e) {
				System.out.println(e);
			}
		
		this.usuario = new Usuario();
		this.cliente = new Cliente();
		this.autorizacao = new Autorizacao();
		this.clientes = this.clienteBean.listar();
			
	}
	
	public String salvar(){

		
		this.autorizacao.setPapel("cliente");
		this.autorizacao.setLogin(this.usuario.getLogin());
		
		this.autorizacaoBean.salvar(autorizacao);
				
		this.usuario.setAutorizacao(this.autorizacao);
		this.usuarioBean.salvar(this.usuario);
		
		this.cliente.setUsuario(this.usuario);
		this.clienteBean.salvar(this.cliente);
		
		this.cliente = new Cliente();
		this.usuario = new Usuario();
		this.clientes = clienteBean.listar();
		JSFMensageiro.info("Cadastrado com sucesso!");		
		return "index";
	}

	public ClienteRemote getClienteBean() {
		return clienteBean;
	}

	public void setClienteBean(ClienteRemote clienteBean) {
		this.clienteBean = clienteBean;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public UsuarioRemote getUsuarioBean() {
		return usuarioBean;
	}

	public void setUsuarioBean(UsuarioRemote usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	} 

	
}
