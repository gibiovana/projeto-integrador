package com.projeto.persistence;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.projeto.model.Nivel;
import com.projeto.model.Pergunta;
import com.projeto.model.Resposta;
import com.projeto.model.Usuario;

public class RespostaDAO {
	private ConexaoMysql conexao;
	public RespostaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "projeto_integrador");

	}

	public Resposta cadastrar(Resposta resposta) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO resposta VALUES (null, ?, ?, ?)";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, resposta.getDescricaoResposta());
			statement.setLong(2, resposta.getPergunta().getIdPergunta());
			statement.setLong(3, resposta.getUsuario().getIdUsuario());
			statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();
			resposta.setIdResposta(rs.getLong(1));
			
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			this.conexao.fecharConexao();
		}
		return resposta;
	}

	public void editar(Resposta resposta) {
		this.conexao.abrirConexao();

		String sqlUpdate = "UPDATE resposta SET descricao_resposta=? , id_pergunta=?, id_usuario=? WHERE id_resposta=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			statement.executeUpdate();
			statement.setString(1, resposta.getDescricaoResposta());
			statement.setLong(2, resposta.getPergunta().getIdPergunta());
			statement.setLong(3, resposta.getUsuario().getIdUsuario());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluir(long idResposta) {
		this.conexao.abrirConexao();
		String sqlDelete = "DELETE FROM resposta WHERE id_resposta=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, idResposta);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public Resposta buscarPorId(long idResposta) {
		Resposta resposta = null;
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM resposta INNER JOIN pergunta ON resposta.id_pergunta = pergunta.id_pergunta INNER JOIN usuario ON pergunta.id_usuario = usuario.id_usuario  INNER JOIN nivel ON usuario.id_nivel = nivel.id_nivel INNER JOIN usuario u ON resposta.id_usuario = u.id_usuario INNER JOIN nivel n ON u.id_nivel = n.id_nivel WHERE id_resposta=?";

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, idResposta);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				resposta = new Resposta();
				resposta.setIdResposta(rs.getLong("id_resposta"));
				resposta.setDescricaoResposta(rs.getString("descricao_resposta"));

				
				Pergunta pergunta = new Pergunta();
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
				resposta.setPergunta(pergunta);
				
				//fim do set pergunta
				
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEmail(rs.getString("email"));
				usuario.setArea(rs.getString("area"));


				nivel.setIdNivel(rs.getLong("id_nivel"));
				nivel.setNome(rs.getString("nome_nivel"));
				nivel.setPontuacaoMinima(rs.getInt("pontuacao_minima"));
				usuario.setNivel(nivel);
				
				resposta.setUsuario(usuario);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return resposta;
	}

	public List<Resposta> buscarTodasRespostas() {
		this.conexao.abrirConexao();
		Resposta resposta = null;
		String sqlSelect = "SELECT * FROM resposta INNER JOIN pergunta ON resposta.id_pergunta = pergunta.id_pergunta INNER JOIN usuario ON pergunta.id_usuario = usuario.id_usuario  INNER JOIN nivel ON usuario.id_nivel = nivel.id_nivel INNER JOIN usuario u ON resposta.id_usuario = u.id_usuario INNER JOIN nivel n ON u.id_nivel = n.id_nivel";
		List<Resposta> listaResposta = new ArrayList<Resposta>();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlSelect);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				resposta = new Resposta();
				resposta.setIdResposta(rs.getLong("id_resposta"));
				resposta.setDescricaoResposta(rs.getString("descricao_resposta"));
				
				
				Pergunta pergunta = new Pergunta();
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
				resposta.setPergunta(pergunta);
				
				
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEmail(rs.getString("email"));
				usuario.setArea(rs.getString("area"));

				nivel.setIdNivel(rs.getLong("id_nivel"));
				nivel.setNome(rs.getString("nome_nivel"));
				nivel.setPontuacaoMinima(rs.getInt("pontuacao_minima"));
				usuario.setNivel(nivel);
				
				resposta.setUsuario(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaResposta;
	}

	public Resposta buscarPorDescricao(String descricaoResposta) {
		Resposta resposta = null;
		this.conexao.abrirConexao();

		String sqlInsert = "SELECT * FROM resposta INNER JOIN pergunta ON resposta.id_pergunta = pergunta.id_pergunta INNER JOIN usuario ON pergunta.id_usuario = usuario.id_usuario INNER JOIN nivel ON usuario.id_nivel = nivel.id_nivel INNER JOIN usuario u ON resposta.id_usuario = u.id_usuario INNER JOIN nivel n ON u.id_nivel = n.id_nivel WHERE descricao_resposta=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setString(1, descricaoResposta);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				resposta = new Resposta();
				resposta.setDescricaoResposta(rs.getString("descricao_resposta"));
				
				resposta = new Resposta();
				resposta.setIdResposta(rs.getLong("id_resposta"));
				resposta.setDescricaoResposta(rs.getString("descricao_resposta"));
				
				
				Pergunta pergunta = new Pergunta();
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
				resposta.setPergunta(pergunta);
				
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEmail(rs.getString("email"));
				usuario.setArea(rs.getString("area"));
				
				nivel.setIdNivel(rs.getLong("id_nivel"));
				nivel.setNome(rs.getString("nome_nivel"));
				nivel.setPontuacaoMinima(rs.getInt("pontuacao_minima"));
				usuario.setNivel(nivel);
				
				resposta.setUsuario(usuario);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return resposta;
	}
	
	
}