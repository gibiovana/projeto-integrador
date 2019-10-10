package com.projeto.model;

import java.util.ArrayList;
import java.util.List;

public class Instituicao {
	private long idInstituicao;
	private String nome;
	private String telefone;
	private String telefone2;
	private List<Curso> listaCursos;

	
	public Instituicao() {
		super();		
		listaCursos = new ArrayList<Curso>();
	}

	public Instituicao(long idInstituicao, String nome, String telefone, String telefone2, List<Curso> listaCursos) {
		super();
		this.idInstituicao = idInstituicao;
		this.nome = nome;
		this.telefone = telefone;
		this.telefone2 = telefone2;
		this.listaCursos = listaCursos;
	}
	
	public long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	
	
	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	@Override
	public String toString() {
		return this.idInstituicao+","+this.nome+","+this.telefone+","+this.telefone2+","+this.listaCursos;
	}
	
	}
