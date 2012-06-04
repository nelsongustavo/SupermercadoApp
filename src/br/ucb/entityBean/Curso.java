package br.ucb.entityBean;

import java.io.Serializable;

public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private int semestres;
	private float valor;
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	public int getSemestres() { return semestres; }
	public void setSemestres(int s) { this.semestres = s; }
	public float getValor() { return valor; }
	public void setValor(float valor) { this.valor = valor; }
}