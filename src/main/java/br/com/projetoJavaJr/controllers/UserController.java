package br.com.projetoJavaJr.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetoJavaJr.model.entities.User;
import br.com.projetoJavaJr.model.enuns.EEstadoCivil;
import br.com.projetoJavaJr.model.enuns.ESexo;
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
	
	@PutMapping(value="userCadastro/{sexom}/{sexof}/{ativo}/{estadoCivil}")
	public ModelAndView userCadastro(User user, @PathVariable("sexom") boolean sexom, @PathVariable("sexof") boolean sexof, @PathVariable("ativo") boolean ativo, @PathVariable("estadoCivil") String estadoCivil) {

		System.out.println("PUT Cadastrado");
		
		if(sexom == true) {
			user.setSexo(ESexo.MASCULINO);
		}else if(sexof == true) {
			user.setSexo(ESexo.FEMININO);
		}else {
			System.out.println("Sexo não informado.");
		}
		
		if(ativo == true) {
			user.setAtivo(true);
		}else{
			user.setAtivo(false);
		}
		
		switch(estadoCivil) {
			case "1":
				user.setEstadoCivil(EEstadoCivil.SOLTEIRO);
				break;
			case "2":
				user.setEstadoCivil(EEstadoCivil.CASADO);
				break;
			case "3":
				user.setEstadoCivil(EEstadoCivil.VIUVO);
				break;
			default:
				System.out.println("Estado civil não informado.");
		}
		
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
	
	@PutMapping("/edit/{cpf}")
	public ModelAndView edit(@PathVariable("cpf") String cpf) {
		try {
			ur.findByCpf(cpf);
			return new ModelAndView("usersList");
			
		}catch(Exception ex) {
			System.out.println(ex);
		}
			
		return new ModelAndView("usersList");
	}
	
	@PutMapping("/delete/{cpf}")
	public ModelAndView delete(@PathVariable("cpf") String cpf) {
		
		try {
			ur.deleteByCpf(cpf);
			return new ModelAndView("usersList");
			
		}catch(Exception ex) {
			System.out.println(ex);
		}		
		
		return new ModelAndView("usersList");
	}
	
	@GetMapping(value="cadastroRealizado")
	public ModelAndView getLista() {
		System.out.println("Página lista de usuário");
		return new ModelAndView("usersList");
	}
}
