package br.com.projetoJavaJr.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping(value="userCadastro")
	public ModelAndView getCadastro() {
		System.out.println("Página cadastro de usuário");
		return new ModelAndView("userCadastro");
	}
	
//	@PostMapping(value="userCadastro/{sexom}/{sexof}/{ativo}/{estadoCivil}")
//	@PathVariable("sexom") boolean sexom, @PathVariable("sexof") boolean sexof, @PathVariable("ativo") boolean ativo, @PathVariable("estadoCivil") String estadoCivil

	@PostMapping(value="userCadastro")
	public ModelAndView postUser(User user) {

		System.out.println("POST Cadastrado");
		
		try {
			if(user != ur.findByCpf(user.getCpf())) {
				ur.save(user);
				System.out.println("Usuário Cadastrado");
			}
			return new ModelAndView("cadastroRealizado");
		}catch(Exception ex) {
			System.out.println(ex);
			return new ModelAndView("cadastroNaoRealizado");
		}
	}
	
	@PostMapping("edit")
	public ModelAndView userUpdate(User user) {
		try {
			ur.save(user);
			return new ModelAndView("usersList");
			
		}catch(Exception ex) {
			System.out.println(ex);
			return new ModelAndView("cadastroNaoRealizado");
		}
			
	}
	
	@PutMapping("delete/{cpf}")
	public ModelAndView userDelete(@PathVariable("cpf") String cpf) {
		
		try {
			ur.deleteByCpf(cpf);
			return new ModelAndView("usersList");
			
		}catch(Exception ex) {
			System.out.println(ex);
			return new ModelAndView("cadastroNaoRealizado");
		}		
		
	}
	
	@GetMapping(value="")
	public ModelAndView getUserList() {
		System.out.println("Página lista de usuário");
		return new ModelAndView("usersList");
	}
	
	
}
