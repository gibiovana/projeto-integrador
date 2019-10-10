package com.projeto.model;

import java.sql.Date;

public class Pergunta {

		private long idPergunta;
		private String descricao;
		private Date dataHora;
		private Usuario usuario;
		
		public Pergunta() {
			super();
			this.dataHora = new java.sql.Date(new java.util.Date().getTime());
			this.usuario = new Usuario();
			
		}
		public Pergunta(long idPergunta, String descricao, Date dataHora, Usuario usuario) {
			super();
			this.idPergunta = idPergunta;
			this.descricao = descricao;
			this.dataHora = dataHora;
			this.usuario = usuario;
		}
		public long getIdPergunta() {
			return idPergunta;
		}
		public void setIdPergunta(long idPergunta) {
			this.idPergunta = idPergunta;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		public Date getDataHora() {
			return dataHora;
		}
		public void setDataHora(Date dataHora) {
			this.dataHora = dataHora;
		}
		public Usuario getUsuario() {
			return usuario;
		}
		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
		
		@Override
		public String toString() {
			return this.idPergunta+","+this.descricao+","+this.dataHora+","+this.usuario;
		}
		
		

}
