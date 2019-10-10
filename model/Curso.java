package com.projeto.model;

import java.util.ArrayList;
import java.util.List;

public class Curso {
	private long idCurso;
	private String nome;
	private String duracaoMedia;
	private String descricao;
	private Instituicao instituicao;	
	private List<Usuario> listaUsuarios;
	
	public Curso() {
		super();
		instituicao= new Instituicao();
		listaUsuarios = new ArrayList<Usuario>();
	}
	public Curso(long idCurso, String nome, String duracaoMedia, String descricao, Instituicao instituicao, List<Usuario> listaUsuarios) {
		super();
		this.idCurso = idCurso;
		this.nome = nome;
		this.duracaoMedia = duracaoMedia;
		this.descricao = descricao;
		this.instituicao = instituicao;
		this.listaUsuarios = listaUsuarios;
	}
	public long getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(long idCurso) {
		this.idCurso = idCurso;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDuracaoMedia() {
		return duracaoMedia;
	}
	public void setDuracaoMedia(String duracaoMedia) {
		this.duracaoMedia = duracaoMedia;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public Instituicao getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	
	
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
	public void adicionarUsuario (Usuario usuario){
		listaUsuarios.add(usuario);
	}
	
	public void excluirUsuario (Usuario usuario){
		listaUsuarios.remove(usuario);
	}
	public Usuario getUsuario(int posicao) {
		return listaUsuarios.get(posicao);
	}

	@Override
	public String toString() {
		return this.idCurso+","+this.nome+","+this.duracaoMedia+","+this.descricao+","+this.listaUsuarios;
	}
	
	
	

}
