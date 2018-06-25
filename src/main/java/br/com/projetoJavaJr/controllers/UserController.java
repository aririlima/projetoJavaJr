package br.com.projetoJavaJr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetoJavaJr.model.entities.User;
import br.com.projetoJavaJr.model.repositoy.UserRepository;


@RestController
@RequestMapping(value="/")
public class UserController {
	
	@Autowired
	private UserRepository ur;
	//
	@GetMapping(value="")
	public ModelAndView getUserList() {
		System.out.println("Página lista de usuário");
		Iterable <User> users = ur.findAll();
		
		ModelAndView mv = new ModelAndView("usersList");
		mv.addObject("users", users);
		return mv;
	}
	
	//
	@GetMapping(value="userCadastro")
	public ModelAndView getCadastro() {
		System.out.println("Página cadastro de usuário");
		return new ModelAndView("userCadastro");
	}
	
	//
	@PostMapping(value="userCadastro")
	public ModelAndView postUser(User user) {
		System.out.println("POST Cadastrado");
		if (user != ur.findByCpf(user.getCpf())) {
			try {
				ur.save(user);
				System.out.println("Usuário Cadastrado");
				return new ModelAndView("cadastroRealizado");
			} catch (Exception ex) {
				System.out.println(ex);
				return new ModelAndView("cadastroNaoRealizado");
			}
		} else {
			return new ModelAndView("cadastroNaoRealizado");
		}
	}
	
	//
	@GetMapping(value="edit/{cpf}")
	public ModelAndView getEdit(@PathVariable("cpf") String cpf) {
		System.out.println("Página cadastro de usuário");
		User user = ur.findByCpf(cpf);
		ModelAndView mv = new ModelAndView("userEdit"); 
		mv.addObject("user", user);
		return mv;
	}
	
	//
	@PostMapping("edit/{cpf}")
	public ModelAndView userUpdate(@PathVariable("cpf") String cpf, User user) {
		System.out.println("Página edit post");

		if(user.getAtivo() != "Ativo") {
			user.setAtivo("Inativo");
		}
		try {
			User userE = ur.findByCpf(cpf);
			userE = user;
			ur.save(userE);
			Iterable <User> users = ur.findAll();
			
			ModelAndView mv = new ModelAndView("usersList");
			mv.addObject("users", users);
			return mv;	
		}catch(Exception ex) {
			System.out.println(ex);
			return new ModelAndView("cadastroNaoRealizado");
		}		
	}
	
	//
	@GetMapping("delete/{cpf}")
	public ModelAndView userDelete(@PathVariable("cpf") String cpf) {
		System.out.println("Delete");

		try {
			User user = ur.findByCpf(cpf);
			ur.delete(user);
			Iterable <User> users = ur.findAll();
			
			ModelAndView mv = new ModelAndView("usersList");
			mv.addObject("users", users);
			return mv;
		}catch(Exception ex) {
			System.out.println(ex);
			return new ModelAndView("cadastroNaoRealizado");
		}		
	}
	
	//
	@GetMapping("deleteAll")
	public ModelAndView seleteAll() {
		System.out.println("DeletAll");
		try {
			ur.deleteAll();
			Iterable <User> users = ur.findAll();
			
			ModelAndView mv = new ModelAndView("usersList");
			mv.addObject("users", users);
			return mv;	
		}catch(Exception ex) {
			System.out.println(ex);
			return new ModelAndView("cadastroNaoRealizado");
		}		
		
	}
	
	//
	@GetMapping(value="search")
	public ModelAndView search() {
		Iterable <User> users = ur.findAll();
		ModelAndView mv = new ModelAndView("search"); 		
		mv.addObject("users", users);
		return mv;
	}
	
	//
	@GetMapping(value="searchByName/{nome}")
	public ModelAndView getByName(@PathVariable("nome") String nome, User user) {
		user = ur.findByNome(nome);
		System.out.println(user.getNome());

		ModelAndView mv = new ModelAndView("search"); 
		mv.addObject("user",user);
		return mv;
	}
	
	//
	@GetMapping(value="searchByCpf/{cpf}")
	public ModelAndView getByCpf(@PathVariable("cpf") String cpf, User user) {
		System.out.println("veio aqui");
		System.out.println();

		user = ur.findByCpf(cpf);
		System.out.println(user.getCpf());

		ModelAndView mv = new ModelAndView("usersList"); 
		mv.addObject("users", user);
		return mv;
	}
}
