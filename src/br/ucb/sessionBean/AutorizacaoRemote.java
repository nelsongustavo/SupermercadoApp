package br.ucb.sessionBean;

import java.util.List;

import javax.ejb.Remote;

import br.ucb.entityBean.Autorizacao;

@Remote
public interface AutorizacaoRemote {
	public Autorizacao consultar(String login);
	public List<Autorizacao> listar();
	public void salvar(Autorizacao autorizacao);
	public boolean excluir(Autorizacao autorizacao);
}
