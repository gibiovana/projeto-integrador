package com.projeto.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projeto.model.Instituicao;
import com.projeto.persistence.InstituicaoDAO;

@Controller
@RequestMapping(path = "/instituicao/")
public class InstituicaoController {

	private InstituicaoDAO iDAO;
	
	@RequestMapping (value="", method = RequestMethod.POST)
	public ResponseEntity<Instituicao> cadastrar(@RequestBody Instituicao instituicao){
		iDAO = new InstituicaoDAO();
		System.out.println("NOME:"+instituicao.getNome());
		iDAO.cadastrar(instituicao);
		return new ResponseEntity<Instituicao>(instituicao, HttpStatus.CREATED);
	}
	
	
	@RequestMapping (value="", method = RequestMethod.GET)
	public ResponseEntity<List<Instituicao>> listaInstituicoes() {
		iDAO = new InstituicaoDAO();
		List<Instituicao> listaInstituicoes = iDAO.buscarTodasInstituicoes();		
		System.out.println("Funcionou");
		return new ResponseEntity<List<Instituicao>> (listaInstituicoes,HttpStatus.OK);
		
	}
	@RequestMapping (value="", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Instituicao instituicao){
		iDAO = new InstituicaoDAO();
		iDAO.editar(instituicao);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping (value="{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(long id){
		iDAO = new InstituicaoDAO();
		iDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping (value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Instituicao> buscarPorId(@PathVariable long id){
		iDAO = new InstituicaoDAO();
		Instituicao instituicao = iDAO.buscarPorId(id);
		if(instituicao!=null){
			return new ResponseEntity <Instituicao>(instituicao, HttpStatus.FOUND);
		}
		return new ResponseEntity<Instituicao> (instituicao, HttpStatus.NOT_FOUND);
	}
		
	
	

}
