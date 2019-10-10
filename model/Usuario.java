package com.projeto.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private long idUsuario;
	private String nome;
	private String login;
	private String senha;
	private String email;
	private String area;
	private Nivel nivel;
	private List<Curso> listaCursos;
	
	public Usuario() {
		super();
		nivel=new Nivel();
		listaCursos = new ArrayList<Curso>();
	}

	public Usuario(long idUsuario, String nome, String login, String senha, String email, String area, Nivel nivel, ArrayList<Curso> listaCursos) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.area = area;
		this.nivel=nivel;
		this.listaCursos=listaCursos;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos (List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}
	
	public void adicionarCurso (Curso curso){
		listaCursos.add(curso);
	}
	
	public void excluirCurso (Curso curso){
		listaCursos.remove(curso);
	}
	public Curso getCurso(int posicao) {
		return listaCursos.get(posicao);
	}

	@Override
	public String toString() {
		return this.idUsuario+","+this.nome+","+this.login+","+this.senha+","+this.email+","+this.area+","+this.listaCursos;
	}
	
	
	
}
