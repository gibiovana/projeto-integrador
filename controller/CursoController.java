package com.projeto.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projeto.model.Curso;
import com.projeto.model.Instituicao;
import com.projeto.model.Pergunta;
import com.projeto.persistence.CursoDAO;
import com.projeto.persistence.InstituicaoDAO;
import com.projeto.persistence.PerguntaDAO;

@Controller
@RequestMapping(path = "/curso/")
public class CursoController {

	private CursoDAO cDAO;
	
	@RequestMapping (value="", method = RequestMethod.POST)
	public ResponseEntity<Curso> cadastrar(@RequestBody Curso curso){
		cDAO = new CursoDAO();
		System.out.println("NOME:"+curso.getNome());
		cDAO.cadastrar(curso);
		return new ResponseEntity<Curso>(curso, HttpStatus.CREATED);
	}
	
	
	@RequestMapping (value="", method = RequestMethod.GET)
	public ResponseEntity<List<Curso>> listaCursos() {
		cDAO = new CursoDAO();
		List<Curso> listaCursos = cDAO.buscarTodosCursos();		
		
		return new ResponseEntity<List<Curso>>(listaCursos,HttpStatus.OK);
		
	}
	@RequestMapping (value="", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Curso curso){
		cDAO = new CursoDAO();
		cDAO.editar(curso);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping (value="{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		cDAO = new CursoDAO();
		cDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	/*@RequestMapping (value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Curso> buscarPorId(@PathVariable long id){
		cDAO = new CursoDAO();
		Curso curso = cDAO.buscarPorId(id);
		if(curso!=null){
			return new ResponseEntity <Curso>(curso, HttpStatus.FOUND);
		}
		return new ResponseEntity<Curso> (curso, HttpStatus.NOT_FOUND);
	}*/
	
	@RequestMapping (value = "{idInstituicao}", method = RequestMethod.GET)
	public ResponseEntity<List<Curso>> buscarPorInstituicao(@PathVariable long idInstituicao){
		cDAO = new CursoDAO();
		Instituicao instituicao = new Instituicao();
		InstituicaoDAO iDAO = new InstituicaoDAO();
		instituicao = iDAO.buscarPorId(idInstituicao);
		List<Curso> curso = cDAO.buscarPorInstituicao(instituicao.getIdInstituicao());
		if(curso!=null){
			return new ResponseEntity<List<Curso>>(curso, HttpStatus.FOUND);
		}
		return new ResponseEntity <List<Curso>> (curso, HttpStatus.NOT_FOUND);
	}
		
	
	

}
