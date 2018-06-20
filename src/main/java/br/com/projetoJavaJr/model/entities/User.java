package br.com.projetoJavaJr.model.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.projetoJavaJr.model.enuns.EEstadoCivil;
import br.com.projetoJavaJr.model.enuns.ESexo;

@Entity
public class User implements Serializable{

	private static final long serialVersionUID= 1L;

	
	@Id
	private String cpf;
	@NotNull
	private String nome;
	@NotNull
	private String email;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar nascimento;
	@NotNull
	private ESexo sexo;
	@NotNull
	private EEstadoCivil estadoCivil;	
	@NotNull
	private boolean ativo;
	
	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Calendar getNascimento() {
		return nascimento;
	}
	public void setNascimento(Calendar nascimento) {
		this.nascimento = nascimento;
	}
	public ESexo getSexo() {
		return sexo;
	}
	public void setSexo(ESexo sexo) {
		this.sexo = sexo;
	}
	public EEstadoCivil getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(EEstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
		
	
}
