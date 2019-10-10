package com.projeto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projeto.model.Endereco;
import com.projeto.persistence.EnderecoDAO;

@Controller
@RequestMapping(path = "/endereco/")
public class EnderecoController {

	private EnderecoDAO eDAO;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Endereco> cadastrar(@RequestBody Endereco endereco) {
		eDAO = new EnderecoDAO();
		System.out.println("RUA:" + endereco.getRua());
		eDAO.cadastrar(endereco);
		return new ResponseEntity<Endereco>(endereco, HttpStatus.CREATED);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Endereco>> listaEnderecos() {
		eDAO = new EnderecoDAO();
		List<Endereco> listaEnderecos = eDAO.buscarTodos();
		return new ResponseEntity<List<Endereco>>(listaEnderecos, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Endereco endereco) {
		eDAO = new EnderecoDAO();
		eDAO.editar(endereco);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(long id) {
		eDAO = new EnderecoDAO();
		eDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Endereco> buscarPorId(@PathVariable long id) {
		eDAO = new EnderecoDAO();
		Endereco endereco = eDAO.buscarPorId(id);
		if (endereco != null) {
			return new ResponseEntity<Endereco>(endereco, HttpStatus.FOUND);
		}
		return new ResponseEntity<Endereco>(endereco, HttpStatus.NOT_FOUND);
	}
}
