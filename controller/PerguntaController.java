package com.projeto.controller;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projeto.model.Pergunta;
import com.projeto.persistence.PerguntaDAO;



@Controller
@RequestMapping(path = "/pergunta/")
public class PerguntaController {

	private PerguntaDAO pDAO;
	
	@RequestMapping (value="", method = RequestMethod.POST)
	public ResponseEntity<Pergunta> cadastrar(@RequestBody Pergunta pergunta){
		pDAO = new PerguntaDAO();
		System.out.println("NOME:"+pergunta.getUsuario().getNome());
		pDAO.cadastrar(pergunta);
		return new ResponseEntity<Pergunta>(pergunta, HttpStatus.CREATED);
	}
	
	
	@RequestMapping (value="", method = RequestMethod.GET)
	public ResponseEntity<List<Pergunta>> listaPerguntas() {
		pDAO = new PerguntaDAO();
		List<Pergunta> listaPerguntas = pDAO.buscarTodasPerguntas();		
		
		return new ResponseEntity<List<Pergunta>> (listaPerguntas,HttpStatus.OK);
		
	}
	@RequestMapping (value="", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Pergunta pergunta){
		pDAO = new PerguntaDAO();
		pDAO.editar(pergunta);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping (value="{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(long id){
		pDAO = new PerguntaDAO();
		pDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping (value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Pergunta> buscarPorId(@PathVariable long id){
		pDAO = new PerguntaDAO();
		Pergunta pergunta = pDAO.buscarPorId(id);
		if(pergunta!=null){
			return new ResponseEntity <Pergunta>(pergunta, HttpStatus.FOUND);
		}
		return new ResponseEntity<Pergunta> (pergunta, HttpStatus.NOT_FOUND);
	}
		
	
	

}
