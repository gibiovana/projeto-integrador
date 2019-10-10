package com.projeto.model;

public class UsuarioCurso {
	private long idUsuarioCurso;
	private Curso curso;
	private Usuario usuario;
	public UsuarioCurso() {
		super();
		this.curso = new Curso();
		this.usuario = new Usuario();
		
	}
	public long getIdUsuarioCurso() {
		return idUsuarioCurso;
	}
	public void setIdUsuarioCurso(long idUsuarioCurso) {
		this.idUsuarioCurso = idUsuarioCurso;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override
	public String toString() {
		return this.curso+","+this.usuario;
	}
}