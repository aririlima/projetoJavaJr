package br.com.projetoJavaJr.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@GetMapping(value="userCadastro")
	public ModelAndView getCadastro() {
		System.out.println("Página cadastro de usuário");
		return new ModelAndView("userCadastro");
	}
	
	@PostMapping(value="userCadastro")
	public ModelAndView postUser(User user) {

		System.out.println("POST Cadastrado");

		if (user != ur.findByCpf(user.getCpf())) {
			try {
//				System.out.println(user.getAtivo());

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
	
	@GetMapping(value="edit/{cpf}")
	public ModelAndView getEdit(@PathVariable("cpf") String cpf) {
		System.out.println("Página cadastro de usuário");
		User user = ur.findByCpf(cpf);
		ModelAndView mv = new ModelAndView("userEdit"); //página html que é exibida
		mv.addObject("user", user);
		return mv;
	}
	
	@PostMapping("edit/{cpf}")
	public ModelAndView userUpdate(@PathVariable("cpf") String cpf, User user) {
		System.out.println("Página edit post");

		try {
			User userE = ur.findByCpf(cpf);
			userE = user;
			ur.save(userE);
			
			Iterable <User> users = ur.findAll();
			ModelAndView mv = new ModelAndView("userEdit"); //página html que é exibida
			mv.addObject("users", users);
			return mv;
			
		}catch(Exception ex) {
			System.out.println(ex);
			return new ModelAndView("cadastroNaoRealizado");
		}
			
	}
	
	@GetMapping("delete/{cpf}")
	public ModelAndView userDelete(@PathVariable("cpf") String cpf) {
		System.out.println("Delet");

		try {
			ur.deleteByCpf(cpf);
			Iterable <User> users = ur.findAll();
			ModelAndView mv = new ModelAndView("usersList"); //página html que é exibida
			mv.addObject("users", users);
			return mv;
			
		}catch(Exception ex) {
			System.out.println(ex);
			return new ModelAndView("cadastroNaoRealizado");
		}		
		
	}
	
	@GetMapping(value="")
	public ModelAndView getUserList() {
		System.out.println("Página lista de usuário");
		Iterable <User> users = ur.findAll();
		
		
		ModelAndView mv = new ModelAndView("usersList"); //página html que é exibida
		mv.addObject("users", users);
		return mv;
	}
	
	@GetMapping(value="buscar/{nome}")
	public ModelAndView getByName(@PathVariable("nome") String nome) {
		System.out.println("filtro nome");
		User user = ur.findByNome(nome);
		ModelAndView mv = new ModelAndView("usersList"); //página html que é exibida
		mv.addObject("users", user);
		return mv;
	}
	@GetMapping(value="buscar/{cpf}")
	public ModelAndView getByCpf(@PathVariable("cpf") String cpf) {
		System.out.println("filtro cpf");
		User user = ur.findByCpf(cpf);

		ModelAndView mv = new ModelAndView("usersList"); //página html que é exibida
		mv.addObject("users", user);
		return mv;
	}
}
