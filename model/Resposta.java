package com.projeto.model;

public class Resposta{
	private long idResposta;
	private String descricaoResposta;
	private Pergunta pergunta;
	private Usuario usuario;
	public Resposta() {
		super();
		this.pergunta = new Pergunta();
		this.usuario = new Usuario();
	}
	public Resposta(long idResposta, String descricaoResposta, Pergunta pergunta, Usuario usuario) {
		super();
		this.idResposta = idResposta;
		this.descricaoResposta= descricaoResposta;
		this.pergunta = pergunta;
		this.usuario=usuario;
	}
	public long getIdResposta() {
		return idResposta;
	}
	public void setIdResposta(long idResposta) {
		this.idResposta = idResposta;
	}
	public Pergunta getPergunta() {
		return pergunta;
	}
	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
	public String getDescricaoResposta() {
		return descricaoResposta;
	}
	public void setDescricaoResposta(String descricaoResposta) {
		this.descricaoResposta = descricaoResposta;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
