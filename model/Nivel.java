package com.projeto.model;

import java.util.ArrayList;
import java.util.List;

public class Nivel {
	private long idNivel;
	private String nome;
	private int pontuacaoMinima;
	private List<Usuario> listaUsuarios;
	public Nivel() {
		super();
		this.listaUsuarios = new ArrayList<Usuario>();
	}

	public Nivel(long idNivel, String nome, int pontuacaoMinima, List<Usuario> listaUsuarios) {
		super();
		this.idNivel = idNivel;
		this.nome = nome;
		this.pontuacaoMinima = pontuacaoMinima;
		this.listaUsuarios = listaUsuarios;
	}
	
	
	public long getIdNivel() {
		return idNivel;
	}

	public void setIdNivel(long idNivel) {
		this.idNivel = idNivel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPontuacaoMinima() {
		return pontuacaoMinima;
	}

	public void setPontuacaoMinima(int pontuacaoMinima) {
		this.pontuacaoMinima = pontuacaoMinima;
	}
	
	
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

}