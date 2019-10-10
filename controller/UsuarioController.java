package com.projeto.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projeto.model.Usuario;

import com.projeto.persistence.UsuarioDAO;

@Controller
@RequestMapping(path = "/usuario/")
public class UsuarioController {

	private UsuarioDAO uDAO;
	
	@RequestMapping (value="", method = RequestMethod.POST)
	public ResponseEntity<Usuario> cadastroUsuario(@RequestBody Usuario usuario){
		uDAO = new UsuarioDAO();
		System.out.println("NOME:"+usuario.getNome());
		uDAO.cadastrar(usuario);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
	}
	
	
	@RequestMapping (value="", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listaTodosUsuarios() {
		uDAO = new UsuarioDAO();
		List<Usuario> listaUsuarios = uDAO.buscarTodos();		
		return new ResponseEntity<List<Usuario>> (listaUsuarios,HttpStatus.OK);
		
	}
	@RequestMapping (value="", method = RequestMethod.PUT)
	public ResponseEntity<Void> editarUsuario(@RequestBody Usuario usuario){
		uDAO = new UsuarioDAO();
		uDAO.editar(usuario);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping (value="{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluirUsuario(long id){
		uDAO = new UsuarioDAO();
		uDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping (value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable long id){
		uDAO = new UsuarioDAO();
		Usuario usuario = uDAO.buscarPorId(id);
		if(usuario!=null){
			return new ResponseEntity <Usuario>(usuario, HttpStatus.FOUND);
		}
		return new ResponseEntity<Usuario> (usuario, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping (value="{email}/{senha}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscarUsuarioPorEmailESenha(@PathVariable String email, @PathVariable String senha){
		uDAO = new UsuarioDAO();
		Usuario usuario = uDAO.buscarPorEmailESenha(email,senha);
		if(usuario!=null){
			return new ResponseEntity <Usuario>(usuario, HttpStatus.FOUND);
		}
		return new ResponseEntity<Usuario> (usuario, HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
