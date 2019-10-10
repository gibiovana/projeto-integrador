package com.projeto.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projeto.model.UsuarioCurso;
import com.projeto.persistence.UsuarioCursoDAO;

@Controller
@RequestMapping(path = "/usuariocurso/")
public class UsuarioCursoController {

	private UsuarioCursoDAO ucDAO;
	
	@RequestMapping (value="", method = RequestMethod.POST)
	public ResponseEntity<UsuarioCurso> cadastrar(@RequestBody UsuarioCurso usuarioCurso){
		ucDAO = new UsuarioCursoDAO();
		System.out.println("id:"+usuarioCurso.getIdUsuarioCurso());
		ucDAO.cadastrar(usuarioCurso);
		return new ResponseEntity<UsuarioCurso>(usuarioCurso, HttpStatus.CREATED);
	}
	
	
	@RequestMapping (value="", method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioCurso>> listaUsuarioCursos() {
		ucDAO = new UsuarioCursoDAO();
		List<UsuarioCurso> listaUsuarioCursos = ucDAO.buscarTodos();		
		
		return new ResponseEntity<List<UsuarioCurso>> (listaUsuarioCursos,HttpStatus.OK);
		
	}
	@RequestMapping (value="", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody UsuarioCurso usuarioCurso){
		ucDAO = new UsuarioCursoDAO();
		ucDAO.editar(usuarioCurso);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping (value="{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		ucDAO = new UsuarioCursoDAO();
		ucDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping (value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<UsuarioCurso> buscarPorId(@PathVariable long id){
		ucDAO = new UsuarioCursoDAO();
		UsuarioCurso usuarioCurso = ucDAO.buscarPorId(id);
		if(usuarioCurso!=null){
			return new ResponseEntity <UsuarioCurso>(usuarioCurso, HttpStatus.FOUND);
		}
		return new ResponseEntity<UsuarioCurso> (usuarioCurso, HttpStatus.NOT_FOUND);
	}
		
	
	

}
