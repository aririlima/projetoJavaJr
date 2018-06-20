package br.com.projetoJavaJr.model.repositoy;

import org.springframework.data.repository.CrudRepository;

import br.com.projetoJavaJr.model.entities.User;


//REPOSITÓRIO GLOBAL DE GERENCIAMENTO DE USUÁRIOS
public interface UserRepository extends CrudRepository<User, String>{

	User findByCpf(String id);
		
	User findByEmail(String email);
	
	User findByNome(String nome);
		
	//Returns the number of entities available.
	long count();
		
	//	Deletes a given entity.
	void delete(User entity);
		
	//	Deletes all entities managed by the repository.
	void deleteAll();
		
	//	Deletes the given entities.
	void deleteAll(Iterable<? extends User> entities);

	//	Deletes the entity with the given id.
	void deleteByCpf(String id);
		
	//	Returns whether an entity with the given id exists.
	boolean	existsByCpf(String id);
		
	//	Returns all instances of the type.
	Iterable<User>	findAll();
		


}

