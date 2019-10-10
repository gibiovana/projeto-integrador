package com.projeto.persistence;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Statement;
import com.projeto.model.Endereco;

public class EnderecoDAO {

	private ConexaoMysql conexao;

	public EnderecoDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "projeto_integrador");
	}

	public void cadastrar(Endereco endereco) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO endereco VALUES (null, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, endereco.getRua());
			statement.setInt(2, endereco.getNumero());
			statement.setString(3, endereco.getCep());
			statement.setString(4, endereco.getComplemento());
			statement.setString(5, endereco.getCidade());
			statement.setString(6, endereco.getPais());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void editar(Endereco endereco) {
		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE endereco SET rua=? , numero=?, cep=?, complemento=?, cidade=?, pais=? WHERE id_endereco=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			statement.setString(1, endereco.getRua());
			statement.setInt(2, endereco.getNumero());
			statement.setString(3, endereco.getCep());
			statement.setString(4, endereco.getComplemento());
			statement.setString(5, endereco.getCidade());
			statement.setString(6, endereco.getPais());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluir(long idEndereco) {
		this.conexao.abrirConexao();
		String sqlDelete = "DELETE FROM endereco WHERE id_endereco=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, idEndereco);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public Endereco buscarPorId(long idEndereco) {
		Endereco endereco = null;
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM endereco WHERE id_endereco=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, idEndereco);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				endereco = new Endereco();
				endereco.setIdEndereco(rs.getLong("id_endereco"));
				endereco.setRua(rs.getString("rua"));
				endereco.setNumero(rs.getInt("numero"));
				endereco.setCep(rs.getString("cep"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setPais(rs.getString("pais"));
				endereco.setCidade(rs.getString("cidade"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return endereco;
	}

	public List<Endereco> buscarTodos() {
		this.conexao.abrirConexao();
		Endereco endereco = null;
		String sqlSelect = "SELECT * FROM endereco";
		List<Endereco> listaEnderecos = new ArrayList<Endereco>();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlSelect);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				endereco = new Endereco();
				endereco.setIdEndereco(rs.getLong("id_endereco"));
				endereco.setRua(rs.getString("rua"));
				endereco.setNumero(rs.getInt("numero"));
				endereco.setCep(rs.getString("cep"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setPais(rs.getString("pais"));
				endereco.setCidade(rs.getString("cidade"));

				listaEnderecos.add(endereco);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaEnderecos;
	}

}
