package com.projeto.persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Statement;
import com.projeto.model.Nivel;
import com.projeto.model.Pergunta;
import com.projeto.model.Usuario;

public class PerguntaDAO {
	private ConexaoMysql conexao;

	public PerguntaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "projeto_integrador");

	}

	public void cadastrar(Pergunta pergunta) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO pergunta VALUES (null, ?, ?, ?)";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, pergunta.getDescricao());
			statement.setDate(2, (Date) pergunta.getDataHora());
			statement.setLong(3, pergunta.getUsuario().getIdUsuario());
			statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			this.conexao.fecharConexao();
		}

	}

	public void editar(Pergunta pergunta) {
		this.conexao.abrirConexao();

		String sqlUpdate = "UPDATE pergunta SET descricao_pergunta=? , data_hora=?, id_usuario=? WHERE id_pergunta=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			statement.setString(1, pergunta.getDescricao());
			statement.setDate(2, (Date) pergunta.getDataHora());
			statement.setLong(3, pergunta.getUsuario().getIdUsuario());
			statement.setLong(4, pergunta.getIdPergunta());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluir(long idPergunta) {
		this.conexao.abrirConexao();
		String sqlDelete = "DELETE FROM pergunta WHERE id_pergunta=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, idPergunta);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public Pergunta buscarPorId(long idPergunta) {
		Pergunta pergunta = null;
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM pergunta INNER JOIN usuario ON pergunta.id_usuario = usuario.id_usuario INNER JOIN nivel ON usuario.id_nivel = nivel.id_nivel WHERE id_pergunta=?";

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, idPergunta);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				pergunta = new Pergunta();
				pergunta.setIdPergunta(rs.getLong("id_pergunta"));
				pergunta.setDescricao(rs.getString("descricao_pergunta"));
				pergunta.setDataHora(rs.getDate("data_hora"));
				
				Usuario usuario = new Usuario();
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
				
				
				pergunta.setUsuario(usuario);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return pergunta;
	}

	public List<Pergunta> buscarTodasPerguntas() {
		this.conexao.abrirConexao();
		Pergunta pergunta = null;
		String sqlSelect = "SELECT * FROM pergunta INNER JOIN usuario ON pergunta.id_usuario = usuario.id_usuario INNER JOIN nivel ON usuario.id_nivel = nivel.id_nivel";
		List<Pergunta> listaPergunta = new ArrayList<Pergunta>();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlSelect);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				pergunta = new Pergunta();
				pergunta.setIdPergunta(rs.getLong("id_pergunta"));
				pergunta.setDescricao(rs.getString("descricao_pergunta"));
				pergunta.setDataHora(rs.getDate("data_hora"));
				
				Usuario usuario = new Usuario();
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
				pergunta.setUsuario(usuario);
				
				
				listaPergunta.add(pergunta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaPergunta;
	}

	public Pergunta buscarPorDescricao(String descricao) {
		Pergunta pergunta = null;
		this.conexao.abrirConexao();

		String sqlInsert = "SELECT * FROM pergunta INNER JOIN usuario ON pergunta.id_usuario = usuario.id_usuario INNER JOIN nivel ON usuario.id_nivel = nivel.id_nivel WHERE descricao_pergunta=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setString(1, descricao);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				pergunta = new Pergunta();
				pergunta.setDescricao(rs.getString("descricao_pergunta"));
				pergunta.setDataHora(rs.getDate("data_hora"));
			
				Usuario usuario = new Usuario();
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
				
				pergunta.setUsuario(usuario);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return pergunta;
	}

}
