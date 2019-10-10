package com.projeto.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.projeto.model.Curso;
import com.projeto.model.Nivel;
import com.projeto.model.Usuario;

public class UsuarioDAO {

	private ConexaoMysql conexao;
	public UsuarioDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "projeto_integrador");
	}

	public void cadastrar(Usuario usuario) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO usuario VALUES (null, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getLogin());
			statement.setString(3, usuario.getSenha());
			statement.setString(4, usuario.getEmail());
			statement.setString(5, usuario.getArea());
			statement.setLong(6, usuario.getNivel().getIdNivel());
			
			statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void editar(Usuario usuario) {
		this.conexao.abrirConexao();

		String sqlUpdate = "UPDATE usuario SET nome_usuario=? , login=?, senha=?, email=?, area=?, id_nivel=? WHERE id_usuario=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getLogin());
			statement.setString(3, usuario.getSenha());
			statement.setString(4, usuario.getEmail());
			statement.setString(5, usuario.getArea());
			statement.setLong(6, usuario.getNivel().getIdNivel());
			statement.setLong(8, usuario.getIdUsuario());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluir(long id) {
		this.conexao.abrirConexao();
		String sqlDelete = "DELETE FROM usuario WHERE id_usuario=?";
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

	public Usuario buscarPorId(long id) {
		Usuario usuario = null;
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM usuario INNER JOIN nivel ON usuario.id_nivel = nivel.id_nivel WHERE id_usuario=?";

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEmail(rs.getString("email"));
				usuario.setArea(rs.getString("area"));

				

				Nivel nivel = new Nivel();
				nivel.setIdNivel(rs.getLong("id_nivel"));
				nivel.setNome(rs.getString("nome_nivel"));
				nivel.setPontuacaoMinima(rs.getInt("pontuacao_minima"));
				usuario.setNivel(nivel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return usuario;
	}

	public List<Usuario> buscarTodos() {
		this.conexao.abrirConexao();
		Usuario usuario = null;
		String sqlSelect = "SELECT * FROM usuario INNER JOIN nivel ON usuario.id_nivel = nivel.id_nivel";
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlSelect);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEmail(rs.getString("email"));
				usuario.setArea(rs.getString("area"));


				Nivel nivel = new Nivel();
				nivel.setIdNivel(rs.getLong("id_nivel"));
				nivel.setNome(rs.getString("nome_nivel"));
				nivel.setPontuacaoMinima(rs.getInt("pontuacao_minima"));
				usuario.setNivel(nivel);

				listaUsuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaUsuarios;
	}

	public Usuario buscarPorNome(String nome) {
		Usuario usuario = null;
		this.conexao.abrirConexao();

		String sqlInsert = "SELECT * FROM usuario INNER JOIN nivel ON usuario.id_nivel = nivel.id_nivel WHERE nome_usuario=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setString(1, nome);
			
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEmail(rs.getString("email"));
				usuario.setArea(rs.getString("area"));


				Nivel nivel = new Nivel();
				nivel.setIdNivel(rs.getLong("id_nivel"));
				nivel.setNome(rs.getString("nome_nivel"));
				nivel.setPontuacaoMinima(rs.getInt("pontuacao_minima"));
				usuario.setNivel(nivel);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return usuario;
	}

	public Usuario buscarPorEmailESenha(String email, String senha) {
		Usuario usuario = null;
		this.conexao.abrirConexao();

		String sqlInsert = "SELECT * FROM usuario INNER JOIN nivel ON usuario.id_nivel = nivel.id_nivel WHERE email=? AND senha=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setString(1, email);
			statement.setString(2, senha);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEmail(rs.getString("email"));
				usuario.setArea(rs.getString("area"));

				
				Nivel nivel = new Nivel();
				nivel.setIdNivel(rs.getLong("id_nivel"));
				nivel.setNome(rs.getString("nome_nivel"));
				nivel.setPontuacaoMinima(rs.getInt("pontuacao_minima"));
				usuario.setNivel(nivel);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return usuario;
	}
}
