package com.projeto.persistence;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.projeto.model.Curso;
import com.projeto.model.Usuario;
import com.projeto.model.UsuarioCurso;

public class UsuarioCursoDAO {

	private ConexaoMysql conexao;

	public UsuarioCursoDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "projeto_integrador");
	}

	public void cadastrar(UsuarioCurso usuarioCurso) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO usuario_curso VALUES (null, ?, ?)";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);

			statement.setLong(1, usuarioCurso.getCurso().getIdCurso());
			statement.setLong(2, usuarioCurso.getUsuario().getIdUsuario());
			statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			this.conexao.fecharConexao();
		}

	}

	public void editar(UsuarioCurso usuarioCurso ) {
		this.conexao.abrirConexao();

		String sqlUpdate = "UPDATE usuario_curso SET id_curso = ? , id_usuario=? WHERE id_usario_curso=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			statement.setLong(1, usuarioCurso.getCurso().getIdCurso());
			statement.setLong(2, usuarioCurso.getUsuario().getIdUsuario());
		
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluir(long idUsuarioCurso) {
		this.conexao.abrirConexao();
		String sqlDelete = "DELETE FROM usuarioCurso WHERE id_usuario_curso=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, idUsuarioCurso);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public UsuarioCurso buscarPorId(long idUsuarioCurso) {
		UsuarioCurso usuarioCurso = null;
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM usuario_curso INNER JOIN  usuario ON usuario_curso.id_usuario = usuario.id_usuario INNER JOIN curso ON usuario_curso.id_curso = curso.id_curso WHERE id_usuario_curso=?";

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, idUsuarioCurso);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				usuarioCurso = new UsuarioCurso();
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEmail(rs.getString("email"));
				usuario.setArea(rs.getString("area"));
				usuarioCurso.setUsuario(usuario);

				Curso curso = new Curso();
				curso.setIdCurso(rs.getLong("id_curso"));
				curso.setNome(rs.getString("nome_curso"));
				curso.setDescricao(rs.getString("descricao"));
				curso.setDuracaoMedia(rs.getString("duracao_media"));
				usuarioCurso.setCurso(curso);
				
				usuarioCurso.setIdUsuarioCurso(rs.getLong("id_usuario_curso"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return usuarioCurso;
	}

	public List<UsuarioCurso> buscarTodos() {
		this.conexao.abrirConexao();
		UsuarioCurso usuarioCurso = null;
		String sqlSelect = "SELECT * FROM usuarioCurso";
		List<UsuarioCurso> listaUsuariosCursos = new ArrayList<UsuarioCurso>();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlSelect);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				usuarioCurso = new UsuarioCurso();
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEmail(rs.getString("email"));
				usuario.setArea(rs.getString("area"));
				usuarioCurso.setUsuario(usuario);

				Curso curso = new Curso();
				curso.setIdCurso(rs.getLong("id_curso"));
				curso.setNome(rs.getString("nome_curso"));
				curso.setDescricao(rs.getString("descricao"));
				curso.setDuracaoMedia(rs.getString("duracao_media"));
				usuarioCurso.setCurso(curso);
				
				usuarioCurso.setIdUsuarioCurso(rs.getLong("id_usuario_curso"));
				listaUsuariosCursos.add(usuarioCurso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaUsuariosCursos;
	}

}