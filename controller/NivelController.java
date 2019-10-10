package com.projeto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projeto.model.Nivel;
import com.projeto.persistence.NivelDAO;

@Controller
@RequestMapping(path = "/nivel/")
public class NivelController {

	private NivelDAO nDAO;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Nivel> cadastrar(@RequestBody Nivel nivel) {
		nDAO = new NivelDAO();
		System.out.println("NOME:" + nivel.getNome());
		nDAO.cadastrar(nivel);
		return new ResponseEntity<Nivel>(nivel, HttpStatus.CREATED);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Nivel>> listaNiveis() {
		nDAO = new NivelDAO();
		List<Nivel> listaNiveis = nDAO.buscarTodos();
		return new ResponseEntity<List<Nivel>>(listaNiveis, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Nivel nivel) {
		nDAO = new NivelDAO();
		nDAO.editar(nivel);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(long id) {
		nDAO = new NivelDAO();
		nDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Nivel> buscarPorId(@PathVariable long id) {
		nDAO = new NivelDAO();
		Nivel nivel = nDAO.buscarPorId(id);
		if (nivel != null) {
			return new ResponseEntity<Nivel>(nivel, HttpStatus.FOUND);
		}
		return new ResponseEntity<Nivel>(nivel, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "{nome_nivel}", method = RequestMethod.GET)
	public ResponseEntity<Nivel> buscarPorNome(@PathVariable String nome) {
		nDAO = new NivelDAO();
		Nivel nivel = nDAO.buscarPorNome(nome);
		if (nivel != null) {
			return new ResponseEntity<Nivel>(nivel, HttpStatus.FOUND);
		}
		return new ResponseEntity<Nivel>(nivel, HttpStatus.NOT_FOUND);
	}

}
