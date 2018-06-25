package br.com.projetoJavaJr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.projetoJavaJr.model.repositoy.UserRepository;

@SpringBootApplication
public class ProjetoJavaJrApplication {

	@Autowired
	static UserRepository ur = null;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoJavaJrApplication.class, args);
//		User user = ur.findByCpf("14105903721");
//		System.out.println(user.getEmail());
	}
}
