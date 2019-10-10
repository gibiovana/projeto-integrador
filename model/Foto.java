package com.projeto.model;

public class Foto {
	private long idFoto;
	private String url;
	private Usuario usuario;
	private Instituicao instituicao;
	public Foto() {
		super();
		usuario = new Usuario ();
		instituicao = new Instituicao();
	}
	public Foto(long idFoto, String url, Usuario usuario, Instituicao instituicao) {
		super();
		this.idFoto = idFoto;
		this.url = url;
		this.usuario = usuario;
		this.instituicao = instituicao;
	}
	public long getIdFoto() {
		return idFoto;
	}
	public void setIdFoto(long idFoto) {
		this.idFoto = idFoto;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Instituicao getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	
	
	
	
	

}
