package com.projeto.model;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.projeto.persistence.CursoDAO;
import com.projeto.persistence.InstituicaoDAO;
import com.projeto.persistence.NivelDAO;
import com.projeto.persistence.PerguntaDAO;
import com.projeto.persistence.RespostaDAO;
import com.projeto.persistence.UsuarioCursoDAO;
import com.projeto.persistence.UsuarioDAO;

public class Main {
	public static void main (String[]args){
		
		
		Scanner sc = new Scanner(System.in);
		
		Date d = new java.sql.Date(new java.util.Date().getTime());
		
		Pergunta pergunta = new Pergunta();
		PerguntaDAO pDAO = new PerguntaDAO();
		UsuarioDAO uDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();
		Instituicao instituicao = new Instituicao();
		InstituicaoDAO iDAO = new InstituicaoDAO();
		Resposta resposta = new Resposta();
		RespostaDAO rDAO = new RespostaDAO();
		CursoDAO cDAO = new CursoDAO();
		NivelDAO nDAO = new NivelDAO();
		List<Curso> curso = new ArrayList<Curso>();
		Nivel nivel = new Nivel();
		
		
		
		 /*pergunta.setDescricao("Se eu gosto das aplicações de biologia, mas quero permanecer na área de informática, o que devo cursar?");
		pergunta.setDataHora(d);
		usuario = uDAO.buscarPorId(19);
		pergunta.setUsuario(usuario);
		pDAO.cadastrar(pergunta);
		
		System.out.println(pergunta.toString());*/
		
		
		
		//instituicao = iDAO.buscarPorId(2);
		curso = cDAO.buscarPorInstituicao(2);
		System.out.println(curso.toString());
		
		/*usuario.setNome("Henrique");
		usuario.setLogin("heenrique");
		usuario.setEmail("henriquefigueredo@gmail.com");
		usuario.setNivel(nDAO.buscarPorId(1));
		usuario.setSenha("julia1");
		usuario.setArea("Engenharia");
		
		uDAO.cadastrar(usuario);*/
	
		
		
		/*List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		usuario = uDAO.buscarPorLoginESenha("gibiovana", "joaozinho");
		listaUsuarios.add(usuario);
		curso.setListaUsuarios(listaUsuarios);*/
		

		/*List<Usuario> listaUsuarios = uDAO.buscarTodos();
		System.out.println(listaUsuarios.toString());*/
		/*List<Usuario>listaUsuarios = new ArrayList<Usuario>();
		UsuarioCurso uC  = new UsuarioCurso();
		curso = cDAO.buscarPorId(2);
		usuario = uDAO.buscarPorLoginESenha("gibiovana","joaozinho");
		listaUsuarios.add(usuario);
		uC.setCurso(curso);
		uC.setUsuario(usuario);
		
		Usuario usuario2 = uDAO.buscarPorId(1);
		listaUsuarios.add(usuario2);
		uC.setUsuario(usuario2);
		curso.setListaUsuarios(listaUsuarios);

		
		
		System.out.println(uC.toString());*/
		
		
		/*
		System.out.println(instituicao.toString());*/
		 /*instituicao.setNome("Universidade Feevale");
		instituicao.setTelefone("51 3586.8811");
		instituicao.setTelefone2(null);
		iDAO.cadastrar(instituicao);
		
		curso.setNome("Ciência da Computação");
		curso.setDuracaoMedia("9 semestres");
		curso.setDescricao("Capacita os alunos para o desenvolvimento de programas e apps a partir das necessidades dos usuários.");		
		instituicao = iDAO.buscarPorId(3);
		curso.setInstituicao(instituicao);
		
		
		cDAO.editar(curso);
		
		List<Curso> listaCursos = new ArrayList<Curso>();
		listaCursos.add(curso);
		instituicao.setListaCursos(listaCursos);
		instituicao.setIdInstituicao(3);
		iDAO.editar(instituicao);
		
		
		//d e s g r a ç a
		
		for(Usuario usuario : listaUsuarios ){
			UsuarioCurso usuarioCurso = new UsuarioCurso();
			UsuarioCursoDAO ucDAO = new UsuarioCursoDAO();
			usuarioCurso.getCurso().getIdCurso();
			usuarioCurso.setUsuario(usuario);
			usuarios.add(usuario);
		}
		/*List<Curso> listaCursos = new ArrayList<Curso>();
		listaCursos.add(curso);
		instituicao.setListaCursos(listaCursos);
		instituicao.setIdInstituicao(3);
		iDAO.editar(instituicao);*/
		
		/*System.out.println(curso.toString());
		System.out.println(instituicao.toString());*/
		
		
		
		
		

		
		
		
		
		/*
		 * 
		nivel.setIdNivel(1);
		nivel.setNome("Mirim");
		nivel.setPontuacaoMinima(0);
		nDAO.cadastrar(nivel);
		usuario.setNivel(nivel);
		
		
		usuario.setCurso(curso);
		
		System.out.println("Nome:");
		String nome = sc.nextLine();
		usuario.setNome(nome);
		
		System.out.println("Login:");
		String login = sc.next();
		usuario.setLogin(login);
		
		System.out.println("Senha:");
		String senha = sc.next();
		usuario.setSenha(senha);		
		
		System.out.println("E-mail:");
		String email = sc.next();
		usuario.setEmail(email);
		
		System.out.println("Área de atuação:");
		String area = sc.next();
		usuario.setArea(area);*/
		
		
	

		
		sc.close();
	}

}
