package com.projeto.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.projeto.model.Curso;
import com.projeto.model.Foto;
import com.projeto.model.Instituicao;
import com.projeto.model.Nivel;
import com.projeto.model.Usuario;;

public class FotoDAO {

	private ConexaoMysql conexao;

	public FotoDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "projeto_integrador");
	}

	public void inserir(Foto foto) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO foto VALUES (null, ?, ?, ?)";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, foto.getUrl());
			statement.setLong(2, foto.getUsuario().getIdUsuario());
			statement.setLong(3, foto.getInstituicao().getIdInstituicao());

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void editar(Foto foto) {
		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE foto SET url=?, id_usuario=?, id_instituicao=? WHERE id_foto=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			statement.setString(1, foto.getUrl());
			statement.setLong(2, foto.getUsuario().getIdUsuario());
			statement.setLong(3, foto.getInstituicao().getIdInstituicao());
			statement.setLong(3, foto.getIdFoto());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluir(long id) {
		this.conexao.abrirConexao();
		String sqlDelete = "DELETE FROM foto WHERE id_foto=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	public Foto buscarPorId(long idFoto) {
		Foto foto = null;
		Usuario usuario = null;
		Instituicao instituicao = null;
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM foto  INNER JOIN usuario ON foto.id_usuario = usuario.id_usuario INNER JOIN instituicao ON foto.id_instituicao = instituicao.id_instituicao WHERE id_foto=?";

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, idFoto);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				foto = new Foto();
				foto.setIdFoto(rs.getLong("id_foto"));
				foto.setUrl(rs.getString("url"));
				
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEmail(rs.getString("email"));
				usuario.setArea(rs.getString("area"));
				foto.setUsuario(usuario);

				instituicao= new Instituicao();
				instituicao.setIdInstituicao(rs.getLong("id_instituicao"));
				instituicao.setNome(rs.getString("nome_instituicao"));
				instituicao.setTelefone(rs.getString("telefone"));
				instituicao.setTelefone2(rs.getString("telefone2"));
				foto.setInstituicao(instituicao);
				
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return foto;
	}
	
	public List<Foto> buscarTodos() {
		this.conexao.abrirConexao();
		Foto foto = null;
		Usuario usuario = null;
		Instituicao instituicao = null;
		String sqlSelect = "SELECT * FROM foto  INNER JOIN usuario ON foto.id_usuario = usuario.id_usuario INNER JOIN instituicao ON foto.id_instituicao = instituicao.id_instituicao";
		List<Foto> listaFotos = new ArrayList<Foto>();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlSelect);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				foto = new Foto();
				foto.setIdFoto(rs.getLong("id_foto"));
				foto.setUrl(rs.getString("url"));
				
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEmail(rs.getString("email"));
				usuario.setArea(rs.getString("area"));
				foto.setUsuario(usuario);

				instituicao= new Instituicao();
				instituicao.setIdInstituicao(rs.getLong("id_instituicao"));
				instituicao.setNome(rs.getString("nome_instituicao"));
				instituicao.setTelefone(rs.getString("telefone"));
				instituicao.setTelefone2(rs.getString("telefone2"));
				foto.setInstituicao(instituicao);
				
				listaFotos.add(foto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaFotos;
	}

}