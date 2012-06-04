package br.ucb.entityBean;

import java.io.Serializable;

public class Autorizacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String login;
	private String papel;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	
	public String getPapel() {
		return papel;
	}
	
	public void setPapel(String papel) {
		this.papel = papel;
	}
	
}