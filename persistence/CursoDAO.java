package com.projeto.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.projeto.model.Curso;
import com.projeto.model.Instituicao;

public class CursoDAO {

	private ConexaoMysql conexao;

	public CursoDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "projeto_integrador");
	}

	public void cadastrar(Curso curso) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO curso VALUES (null, ?, ?, ?,?)";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, curso.getNome());
			statement.setString(2, curso.getDuracaoMedia());
			statement.setString(3, curso.getDescricao());
			statement.setLong(4, curso.getInstituicao().getIdInstituicao());
			statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			this.conexao.fecharConexao();
		}

	}

	public void editar(Curso curso) {
		this.conexao.abrirConexao();

		String sqlUpdate = "UPDATE curso SET nome_curso=? , duracao_media=?, descricao=?, id_instituicao=? WHERE id_curso=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			statement.setString(1, curso.getNome());
			statement.setString(2, curso.getDuracaoMedia());
			statement.setString(3, curso.getDescricao());
			statement.setLong(4, curso.getInstituicao().getIdInstituicao());
			statement.setLong(5, curso.getIdCurso());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluir(long idCurso) {
		this.conexao.abrirConexao();
		String sqlDelete = "DELETE FROM curso WHERE id_curso=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, idCurso);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public Curso buscarPorId(long idCurso) {
		Curso curso = null;
		Instituicao instituicao = null;
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM curso INNER JOIN instituicao ON curso.id_instituicao = instituicao.id_instituicao WHERE id_curso=?";

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, idCurso);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				curso = new Curso();
				curso.setIdCurso(rs.getLong("id_curso"));
				curso.setNome(rs.getString("nome_curso"));
				curso.setDuracaoMedia(rs.getString("duracao_media"));
				curso.setDescricao(rs.getString("descricao"));

				instituicao = new Instituicao();
				instituicao.setIdInstituicao(rs.getLong("id_instituicao"));
				instituicao.setNome(rs.getString("nome_instituicao"));
				instituicao.setTelefone(rs.getString("telefone"));
				instituicao.setTelefone2(rs.getString("telefone2"));
				curso.setInstituicao(instituicao);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return curso;
	}

	public List<Curso> buscarTodosCursos() {
		this.conexao.abrirConexao();
		Curso curso = null;
		Instituicao instituicao = null;
		String sqlSelect = "SELECT * FROM curso INNER JOIN instituicao ON curso.id_instituicao = instituicao.id_instituicao";
		List<Curso> listaCursos = new ArrayList<Curso>();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlSelect);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				curso = new Curso();
				curso.setIdCurso(rs.getLong("id_curso"));
				curso.setNome(rs.getString("nome_curso"));
				curso.setDuracaoMedia(rs.getString("duracao_media"));
				curso.setDescricao(rs.getString("descricao"));

				instituicao = new Instituicao();
				instituicao.setIdInstituicao(rs.getLong("id_instituicao"));
				instituicao.setNome(rs.getString("nome_instituicao"));
				instituicao.setTelefone(rs.getString("telefone"));
				instituicao.setTelefone2(rs.getString("telefone2"));
				curso.setInstituicao(instituicao);

				listaCursos.add(curso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaCursos;
	}

	public Curso buscarPorNome(String nome) {
		Curso curso = null;
		this.conexao.abrirConexao();

		String sqlInsert = "SELECT * FROM curso INNER JOIN instituicao ON curso.id_instituicao = instituicao.id_instituicao WHERE nome_curso=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setString(1, nome);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				curso = new Curso();
				curso.setIdCurso(rs.getLong("id_curso"));
				curso.setNome(rs.getString("nome_curso"));
				curso.setDuracaoMedia(rs.getString("duracao_media"));
				curso.setDescricao(rs.getString("descricao"));

				Instituicao instituicao = new Instituicao();
				instituicao.setIdInstituicao(rs.getLong("id_instituicao"));
				instituicao.setNome(rs.getString("nome_instituicao"));
				instituicao.setTelefone(rs.getString("telefone"));
				instituicao.setTelefone2(rs.getString("telefone2"));
				curso.setInstituicao(instituicao);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return curso;
	}

	public List<Curso> buscarPorInstituicao(long idInstituicao) {
		List<Curso> listaCursos = new ArrayList<Curso>();
		Curso curso = null;
		Instituicao instituicao = null;
		this.conexao.abrirConexao();

		String sqlSelect = "SELECT * FROM curso INNER JOIN instituicao ON curso.id_instituicao = instituicao.id_instituicao WHERE curso.id_instituicao=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			statement.setLong(1, idInstituicao);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				curso = new Curso();
				curso.setIdCurso(rs.getLong("id_curso"));
				curso.setNome(rs.getString("nome_curso"));
				curso.setDuracaoMedia(rs.getString("duracao_media"));
				curso.setDescricao(rs.getString("descricao"));

				instituicao = new Instituicao();
				instituicao.setIdInstituicao(rs.getLong("id_instituicao"));
				instituicao.setNome(rs.getString("nome_instituicao"));
				instituicao.setTelefone(rs.getString("telefone"));
				instituicao.setTelefone2(rs.getString("telefone2"));
				curso.setInstituicao(instituicao);

				listaCursos.add(curso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaCursos;
	}

}