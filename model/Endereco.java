package com.projeto.model;

public class Endereco {
		private long idEndereco;
		private String rua;
		private int numero;
		private String cep;
		private String complemento;
		private String pais;
		private String cidade;
		private Instituicao instituicao;
		
		public Endereco() {
			super();
		}
		public Endereco(long idEndereco, String rua, int numero, String cep, String complemento, String pais, String cidade, Instituicao instituicao) {
			super();
			this.idEndereco= idEndereco;
			this.rua = rua;
			this.numero = numero;
			this.cep = cep;
			this.complemento = complemento;
			this.pais = pais;
			this.cidade = cidade;
			this.instituicao = instituicao;
		}
		
		public long getIdEndereco() {
			return idEndereco;
		}
		public void setIdEndereco(long idEndereco) {
			this.idEndereco = idEndereco;
		}
		public String getRua() {
			return rua;
		}
		public void setRua(String rua) {
			this.rua = rua;
		}
		public int getNumero() {
			return numero;
		}
		public void setNumero(int numero) {
			this.numero = numero;
		}
		public String getCep() {
			return cep;
		}
		public void setCep(String cep) {
			this.cep = cep;
		}
		public String getComplemento() {
			return complemento;
		}
		public void setComplemento(String complemento) {
			this.complemento = complemento;
		}
		public String getPais() {
			return pais;
		}
		public void setPais(String pais) {
			this.pais = pais;
		}
		public String getCidade() {
			return cidade;
		}
		public void setCidade(String cidade) {
			this.cidade = cidade;
		}
		public Instituicao getInstituicao() {
			return instituicao;
		}
		public void setInstituicao(Instituicao instituicao) {
			this.instituicao = instituicao;
		}
		
		
	}

