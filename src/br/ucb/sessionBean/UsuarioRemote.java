package br.ucb.sessionBean;

import java.util.List;

import javax.ejb.Remote;

import br.ucb.entityBean.Usuario;

@Remote
public interface UsuarioRemote {
	public Usuario consultar(String login);
	public List<Usuario> listar();
	public void salvar(Usuario usuario);
	public boolean excluir (Usuario usuario);
}
