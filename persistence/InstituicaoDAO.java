package com.projeto.persistence;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.projeto.model.Curso;
import com.projeto.model.Instituicao;

public class InstituicaoDAO {	
	
	private ConexaoMysql conexao;

	public InstituicaoDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost","root","","projeto_integrador");

	}
	public void cadastrar(Instituicao instituicao) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO instituicao VALUES (null, ?, ?, ?)";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, instituicao.getNome());
			statement.setString(2, instituicao.getTelefone());
			statement.setString(3, instituicao.getTelefone2());
			
			statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			this.conexao.fecharConexao();
		}

	}public void editar(Instituicao instituicao) {
		this.conexao.abrirConexao();

		String sqlUpdate = "UPDATE instituicao SET nome_instituicao=? , telefone=?, telefone2=? WHERE id_instituicao=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			statement.setString(1, instituicao.getNome());
			statement.setString(2, instituicao.getTelefone());
			statement.setString(3, instituicao.getTelefone2());
			statement.setLong(4,instituicao.getIdInstituicao());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluir(long idInstituicao) {
		this.conexao.abrirConexao();
		String sqlDelete = "DELETE FROM instituicao WHERE id_instituicao=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, idInstituicao);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public Instituicao buscarPorId(long idInstituicao) {
		Instituicao instituicao = null;
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM instituicao WHERE id_instituicao=?";

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, idInstituicao);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				instituicao = new Instituicao();
				instituicao.setIdInstituicao(rs.getLong("id_instituicao"));
				instituicao.setNome(rs.getString("nome_instituicao"));
				instituicao.setTelefone(rs.getString("telefone"));
				instituicao.setTelefone2(rs.getString("telefone2"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return instituicao;
	}

	public List<Instituicao> buscarTodasInstituicoes() {
		this.conexao.abrirConexao();
		Instituicao instituicao = null;
		String sqlSelect = "SELECT * FROM instituicao";
		List<Instituicao> listaInstituicao = new ArrayList<Instituicao>();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlSelect);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				instituicao = new Instituicao();
				instituicao.setIdInstituicao(rs.getLong("id_instituicao"));
				instituicao.setNome(rs.getString("nome_instituicao"));
				instituicao.setTelefone(rs.getString("telefone"));
				instituicao.setTelefone2(rs.getString("telefone2"));
				listaInstituicao.add(instituicao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaInstituicao;
	}

	public Instituicao buscarPorNome(String nome) {
		Instituicao instituicao = null;
		this.conexao.abrirConexao();

		String sqlInsert = "SELECT * FROM instituicao WHERE nome_instituicao=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setString(1, nome);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				instituicao = new Instituicao();
				instituicao.setIdInstituicao(rs.getLong("id_instituicao"));
				instituicao.setNome(rs.getString("nome_instituicao"));
				instituicao.setTelefone(rs.getString("telefone"));
				instituicao.setTelefone2(rs.getString("telefone2"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return instituicao;
	}
	
	

	
	
}