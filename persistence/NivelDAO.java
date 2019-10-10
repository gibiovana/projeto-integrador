package com.projeto.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Statement;
import com.projeto.model.Nivel;

public class NivelDAO {

		private ConexaoMysql conexao;

		public NivelDAO() {
			super();
			this.conexao = new ConexaoMysql("localhost","root","","projeto_integrador");
		}

		public void cadastrar (Nivel nivel){
			this.conexao.abrirConexao();
			String sqlInsert = "INSERT INTO nivel VALUES (null, ?, ?)";
			try{
				PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				
				statement.setString(1, nivel.getNome());
				statement.setInt(2, nivel.getPontuacaoMinima());
				statement.executeUpdate();
				
				ResultSet rs = statement.getGeneratedKeys();
			
			}catch (SQLException e){
				e.printStackTrace();

			}finally{
				this.conexao.fecharConexao();
			}
			
		}
		
		public void editar (Nivel nivel){
			this.conexao.abrirConexao();
			
			String sqlUpdate = "UPDATE nivel SET nome_nivel=? , pontuacao_minima=? WHERE id_nivel=?";
			try{
				PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
				statement.setString(1, nivel.getNome());
				statement.setInt(2, nivel.getPontuacaoMinima());
				statement.setLong(3, nivel.getIdNivel());
				statement.executeUpdate();
			}catch (SQLException e ){
				e.printStackTrace();
			}finally{
				this.conexao.fecharConexao();
			}
		}
		
		
		public void excluir (long idNivel){
			this.conexao.abrirConexao();
			String sqlDelete = "DELETE FROM nivel WHERE id_nivel=?";
			try{
				PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
				statement.setLong(1,idNivel);
				
				statement.executeUpdate();
			}catch (SQLException e){
				e.printStackTrace();
			}finally{
				this.conexao.fecharConexao();
			}
		}
		
		
		public Nivel buscarPorId(long idNivel){
			Nivel nivel = null;
			this.conexao.abrirConexao();
			String sqlInsert = "SELECT * FROM nivel WHERE id_nivel=?";
			
			try{
				PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert);
				statement.setLong(1, idNivel);
				
				ResultSet rs = statement.executeQuery();
				if(rs.next()){
					nivel=new Nivel();
					nivel.setIdNivel(rs.getLong("id_nivel"));
					nivel.setNome(rs.getString("nome_nivel"));
					nivel.setPontuacaoMinima(rs.getInt("pontuacao_minima"));
				
				}
			}catch (Exception e){
				e.printStackTrace();
			}finally{
				this.conexao.fecharConexao();
			}
			return nivel;
		}
		
		public List<Nivel> buscarTodos() {
	        this.conexao.abrirConexao();
	        Nivel nivel = null;
	        String sqlSelect = "SELECT * FROM nivel";
	        List<Nivel> listaNiveis = new ArrayList<Nivel>();
	        try {
	            PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlSelect);

	            ResultSet rs = statement.executeQuery();
	            while(rs.next()){
	                nivel = new Nivel();
	                nivel.setIdNivel(rs.getLong("id_nivel"));
	                nivel.setNome(rs.getString("nome_nivel"));
	                nivel.setPontuacaoMinima(rs.getInt("pontuacao_minima"));
	                
	                listaNiveis.add(nivel);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            this.conexao.fecharConexao();
	        }
	        return listaNiveis;
	    }
		
	    public Nivel buscarPorNome(String nome) {
	        Nivel nivel = null;
	        this.conexao.abrirConexao();

	        String sqlInsert = "SELECT * FROM nivel WHERE nome_nivel=?";
	        try {
	            PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert);
	            statement.setString(1, nome);

	            ResultSet rs = statement.executeQuery();
	            if(rs.next()){
	               	nivel = new Nivel();
	               	nivel.setIdNivel(rs.getLong("id_nivel"));
	               	nivel.setNome(rs.getString("nome_nivel"));
	               	nivel.setPontuacaoMinima(rs.getInt("pontuacao_minima")); 
	            }
	    

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            this.conexao.fecharConexao();
	        }
	        return nivel;
	    }

}

