package br.ucb.managedBean;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.faces.event.ActionEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.ucb.entityBean.Curso;
import br.ucb.sessionBean.CursoRemote;
import br.ucb.util.JSFMensageiro;

public class CursoManagedBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private CursoRemote cursoBean;
	private Curso curso;
	private List<Curso> cursos;

	public CursoManagedBean(){

		try{
		Properties properties = new Properties();
		properties.put(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
		properties.put(InitialContext.PROVIDER_URL,"jnp://localhost:1099");
		Context context = new InitialContext(properties);
		this.cursoBean = (CursoRemote) context.lookup("CursoBean/remote");	
		}catch (NamingException e) {
			System.out.println(e);
		}	
		
		
		this.curso = new Curso();
		this.cursos = this.cursoBean.listar();
	}
	
	public void listar(ActionEvent evento) {
		this.curso = new Curso();
		this.cursos = this.cursoBean.listar();
	}
	
	public void salvar(ActionEvent evento) {
		this.cursoBean.salvar(this.curso);
		this.curso = new Curso();
		this.cursos = this.cursoBean.listar();
		JSFMensageiro.info("Curso incluido com sucesso!");
		}
	
	public void excluir(ActionEvent evento) {
		this.curso = (Curso) evento.getComponent().getAttributes().get("curso");
		if (this.cursoBean.excluir(this.curso)) {
		JSFMensageiro.info("Curso excluido com sucesso!");
		this.cursos = this.cursoBean.listar();
		}
		else
		JSFMensageiro.error("Erro inesperado ao excluir curso!");
		this.curso = new Curso();
	}

	public CursoRemote getCursoBean() {
		return cursoBean;
	}

	public void setCursoBean(CursoRemote cursoBean) {
		this.cursoBean = cursoBean;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
}
