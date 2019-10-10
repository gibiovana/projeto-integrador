package com.projeto.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projeto.model.Foto;
import com.projeto.persistence.FotoDAO;



@Controller
@RequestMapping(path = "/foto/")
public class FotoController {

	private FotoDAO fDAO;
	
	@RequestMapping (value="", method = RequestMethod.POST)
	public ResponseEntity<Foto> cadastrar(@RequestBody Foto foto){
		fDAO = new FotoDAO();
		System.out.println("URL: "+foto.getUrl());
		fDAO.inserir(foto);
		return new ResponseEntity<Foto>(foto, HttpStatus.CREATED);
	}
	
	
	@RequestMapping (value="", method = RequestMethod.GET)
	public ResponseEntity<List<Foto>> listaFotos() {
		fDAO = new FotoDAO();
		List<Foto> listaFotos = fDAO.buscarTodos();		
		
		return new ResponseEntity<List<Foto>> (listaFotos,HttpStatus.OK);
		
	}
	@RequestMapping (value="", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Foto foto){
		fDAO = new FotoDAO();
		fDAO.editar(foto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping (value="{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(long id){
		fDAO = new FotoDAO();
		fDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping (value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Foto> buscarPorId(@PathVariable long id){
		fDAO = new FotoDAO();
		Foto foto = fDAO.buscarPorId(id);
		if(foto!=null){
			return new ResponseEntity <Foto>(foto, HttpStatus.FOUND);
		}
		return new ResponseEntity<Foto> (foto, HttpStatus.NOT_FOUND);
	}
		
	
	

}
