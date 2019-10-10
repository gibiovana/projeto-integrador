package com.projeto.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projeto.model.Resposta;
import com.projeto.persistence.RespostaDAO;



@Controller
@RequestMapping(path = "/resposta/")
public class RespostaController {

	private RespostaDAO rDAO;
	
	@RequestMapping (value="", method = RequestMethod.POST)
	public ResponseEntity<Resposta> cadastrar(@RequestBody Resposta resposta){
		rDAO = new RespostaDAO();
		System.out.println("NOME:"+resposta.getUsuario().getNome());
		rDAO.cadastrar(resposta);
		return new ResponseEntity<Resposta>(resposta, HttpStatus.CREATED);
	}
	
	
	@RequestMapping (value="", method = RequestMethod.GET)
	public ResponseEntity<List<Resposta>> listaRespostas() {
		rDAO = new RespostaDAO();
		List<Resposta> listaRespostas = rDAO.buscarTodasRespostas();		
		
		return new ResponseEntity<List<Resposta>> (listaRespostas,HttpStatus.OK);
		
	}
	@RequestMapping (value="", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Resposta resposta){
		rDAO = new RespostaDAO();
		rDAO.editar(resposta);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping (value="{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(long id){
		rDAO = new RespostaDAO();
		rDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping (value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Resposta> buscarPorId(@PathVariable long id){
		rDAO = new RespostaDAO();
		Resposta resposta = rDAO.buscarPorId(id);
		if(resposta!=null){
			return new ResponseEntity <Resposta>(resposta, HttpStatus.FOUND);
		}
		return new ResponseEntity<Resposta> (resposta, HttpStatus.NOT_FOUND);
	}
		
	
	

}
