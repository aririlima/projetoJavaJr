package br.com.projetoJavaJr.model.repositoy;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.projetoJavaJr.model.entities.User;


//REPOSITÓRIO DE GERENCIAMENTO DE USUÁRIOS
public interface UserRepository extends CrudRepository<User, String>{

//    @Query("SELECT u.cpf FROM User u where u.cpf = :cpf") 
	User findByCpf(@Param("cpf") String cpf);

//    @Query("SELECT u.email FROM User u where u.nome like :nome") 	
	User findByNome(@Param("nome") String nome);
	
	//Returns the number of entities available.
	long count();
	
	//	Deletes a given entity.
	void delete(User entity);
	
	//	Deletes all entities managed by the repository.
	void deleteAll();
	
	//	Deletes the given entities.
	void deleteAll(Iterable<? extends User> entities);
	
	//	Deletes the entity with the given cpf.
	void deleteByCpf(String cpf);
	
	//	Returns whether an entity with the given id exists.
	boolean	existsByCpf(String cpf);
	
	//	Returns all instances of the type.
	Iterable<User>	findAll();
		


}

